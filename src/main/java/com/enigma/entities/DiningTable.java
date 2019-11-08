package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

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

    @UniqueElements
    @Column(unique = true)
    private String numberTable;
    private Integer capacity;
    private Boolean availability;

    public DiningTable(Integer capacity, Boolean availability) {
        this.capacity = capacity;
        this.availability = availability;
    }

    public void costumerEntry(){
        this.availability = false;
    }

    public void costumerOut(){
        this.availability = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiningTable that = (DiningTable) o;
        return Objects.equals(idDiningTable, that.idDiningTable) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(availability, that.availability);
    }
}
