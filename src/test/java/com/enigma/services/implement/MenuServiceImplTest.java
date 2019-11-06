package com.enigma.services.implement;

import com.enigma.entities.Menu;
import com.enigma.repositories.MenuRepository;
import com.enigma.services.MenuService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {

    @Autowired
    MenuService menuService;
    @Autowired
    MenuRepository menuRepository;

    @Before
    public void cleanUp(){
        menuRepository.deleteAll();
    }

    @Test
    public void getMenuById_should_return_Menu_when_Found() {
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true);
        menu = menuService.createMenu(menu);
        Menu actual = menuService.getMenuById(menu.getIdMenu());
        Menu expected = menuRepository.findById(menu.getIdMenu()).get();
        assertEquals(expected, actual);
    }
    @Test
    public void getMenuById_should_return_notnull_when_Found(){
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true);
        menu = menuService.createMenu(menu);
        assertNotEquals(0,menuService.getMenuById(menu.getIdMenu()));
    }

    @Test
    public void getAllMenu_should_return_2_when_inputTwoMenu() {
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true);
        Menu menu2 = new Menu("Ayam Bakar", new BigDecimal(40000), true);
        menuService.createMenu(menu1);
        menuService.createMenu(menu2);
        assertEquals(2, menuService.getAllMenu().size());
    }

    @Test
    public void createMenu_should_return_Menu_when_inputMenu() {
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true);
        menu = menuService.createMenu(menu);
        Menu expected = menuRepository.findById(menu.getIdMenu()).get();
        assertEquals(expected, menu);
    }

    @Test
    public void createMenuWithImage_should_return_menu_and_image_ExistOnServer() {

    }

    @Test
    public void deleteMenuById_should_return_1_when_1of2_data_deleted() {
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true);
        Menu menu2 = new Menu("Ayam Bakar", new BigDecimal(40000), true);
        menu1 = menuService.createMenu(menu1);
        menuService.createMenu(menu2);
        menuService.deleteMenuById(menu1.getIdMenu());
        assertEquals(1, menuService.getAllMenu().size());
    }

    @Test
    public void updateMenu_should_return_new_Menu_when_Data_Updated() {
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true);
        menu = menuService.createMenu(menu);
        Menu edited = menu;
        edited.setNameMenu("Ayam Bakar");
        menuService.updateMenu(edited);
        assertEquals(edited, menuRepository.findById(menu.getIdMenu()).get());

    }
}