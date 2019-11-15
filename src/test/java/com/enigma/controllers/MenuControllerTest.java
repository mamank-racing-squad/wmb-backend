package com.enigma.controllers;

import com.enigma.entities.Menu;
import com.enigma.entities.MenuCategory;
import com.enigma.repositories.MenuCategoryRepository;
import com.enigma.repositories.MenuRepository;
import com.enigma.services.MenuCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MenuCategoryService menuCategoryService;

    @Autowired
    MenuRepository menuRepository;

    MenuCategory menuCategory = new MenuCategory("Foods");

    @Test
    void saveMenu_should_return_OKStatus() throws Exception {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu sample1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory() );

        mockMvc.perform(put("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample1)))
                .andExpect(status().isOk());
    }

    @Test
    void  saveMenu_shouldExistInDb() throws Exception {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu sample1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory() );
        String response = mockMvc.perform(put("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample1))).andReturn().getResponse().getContentAsString();

        Menu expected = new ObjectMapper().readValue(response, Menu.class);

        assertTrue(menuRepository.findById(expected.getIdMenu()).isPresent());
    }

    @Test
    void getMenuCategoryById_Should_ReturnOkStatus() throws Exception {
        MenuCategory newMenuCategory = menuCategoryService.createMenuCategory(menuCategory);
        Menu sample1 = new Menu("Ikan Bakar", new BigDecimal(50000), true, newMenuCategory.getIdMenuCategory() );
        menuRepository.save(sample1);

        mockMvc.perform(get("/menu/{id}", sample1.getIdMenu())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMenu").value(sample1.getIdMenu()));
    }

    @Test
    void getAllMenu() {
    }

    @Test
    void deleteMenu() {
    }

    @Test
    void updateMenu() {
    }
}