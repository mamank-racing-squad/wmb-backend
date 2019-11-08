package com.enigma.controllers;

import com.enigma.entities.Menu;
import com.enigma.services.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class MenuController {
    @Autowired
    MenuService menuService;


    @PutMapping("/menu")
    public Menu createMenu(@RequestBody Menu menu){
        return menuService.createMenu(menu);
    }

    @PutMapping("/menu/upload")
    public Menu createMenuWithImage(@RequestPart MultipartFile image, String menuInput) throws IOException {
        return menuService.createMenuWithImage(menuInput, image);
    }

    @GetMapping("/menu/{id}")
    public Menu getMenuById(@PathVariable String id){
        return menuService.getMenuById(id);
    }

    @GetMapping("/list-menu")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenu();
    }

    @DeleteMapping("/menu/{id}")
    public void deleteMenu(@PathVariable String id){
        menuService.deleteMenuById(id);
    }

    @PostMapping("/menu")
    public Menu updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

}