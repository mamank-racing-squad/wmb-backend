package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "mst_menu")
@NoArgsConstructor @Getter @Setter
public class Menu {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idMenu;

    private String menuName;
    private BigDecimal price;
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "id_menu_category")
    private MenuCategory menuCategory;

    @Transient
    private String idMenuCategoryTransient;

    public Menu(String menuName, BigDecimal price, Boolean availability, String idMenuCategoryTransient) {
        this.menuName = menuName;
        this.price = price;
        this.availability = availability;
        this.idMenuCategoryTransient = idMenuCategoryTransient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(idMenu, menu.idMenu) &&
                Objects.equals(menuName, menu.menuName) &&
                price.compareTo(menu.getPrice())==0 &&
                Objects.equals(availability, menu.availability);
    }

}
