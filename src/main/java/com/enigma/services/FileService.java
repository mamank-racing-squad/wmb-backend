package com.enigma.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void saveFile(MultipartFile image, String id) throws IOException;
}
