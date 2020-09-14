package com.example.demo.models.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface SIUploadFile {
    public String copy(MultipartFile file) throws IOException;

    public boolean delete(String filename);

    public void deleteAll();

    public void init() throws IOException;

    public byte[] getBytes(String filename) throws Exception;
}
