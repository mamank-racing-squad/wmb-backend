package com.enigma.services.implement;

import com.enigma.entities.DiningTable;
import com.enigma.repositories.DiningTableRepository;
import com.enigma.services.DiningTableService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiningTableServiceImplTest {

    @Autowired
    DiningTableRepository diningTableRepository;

    @Autowired
    DiningTableService diningTableService;

    @Before
    public void cleanUp(){
        diningTableRepository.deleteAll();
    }

    private static Pageable pageable = PageRequest.of(0, 10);


    // Happy Test save dining table :)

    private static DiningTable sample1 = new DiningTable(2, true);
    private static DiningTable sample2 = new DiningTable(4, false);

    @Test
    public void saveDiningTable() {
        DiningTable expected = diningTableService.saveDiningTable(sample1);
        DiningTable result = diningTableRepository.findById(expected.getIdDiningTable()).get();

        assertEquals(expected, result);
    }

    @Test
    public void getDiningTableById() {
        DiningTable sampleDiningTable = diningTableService.saveDiningTable(sample1);

        DiningTable expected = diningTableService.getDiningTableById(sampleDiningTable.getIdDiningTable());
        DiningTable result = diningTableRepository.findById(sampleDiningTable.getIdDiningTable()).get();

        assertEquals(expected, result);

    }

    @Test
    public void getAllDiningTable() {
        diningTableService.saveDiningTable(sample1);
        diningTableService.saveDiningTable(sample2);

        List<DiningTable> expected = diningTableService.getAllDiningTable();
        List<DiningTable> result = diningTableRepository.findAll();

        assertEquals(expected, result);
    }

    @Test
    public void getAllDiningTableWithPagination() {
        diningTableService.saveDiningTable(sample1);
        diningTableService.saveDiningTable(sample2);

        Page<DiningTable> expected = diningTableService.getAllDiningTableWithPagination(pageable);
        Page<DiningTable> result = diningTableRepository.findAll(pageable);

        assertEquals(expected, result);
    }

    @Test
    public void updateDiningTable() {
        DiningTable sampleDiningTable = diningTableService.saveDiningTable(sample1);

        sampleDiningTable.setCapacity(4);
        sampleDiningTable.setStatus(false);

        DiningTable expected = diningTableService.updateDiningTable(sampleDiningTable);
        DiningTable result = diningTableRepository.findById(sampleDiningTable.getIdDiningTable()).get();

        assertEquals(expected, result);
    }

    @Test
    public void deleteDiningTableById() {
        DiningTable sampleDiningTable = diningTableService.saveDiningTable(sample1);

        diningTableService.deleteDiningTableById(sampleDiningTable.getIdDiningTable());

        assertFalse(diningTableRepository.findById(sampleDiningTable.getIdDiningTable()).isPresent());
    }
}