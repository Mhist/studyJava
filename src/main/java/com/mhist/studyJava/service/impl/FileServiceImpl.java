package com.mhist.studyJava.service.impl;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhist.studyJava.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import okhttp3.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class FileServiceImpl implements FileService {

    @Value("${minio.bucketName}")
    private String bucketName;


    @Value("${minio.apiIP}")
    private String apiIP;
    private final MinioClient minioClient;

    public FileServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, InterruptedException {
        System.out.println(file);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        // 检查桶是否存在，不存在则创建
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        // 上传文件到桶
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .contentType(file.getContentType()) // 根据实际情况设置MIME类型
                        .object(file.getOriginalFilename())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build());

        // 生成一个公开的预签名URL，用于访问上传的文件
        // 设置一个非常长的有效期，例如100年
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT)
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .expiry(7, TimeUnit.DAYS)
                        .build());

//        http://119.91.213.59:9001/api/v1/buckets/mhist/objects/download?
//        preview=true&prefix=dsdsdad.png&version_id=700bb7ca-d105-463a-a1f9-4c3b69d7b8b2

        GetObjectResponse object = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .build());
        Headers headers = object.headers();
        System.out.println(headers);
        String version_id = headers.get("x-amz-version-id");

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("preview", "true");
        queryMap.put("prefix", file.getOriginalFilename());
        queryMap.put("version_id",version_id);

        // 转换为MultiValueMap
        LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            // 将单个值放入集合中，即使目前只有一个值，这样可以保持与MultiValueMap的接口一致
            multiValueMap.add(entry.getKey(), entry.getValue());
            // 如果原始Map中某个键已经有多个值，你可以通过multiValueMap.addAll(key, Collections.singletonList(value))来添加
        }

        // 使用UriComponentsBuilder将Map转换为查询字符串
        String queryString = UriComponentsBuilder.newInstance()
                .queryParams(multiValueMap)
                .build()
                .getQuery();

        // 输出查询字符串
        System.out.println(queryString);
        String previewUrl = apiIP + "/api/v1/buckets/" + bucketName + "/objects/download?" + queryString;
        return previewUrl;
    }
}
