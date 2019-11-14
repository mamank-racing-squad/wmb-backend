package com.enigma.services;

import com.enigma.entities.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MenuService {
    Menu getMenuById(String id);
    List<Menu> getAllMenu();
    Menu createMenuWithImage(String menuInput, MultipartFile image) throws IOException;
    void deleteMenuById(String id);
    Menu updateMenuWithImage(String menuInput, MultipartFile image) throws IOException;
}
