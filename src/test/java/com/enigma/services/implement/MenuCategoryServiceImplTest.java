package com.enigma.services.implement;

import com.enigma.entities.MenuCategory;
import com.enigma.exceptions.BadRequestException;
import com.enigma.repositories.MenuCategoryRepository;
import com.enigma.repositories.MenuRepository;
import com.enigma.services.MenuCategoryService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MenuCategoryServiceImplTest {
    @Autowired
    MenuCategoryRepository menuCategoryRepository;

    @Autowired
    MenuCategoryService menuCategoryService;
    //sementara
    @Autowired
    MenuRepository menuRepository;
    @Before
    public void cleanUp(){
        menuRepository.deleteAll();
        menuCategoryRepository.deleteAll();

    }
    @Test
    void getAllMenuCategory() {
        MenuCategory menuCategory1 = new MenuCategory("Drinks");
        MenuCategory menuCategory2 = new MenuCategory("Foods");
        menuCategoryRepository.save(menuCategory1);
        menuCategoryRepository.save(menuCategory2);
        Assertions.assertEquals(2, menuCategoryService.getAllMenuCategory().size());
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
    public void createMenuCategory_should_return_400_when_dataInputEmpty() {
        MenuCategory menuCategory = new MenuCategory("");
        assertThrows(BadRequestException.class, () -> {menuCategoryService.createMenuCategory(menuCategory);});
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