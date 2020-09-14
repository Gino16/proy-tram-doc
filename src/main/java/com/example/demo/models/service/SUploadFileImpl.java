package com.example.demo.models.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class SUploadFileImpl implements SIUploadFile {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String UPLOADS_FOLDER = "uploads";

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Path rootPath = getPath(uniqueFilename);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile();
        if(archivo.exists() && archivo.canRead()){
            if(archivo.delete()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOADS_FOLDER));
    }

    @Override
    public byte[] getBytes(String filename) throws Exception{
        return Files.readAllBytes(getPath(filename));
    }


    public Path getPath(String filename){
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}
