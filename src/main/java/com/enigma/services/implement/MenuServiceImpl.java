package com.enigma.services.implement;

import com.enigma.entities.Menu;
import com.enigma.exceptions.BadRequestException;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.MenuRepository;
import com.enigma.services.FileService;
import com.enigma.services.MenuCategoryService;
import com.enigma.services.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuCategoryService menuCategoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileService fileService;

    @Override
    public Menu getMenuById(String id) {
        if (!(menuRepository.findById(id).isPresent())) throw new NotFoundException("Menu with id : " + id + " is not found.");
        return menuRepository.findById(id).get();
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu createMenuWithImage(String menuInput, MultipartFile image) throws IOException {
        Menu menu = objectMapper.readValue(menuInput, Menu.class);
        validatingMenuNameIsExist(menu.getMenuName());
        validatingMenuNameEmpty(menu.getMenuName());
        validatingPriceEmpty(menu.getPrice());
        validatingAvailabilityEmpty(menu.getIsAvailable());
        validatingMenuCategoryEmpty(menu.getIdMenuCategory());
        menu.setMenuCategory(menuCategoryService.getMenuCategoryById(menu.getIdMenuCategory()));
        fileService.saveFile(image, menu.getIdMenu());
        return menuRepository.save(menu);
    }

    @Override
    public void deleteMenuById(String id) {
        getMenuById(id);
        menuRepository.deleteById(id);
    }

    @Override
    public Menu updateMenuWithImage(String menuInput, MultipartFile image) throws IOException {
        Menu menu = menuRepository.save(objectMapper.readValue(menuInput, Menu.class));
        validatingMenuNameEmpty(menu.getMenuName());
        validatingPriceEmpty(menu.getPrice());
        validatingAvailabilityEmpty(menu.getIsAvailable());
        validatingMenuCategoryEmpty(menu.getIdMenuCategory());
        fileService.saveFile(image, menu.getIdMenu());
        return menu;
    }

    private void validatingMenuNameIsExist(String value) {
        if (menuRepository.existsByMenuNameIsLike(value)) throw new BadRequestException("Menu name with name : " + value + " already exist");
    }

    private void validatingMenuNameEmpty(String value) {
        if (value.isEmpty()) throw new BadRequestException("Menu name can't be empty");
    }

    private void validatingPriceEmpty(BigDecimal value) {
        if (value == null) throw new BadRequestException("Menu price can't be empty");
    }

    private void validatingAvailabilityEmpty(Boolean value) {
        if (value == null) throw new BadRequestException("Menu availability can't be empty");
    }

    private void validatingMenuCategoryEmpty(String value) {
        if (value.isEmpty()) throw new BadRequestException("Menu Category can't be empty");
    }
}
