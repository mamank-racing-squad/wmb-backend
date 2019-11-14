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
<<<<<<< HEAD
//        Windows Dir
//        File file = new File("/var/www/html/menu-images/"+id);
//        Linux Dir
//        File file = new File("E:/Software/nginx-1.16.1/html/menu-image/"+id+".jpg");
=======
        File file = new File("C:/nginx-1.16.1/html/menu-image/"+id+".jpg");
>>>>>>> e61c986d5cf0294e977c7810b0a219cca7a09dbd
        file.createNewFile();
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(image.getBytes());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
