package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String nameMenu;
    private BigDecimal price;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_menu_category")
    @JsonIgnore
    private MenuCategory menuCategory;

    @Transient
    private String idMenuCategoryTransient;

    public Menu(String nameMenu, BigDecimal price, Boolean status) {
        this.nameMenu = nameMenu;
        this.price = price;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(idMenu, menu.idMenu) &&
                Objects.equals(nameMenu, menu.nameMenu) &&
                price.compareTo(menu.getPrice())==0 &&
                Objects.equals(status, menu.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, nameMenu, price, status);
    }
}
