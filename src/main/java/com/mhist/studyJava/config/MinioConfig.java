package com.mhist.studyJava.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
@Data
@Configuration
public class MinioConfig<InvalidEndpointException> {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 创建并返回MinioClient实例，用于与MinIO服务交互。
     *
     * @return MinioClient 实例
     */
    @Primary
    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();
        } catch (Exception e) {
            // 异常处理逻辑，例如记录日志或者抛出自定义异常
            throw new RuntimeException("MinIO Client initialization failed", e);
        }
    }
}
