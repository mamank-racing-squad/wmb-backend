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

    @GetMapping("/all-menu-category")
    public List<MenuCategory> getAllMenuCategory(){
        return menuCategoryService.getAllMenuCategory();
    }

    @PostMapping("/menu-category")
    public MenuCategory createMenuCategory(@RequestBody MenuCategory menuCategory){
        return menuCategoryService.createMenuCategory(menuCategory);
    }
    @DeleteMapping("/menu-category/{id}")
    public void deleteMenuCategoryById(@PathVariable String id){
        menuCategoryService.deleteMenuCategoryById(id);
    }

    @PutMapping("update/menu-category")
    public MenuCategory updateMenuCategory(@RequestBody MenuCategory menuCategory){
        return menuCategoryService.updateMenuCategory(menuCategory);
    }
}
