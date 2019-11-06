package com.enigma.controllers;

import com.enigma.entities.DiningTable;
import com.enigma.services.DiningTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DiningTableController {

    @Autowired
    DiningTableService diningTableService;

    @PostMapping("/dining-table")
    public DiningTable saveDiningTable(@RequestBody DiningTable diningTable){
        return diningTableService.saveDiningTable(diningTable);
    }

    @GetMapping("/dining-table/{id}")
    public DiningTable getDiningTableById(@PathVariable String id) {
        return diningTableService.getDiningTableById(id);
    }

    @GetMapping("/dining-tables")
    public List<DiningTable> getAllDiningTable() {
        return diningTableService.getAllDiningTable();
    }

    @GetMapping("/dining-table/pagination")
    public Page<DiningTable> getAllDiningTableWithPagination(@RequestParam Integer page, @RequestParam Integer size) {
        return diningTableService.getAllDiningTableWithPagination(PageRequest.of(page, size));
    }

    @PutMapping("/dining-table")
    public DiningTable updateDiningTable(@RequestBody  DiningTable diningTable) {
        return diningTableService.updateDiningTable(diningTable);
    }

    @DeleteMapping("/dining-table/{id}")
    public void deleteDiningTableById(@PathVariable String id) {
        diningTableService.deleteDiningTableById(id);
    }
}
