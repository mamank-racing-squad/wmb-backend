package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mst_dining_table")
@NoArgsConstructor @Getter @Setter
public class DiningTable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idDiningTable;

    private String numberDiningTable;
    private Integer capacity;
    private Boolean isAvailable;

    public DiningTable(String numberDiningTable, Integer capacity) {
        this.numberDiningTable = numberDiningTable;
        this.capacity = capacity;
    }

    public void costumerEntry(){
        this.isAvailable = false;
    }

    public void costumerOut(){
        this.isAvailable = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiningTable that = (DiningTable) o;
        return Objects.equals(idDiningTable, that.idDiningTable) &&
                Objects.equals(numberDiningTable, that.numberDiningTable) &&
                Objects.equals(capacity, that.capacity);
    }

}
