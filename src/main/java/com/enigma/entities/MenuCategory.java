package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_menu_category")
@NoArgsConstructor
@Getter
@Setter
public class MenuCategory {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idMenuCategory;
    private String categoryName;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Menu> menuList = new ArrayList<>();

    public MenuCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuCategory that = (MenuCategory) o;
        return Objects.equals(idMenuCategory, that.idMenuCategory) &&
                Objects.equals(categoryName, that.categoryName);
    }
}
