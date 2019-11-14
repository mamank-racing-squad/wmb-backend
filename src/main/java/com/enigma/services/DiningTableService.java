package com.enigma.services;

import com.enigma.entities.DiningTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiningTableService {
    DiningTable createDiningTable(DiningTable diningTable);
    DiningTable getDiningTableById(String id);
    List<DiningTable> getAllDiningTable();
    Page<DiningTable> getAllDiningTableWithPagination(Pageable pageable);
    DiningTable updateDiningTable(DiningTable diningTable);
    void deleteDiningTableById(String id);
    void costumerDining(Integer totalCostumer, DiningTable diningTable);
}
