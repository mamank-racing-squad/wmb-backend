package com.enigma.services;

import com.enigma.entities.MenuCategory;

import java.util.List;

public interface MenuCategoryService {
    public List<MenuCategory> getAllMenuCategory();
    public MenuCategory createMenuCategory(MenuCategory menuCategory);
    public void deleteMenuCategoryById(String id);
    public MenuCategory updateMenuCategory(MenuCategory menuCategory);
    public MenuCategory getMenuById(String id);
}
