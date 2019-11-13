package com.enigma.services.implement;

import com.enigma.entities.Menu;
import com.enigma.entities.MenuCategory;
import com.enigma.repositories.MenuRepository;
import com.enigma.services.FileService;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuCategoryService menuCategoryService;
    @Autowired
    FileService fileService;

    @Override
    public Menu getMenuById(String id) {
        return menuRepository.findById(id).get();
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu createMenu(Menu menu) {
        MenuCategory menuCategory = menuCategoryService.getMenuCategoryById(menu.getIdMenuCategoryTransient());
        menu.setMenuCategory(menuCategory);
        return menuRepository.save(menu);
    }

    @Override
    public Menu createMenuWithImage(String menuInput, MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Menu menu = objectMapper.readValue(menuInput, Menu.class);
        menu = createMenu(menu);
        fileService.saveFile(image, menu.getIdMenu());
        return menu;
    }

    @Override
    public void deleteMenuById(String id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }
}
