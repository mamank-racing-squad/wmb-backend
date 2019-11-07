package com.enigma.services.implement;

import com.enigma.entities.MenuCategory;
import com.enigma.exceptions.InputCanNotBeEmptyException;
import com.enigma.repositories.MenuCategoryRepository;
import com.enigma.services.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    @Autowired
    MenuCategoryRepository menuCategoryRepository;

    @Override
    public List<MenuCategory> getAllMenuCategory() {
        return menuCategoryRepository.findAll();
    }

    @Override
    public MenuCategory createMenuCategory(MenuCategory menuCategory) {
        if(menuCategory.getCategoryName().equals("")){
            throw new InputCanNotBeEmptyException();
        }
        return menuCategoryRepository.save(menuCategory);
    }

    @Override
    public void deleteMenuCategoryById(String id) {
        menuCategoryRepository.deleteById(id);
    }

    @Override
    public MenuCategory updateMenuCategory(MenuCategory menuCategory) {
        return menuCategoryRepository.save(menuCategory);
    }
}
