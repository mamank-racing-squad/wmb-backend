package com.enigma.controllers;

import com.enigma.entities.DiningTable;
import com.enigma.repositories.DiningTableRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiningTableControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DiningTableRepository diningTableRepository;


    private static Pageable pageable = PageRequest.of(0, 10);

    private static DiningTable sample1 = new DiningTable("A01",2);
    private static DiningTable sample2 = new DiningTable("A02", 4);

    // Happy Test save dining table :)

    @Test
    public void saveDiningTable_should_return_OKStatus() throws Exception {
        DiningTable expected = sample1;

        mockMvc.perform(put("/dining-table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected)))
                .andExpect(status().isOk());
    }

    @Test
    public void saveDiningTable_shouldExistInDb() throws Exception {
        String response = mockMvc.perform(put("/dining-table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample2))).andReturn().getResponse().getContentAsString();

        DiningTable expected = new ObjectMapper().readValue(response, DiningTable.class);

        assertTrue(diningTableRepository.findById(expected.getIdDiningTable()).isPresent());
    }

    @Test
    public void getDiningTableById_Should_ReturnOkStatus() throws Exception {

        diningTableRepository.save(sample1);

        mockMvc.perform(get("/dining-table/{id}", sample1.getIdDiningTable())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDiningTable").value(sample1.getIdDiningTable()));
    }

    @Test
    public void getDiningTableById_shouldExistInDb() throws Exception {

    }

    @Test
    public void getAllDiningTable() {
    }

    @Test
    public void getAllDiningTableWithPagination() {
    }

    @Test
    public void updateDiningTable() {
    }

    @Test
    public void deleteDiningTableById() {
    }
}