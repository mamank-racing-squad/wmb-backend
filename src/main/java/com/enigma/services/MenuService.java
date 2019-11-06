package com.enigma.services;

import com.enigma.entities.Menu;

import java.util.List;

public interface MenuService {

    public Menu getMenuById(String id);
    public List<Menu> getAllMenu();
    public Menu createMenu(Menu menu);
    public void deleteMenuById(String id);
    public Menu updateMenu(Menu menu);
}
