package com.enigma.services.implement;

import com.enigma.entities.DiningTable;
import com.enigma.exceptions.InputCanNotBeEmptyException;
import com.enigma.exceptions.NotAccordingToCapacityException;
import com.enigma.exceptions.ResultNotFoundException;
import com.enigma.exceptions.TableIsNotEmptyException;
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
        validatingDiningTable(diningTable);
        diningTable.setAvailability(true);
        return diningTableRepository.save(diningTable);
    }

    private void validatingDiningTable(DiningTable diningTable) {
        if (diningTable.getNumberDiningTable().isEmpty()) throw new InputCanNotBeEmptyException("Number dining table can't be empty");
        if (diningTableRepository.existsByNumberDiningTable(diningTable.getNumberDiningTable())) throw new InputCanNotBeEmptyException("Number dining table with number : " + diningTable.getNumberDiningTable() + "already exists");
        else if (diningTable.getCapacity() < 1) throw new InputCanNotBeEmptyException("Capacity can't be less then one");
    }

    @Override
    public DiningTable getDiningTableById(String id) {
        if (!(diningTableRepository.findById(id).isPresent())) throw new ResultNotFoundException();
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
        getDiningTableById(diningTable.getIdDiningTable());
        validatingDiningTable(diningTable);
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
                throw new TableIsNotEmptyException();
            }
        }else {
            throw new NotAccordingToCapacityException();
        }
    }
}
