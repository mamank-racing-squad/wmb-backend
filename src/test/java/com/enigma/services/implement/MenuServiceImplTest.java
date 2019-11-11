package com.enigma.services.implement;

import com.enigma.entities.Menu;
import com.enigma.entities.MenuCategory;
import com.enigma.repositories.MenuRepository;
import com.enigma.services.MenuCategoryService;
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

    @Autowired
    MenuCategoryService menuCategoryService;

    @Before
    public void cleanUp(){
        menuRepository.deleteAll();
    }

    MenuCategory menuCategory = new MenuCategory("Foods");

    @Test
    public void getMenuById_should_return_Menu_when_Found() {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory() );
        menu = menuService.createMenu(menu);
        Menu actual = menuService.getMenuById(menu.getIdMenu());
        Menu expected = menuRepository.findById(menu.getIdMenu()).get();
        assertEquals(expected, actual);
    }

    @Test
    public void getMenuById_should_return_notnull_when_Found(){
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory() );
        menu = menuService.createMenu(menu);
        assertNotEquals(0,menuService.getMenuById(menu.getIdMenu()));
    }

    @Test
    public void getAllMenu_should_return_2_when_inputTwoMenu() {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory());
        Menu menu2 = new Menu("Ayam Bakar", new BigDecimal(40000), true, newMenuCategory.getIdMenuCategory());
        menuService.createMenu(menu1);
        menuService.createMenu(menu2);
        assertEquals(2, menuService.getAllMenu().size());
    }

    @Test
    public void createMenu_should_return_Menu_when_inputMenu() {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory() );
        menu = menuService.createMenu(menu);
        Menu expected = menuRepository.findById(menu.getIdMenu()).get();
        assertEquals(expected, menu);
    }
//
//    @Test
//    public void createMenuWithImage_should_return_menu_and_image_ExistOnServer() {
//
//    }

    @Test
    public void deleteMenuById_should_return_1_when_1of2_data_deleted() {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory());
        Menu menu2 = new Menu("Ayam Bakar", new BigDecimal(40000), true, newMenuCategory.getIdMenuCategory());
        menu1 = menuService.createMenu(menu1);
        menuService.createMenu(menu2);
        menuService.deleteMenuById(menu1.getIdMenu());
        assertEquals(1, menuService.getAllMenu().size());
    }

    @Test
    public void updateMenu_should_return_new_Menu_when_Data_Updated() {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory() );
        menu = menuService.createMenu(menu);
        Menu edited = menu;
        edited.setMenuName("Ayam Bakar");
        menuService.updateMenu(edited);
        assertEquals(edited, menuRepository.findById(menu.getIdMenu()).get());
    }
}