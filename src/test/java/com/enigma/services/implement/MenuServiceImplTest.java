package com.enigma.services.implement;

import com.enigma.entities.Menu;
import com.enigma.entities.MenuCategory;
import com.enigma.exceptions.BadRequestException;
import com.enigma.exceptions.ForbiddenException;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.MenuCategoryRepository;
import com.enigma.repositories.MenuRepository;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    @Autowired
    MenuCategoryRepository menuCategoryRepository;

    @Before
    public void cleanUp(){
        menuRepository.deleteAll();
        menuCategoryRepository.deleteAll();
    }
    @After
    public void cleanUpAgain(){
        menuRepository.deleteAll();
        menuCategoryRepository.deleteAll();
    }

    MenuCategory menuCategory = new MenuCategory("Food");

    @Test
    public void getMenuById_should_return_Menu_when_Found() {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), menuCategory.getIdMenuCategory() );
        menu = menuRepository.save(menu);
        Menu actual = menuService.getMenuById(menu.getIdMenu());
        Menu expected = menuRepository.findById(menu.getIdMenu()).get();
        assertEquals(expected, actual);
    }
    @Test
    public void getMenuById_should_return_NotFoundException_when_not_found() {
        Assertions.assertThrows(NotFoundException.class, () -> menuService.getMenuById("1dy4n9t1d4ck4d4"));
    }

    @Test
    public void getMenuById_should_return_notnull_when_Found(){
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), menuCategory.getIdMenuCategory() );
        menu = menuRepository.save(menu);
        assertNotEquals(0,menuService.getMenuById(menu.getIdMenu()));
    }

    @Test
    public void getAllMenu_should_return_2_when_inputTwoMenu() {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), menuCategory.getIdMenuCategory());
        Menu menu2 = new Menu("Ayam Bakar", new BigDecimal(40000), menuCategory.getIdMenuCategory());
        menuRepository.save(menu1);
        menuRepository.save(menu2);
        assertEquals(2, menuService.getAllMenu().size());
    }


    @Test
    public void createMenuWithImage_should_return_menu_and_image_ExistOnServer() throws IOException {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        File file = new File("E:\\mini-project-enigma-2019\\wmb-backend\\src\\test\\java\\com\\enigma\\services\\implement\\soto.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile image = new MockMultipartFile("image", input);
        Menu menu = new Menu("ikan asin", new BigDecimal(50000), menuCategory.getIdMenuCategory());
        ObjectMapper objectMapper = new ObjectMapper();
        String menuInput = objectMapper.writeValueAsString(menu);
        Menu menuWithImage = menuService.createMenuWithImage(menuInput, image);
        FileReader fileReader = new FileReader("C:/nginx-1.16.1/html/menu-image/"+menuWithImage.getIdMenu()+".jpg");
        assertEquals(menuWithImage, menuRepository.findById(menuWithImage.getIdMenu()).get());
        assertFalse(fileReader.getEncoding().isEmpty());
    }

    @Test
    public void createMenu_should_return_Menu_when_inputMenu() {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), newMenuCategory.getIdMenuCategory() );
        menu = menuService.createMenu(menu);
        Menu expected = menuRepository.findById(menu.getIdMenu()).get();
        assertEquals(expected, menu);
    }

    @Test
    public void updateMenu_should_return_new_Menu_when_Data_Updated() {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu = new Menu("Ikan Bakar", new BigDecimal(50000), newMenuCategory.getIdMenuCategory() );
        menu = menuService.createMenu(menu);
        Menu edited = menu;
        edited.setMenuName("Ayam Bakar");
        menuService.updateMenu(edited);
        assertEquals(edited, menuRepository.findById(menu.getIdMenu()).get());
    }

    @Test
    public void updateMenuWithImage_should_return_menu_and_image_ExistOnServer() throws IOException {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        File file = new File("E:\\mini-project-enigma-2019\\wmb-backend\\src\\test\\java\\com\\enigma\\services\\implement\\soto.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile image = new MockMultipartFile("image", input);
        Menu menu = new Menu("ikan asin", new BigDecimal(50000), menuCategory.getIdMenuCategory());
        ObjectMapper objectMapper = new ObjectMapper();
        String menuInput = objectMapper.writeValueAsString(menu);
        Menu menuWithImage = menuService.createMenuWithImage(menuInput, image);
        menuWithImage.setMenuName("martabak");
        String newMenuInput = objectMapper.writeValueAsString(menuWithImage);
        Menu newMenu = menuService.updateMenuWithImage(newMenuInput, image);
        FileReader fileReader = new FileReader("C:/nginx-1.16.1/html/menu-image/"+menuWithImage.getIdMenu()+".jpg");
        assertEquals(newMenu, menuRepository.findById(menuWithImage.getIdMenu()).get());
        assertFalse(fileReader.getEncoding().isEmpty());
    }

    @Test
    public void deleteMenuById_should_return_1_when_1of2_data_deleted() {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), menuCategory.getIdMenuCategory());
        Menu menu2 = new Menu("Ayam Bakar", new BigDecimal(40000), menuCategory.getIdMenuCategory());
        menu1 = menuRepository.save(menu1);
        menuRepository.save(menu2);
        menuService.deleteMenuById(menu1.getIdMenu());
        assertEquals(1, menuService.getAllMenu().size());
    }
    @Test
    public void createMenuWithImage_should_return_BadRequestException_when_Input_sameMenuName() throws IOException {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("Ikan Bakar", new BigDecimal(50000), menuCategory.getIdMenuCategory());
        menuRepository.save(menu1);
        ObjectMapper objectMapper = new ObjectMapper();
        String menuInput = objectMapper.writeValueAsString(menu1);
        File file = new File("E:\\mini-project-enigma-2019\\wmb-backend\\src\\test\\java\\com\\enigma\\services\\implement\\soto.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile image = new MockMultipartFile("image", input);
        Assertions.assertThrows(BadRequestException.class, () -> {menuService.createMenuWithImage(menuInput, image);});
    }
    @Test
    public void createMenuWithImage_should_return_BadRequestException_when_menuName_Empty() throws IOException {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("", new BigDecimal(50000), menuCategory.getIdMenuCategory());
        ObjectMapper objectMapper = new ObjectMapper();
        String menuInput = objectMapper.writeValueAsString(menu1);
        File file = new File("E:\\mini-project-enigma-2019\\wmb-backend\\src\\test\\java\\com\\enigma\\services\\implement\\soto.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile image = new MockMultipartFile("image", input);
        Assertions.assertThrows(BadRequestException.class, () -> {menuService.createMenuWithImage(menuInput, image);});

    }
    @Test
    public void createMenuWithImage_should_return_ForbiddenException_when_price_Zero() throws IOException {
        menuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu menu1 = new Menu("ikan goreng",new BigDecimal(0) ,menuCategory.getIdMenuCategory());
        ObjectMapper objectMapper = new ObjectMapper();
        String menuInput = objectMapper.writeValueAsString(menu1);
        File file = new File("E:\\mini-project-enigma-2019\\wmb-backend\\src\\test\\java\\com\\enigma\\services\\implement\\soto.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile image = new MockMultipartFile("image", input);
        Assertions.assertThrows(ForbiddenException.class, () -> {menuService.createMenuWithImage(menuInput, image);});
    }


    @Test
    public void createMenuWithImage_should_return_BadRequestException_when_idMenuCategory_Empty() throws IOException {
        Menu menu1 = new Menu("apa yang digoreng", new BigDecimal(50000), "");
        ObjectMapper objectMapper = new ObjectMapper();
        String menuInput = objectMapper.writeValueAsString(menu1);
        File file = new File("E:\\mini-project-enigma-2019\\wmb-backend\\src\\test\\java\\com\\enigma\\services\\implement\\soto.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile image = new MockMultipartFile("image", input);
        Assertions.assertThrows(BadRequestException.class, () -> {menuService.createMenuWithImage(menuInput, image);});
    }


}