package com.enigma.services.implement;

import com.enigma.entities.MenuCategory;
import com.enigma.repositories.MenuCategoryRepository;
import com.enigma.services.MenuCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuCategoryServiceImplTest {
    @Autowired
    MenuCategoryRepository menuCategoryRepository;

    @Autowired
    MenuCategoryService menuCategoryService;

    @Before
    public void cleanUp(){
        menuCategoryRepository.deleteAll();
    }

    @Test
    public void getAllMenuCategory_should_return_2_when_Input_2Data() {
        MenuCategory menuCategory1 = new MenuCategory("Drinks");
        MenuCategory menuCategory2 = new MenuCategory("Foods");
        menuCategoryRepository.save(menuCategory1);
        menuCategoryRepository.save(menuCategory2);
        assertEquals(2, menuCategoryService.getAllMenuCategory().size());
    }

    @Test
    public void getAllMenuCategory_should_return_true_when_Input_emptyData() {
        assertTrue(menuCategoryService.getAllMenuCategory().isEmpty());
    }

    @Test
    public void getAllMenuCategory_should_return_false_when_Input_DataExist() {
        MenuCategory menuCategory1 = new MenuCategory("Drinks");
        MenuCategory menuCategory2 = new MenuCategory("Foods");
        menuCategoryRepository.save(menuCategory1);
        menuCategoryRepository.save(menuCategory2);
        assertFalse(menuCategoryService.getAllMenuCategory().isEmpty());
    }


    @Test
    public void createMenuCategory_should_return_MenuCategory_when_dataInput() {
        MenuCategory menuCategory = new MenuCategory("Drinks");
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        assertEquals(menuCategoryRepository.findById(menuCategory.getIdMenuCategory()).get(), menuCategory);
    }

    @Test
    public void deleteMenuCategoryById() {
        MenuCategory menuCategory = new MenuCategory("Drinks");
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        menuCategoryService.deleteMenuCategoryById(menuCategory.getIdMenuCategory());
        assertEquals(0, menuCategoryRepository.findAll().size());
    }

    @Test
    public void updateMenuCategory() {
        MenuCategory menuCategory = new MenuCategory("Main Foods");
        menuCategory = menuCategoryRepository.save(menuCategory);
        MenuCategory edited = menuCategory;
        edited.setCategoryName("Drinks");
        menuCategoryService.updateMenuCategory(edited);
        assertEquals(edited, menuCategoryRepository.findById(menuCategory.getIdMenuCategory()).get());
    }
}