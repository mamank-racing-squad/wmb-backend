package com.enigma.services.implement;

import com.enigma.entities.DiningTable;
import com.enigma.exceptions.*;
import com.enigma.repositories.DiningTableRepository;
import com.enigma.services.DiningTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiningTableServiceImpl implements DiningTableService {

    @Autowired
    DiningTableRepository diningTableRepository;

    @Override
    public DiningTable saveDiningTable(DiningTable diningTable) {
        validatingNumberDiningTableEmpty(diningTable.getNumberDiningTable());
        validatingNumberDiningTableIsExist(diningTable.getNumberDiningTable());
        validatingCapacityIsLessThanOne(diningTable.getCapacity());
        diningTable.setAvailability(true);
        return diningTableRepository.save(diningTable);
    }

    @Override
    public DiningTable getDiningTableById(String id) {
        if (!(diningTableRepository.findById(id).isPresent())) throw new NotFoundException("Number Dining Table with id : " + id + " is not found.");
        return diningTableRepository.findById(id).get();
    }

    @Override
    public List<DiningTable> getAllDiningTable() {
        return diningTableRepository.findAll();
    }

    @Override
    public Page<DiningTable> getAllDiningTableWithPagination(Pageable pageable) {
        return diningTableRepository.findAll(pageable);
    }

    @Override
    public DiningTable updateDiningTable(DiningTable diningTable) {
        validatingNumberDiningTableEmpty(diningTable.getNumberDiningTable());
        validatingCapacityIsLessThanOne(diningTable.getCapacity());
        getDiningTableById(diningTable.getIdDiningTable());
        return diningTableRepository.save(diningTable);
    }

    @Override
    public void deleteDiningTableById(String id) {
        getDiningTableById(id);
        diningTableRepository.deleteById(id);
    }

    @Override
    public void costumerDining(Integer totalCostumer, DiningTable diningTable) {
        if(totalCostumer<=diningTable.getCapacity()){
            if(diningTable.getAvailability()){
                diningTable.costumerEntry();
            }else{
                throw new ForbiddenException("Sorry, the Table is not Empty");
            }
        }else {
            throw new ForbiddenException();
        }
    }

    private void validatingNumberDiningTableEmpty(String value) {
        if (value.isEmpty()) throw new BadRequestException("Number dining table can't be empty");
    }

    private void validatingNumberDiningTableIsExist(String value) {
        if (diningTableRepository.existsByNumberDiningTable(value)) throw new BadRequestException("Number dining table with number : " + value + " is already exists");
    }

    private void validatingCapacityIsLessThanOne(Integer value) {
        if (value < 1) throw new BadRequestException("Capacity can't be less then one");
    }
}
