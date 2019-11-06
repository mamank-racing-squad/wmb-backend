package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_menu_category")
@NoArgsConstructor @Getter @Setter
public class MenuCategory {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idMenuCategory;
    private String categoryName;

    @OneToMany(mappedBy = "menuCategory")
    List<Menu> menuList = new ArrayList<>();

}
