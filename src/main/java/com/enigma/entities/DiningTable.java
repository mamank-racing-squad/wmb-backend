package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mst_dining_table")
@NoArgsConstructor @Getter @Setter
public class DiningTable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idDiningTable;
    private Integer capacity;
    private Boolean status;

    public DiningTable(Integer capacity, Boolean status) {
        this.capacity = capacity;
        this.status = status;
    }

    public void costumerEntry(){
        this.status = false;
    }

    public void costumerOut(){
        this.status = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiningTable that = (DiningTable) o;
        return Objects.equals(idDiningTable, that.idDiningTable) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(status, that.status);
    }
}
