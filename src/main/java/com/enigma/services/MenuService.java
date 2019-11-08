package com.enigma.services;

import com.enigma.entities.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MenuService {

    public Menu getMenuById(String id);
    public List<Menu> getAllMenu();
    public Menu createMenu(Menu menu);
    public Menu createMenuWithImage(String menuInput, MultipartFile image) throws IOException;
    public void deleteMenuById(String id);
    public Menu updateMenu(Menu menu);
}
