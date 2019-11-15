package com.enigma.controllers;

import com.enigma.entities.MenuCategory;
import com.enigma.repositories.MenuCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MenuCategoryControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MenuCategoryRepository menuCategoryRepository;

    private static MenuCategory sample1 = new MenuCategory("Drinks");
    private static MenuCategory sample2 = new MenuCategory("Foods");

    @Test
    void saveMenuCategory_should_return_OKStatus() throws Exception {
        MenuCategory expected = sample1;
        mockMvc.perform(put("/menu-category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected)))
                .andExpect(status().isOk());
    }

    @Test
    void saveMenuCategory_shouldExistInDb() throws Exception {
        String response = mockMvc.perform(put("/menu-category")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sample2))).andReturn().getResponse().getContentAsString();

        MenuCategory expected = new ObjectMapper().readValue(response, MenuCategory.class);

        assertTrue(menuCategoryRepository.findById(expected.getIdMenuCategory()).isPresent());

    }

    @Test
    public void getMenuCategoryById_Should_ReturnOkStatus() throws Exception {
        menuCategoryRepository.save(sample1);
        mockMvc.perform(get("/menu-category/{id}", sample1.getIdMenuCategory())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMenuCategory").value(sample1.getIdMenuCategory()));
    }

    @Test
    void deleteMenuCategoryById() {
    }

    @Test
    void updateMenuCategory() {
    }
}