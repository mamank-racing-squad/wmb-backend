package com.enigma.services.implement;

import com.enigma.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void saveFile(MultipartFile image, String id) throws IOException {
//        Windows Dir
//        File file = new File("/var/www/html/menu-images/"+id);
//        Linux Dir
//        File file = new File("E:/Software/nginx-1.16.1/html/menu-image/"+id+".jpg");
        file.createNewFile();
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(image.getBytes());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
