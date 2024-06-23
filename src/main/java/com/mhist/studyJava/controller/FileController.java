package com.mhist.studyJava.controller;

import com.mhist.studyJava.pojo.Result;
import com.mhist.studyJava.service.FileService;
import com.mhist.studyJava.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // 获取文件的输入流
        try {
            String fileUrl = fileService.upload(file);
            return Result.success(fileUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
    }
}
