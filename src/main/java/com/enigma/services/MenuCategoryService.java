package com.enigma.services;

import com.enigma.entities.MenuCategory;

import java.util.List;

public interface MenuCategoryService {
    List<MenuCategory> getAllMenuCategory();
    MenuCategory createMenuCategory(MenuCategory menuCategory);
    void deleteMenuCategoryById(String id);
    MenuCategory updateMenuCategory(MenuCategory menuCategory);
    MenuCategory getMenuCategoryById(String id);
}
