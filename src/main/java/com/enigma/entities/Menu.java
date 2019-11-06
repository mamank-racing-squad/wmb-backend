package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

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
}
