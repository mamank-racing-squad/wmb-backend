package com.enigma.entities;

import com.enigma.repositories.DiningTableRepository;
import com.enigma.services.DiningTableService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiningTableTest {

    @Autowired
    DiningTableService diningTableService;
    @Autowired
    DiningTableRepository diningTableRepository;
    @Before
    public void cleanUp(){
        diningTableRepository.deleteAll();
    }
    private static DiningTable diningTable1 = new DiningTable("B01", 2);

    @Test
    public void costumerEntry_should_make_isAvailable_toBe_false() {
        DiningTable diningTable = diningTableService.createDiningTable(diningTable1);
        diningTable.costumerEntry();
        assertFalse(diningTable.getIsAvailable());
    }

    @Test
    public void costumerOut_should_make_isAvailable_toBe_true() {
        DiningTable diningTable = diningTableService.createDiningTable(diningTable1);
        diningTable.costumerOut();
        assertTrue(diningTable.getIsAvailable());
    }
}