package com.enigma.controllers;

import com.enigma.entities.MenuCategory;
import com.enigma.services.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MenuCategoryController {

    @Autowired
    MenuCategoryService menuCategoryService;

    @GetMapping("/menu-categories")
    public List<MenuCategory> getAllMenuCategory(){
        return menuCategoryService.getAllMenuCategory();
    }

    @PutMapping("/menu-category")
    public MenuCategory createMenuCategory(@RequestBody MenuCategory menuCategory){
        return menuCategoryService.createMenuCategory(menuCategory);
    }

    @PostMapping("/menu-category")
    public MenuCategory updateMenuCategory(@RequestBody MenuCategory menuCategory){
        return menuCategoryService.updateMenuCategory(menuCategory);
    }

    @GetMapping("/menu-category/{id}")
    public MenuCategory getMenuCategoryById(@PathVariable String id){
        return menuCategoryService.getMenuCategoryById(id);
    }

    @DeleteMapping("/menu-category/{id}")
    public void deleteMenuCategoryById(@PathVariable String id){
        menuCategoryService.deleteMenuCategoryById(id);
    }
}
