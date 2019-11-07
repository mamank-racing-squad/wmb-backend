package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "trx_order")
@NoArgsConstructor @Getter @Setter
public class Order {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idOrder;

    private String costumerName;
    private String totalCostumer;
    private BigDecimal totalPrice;
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "id_dining_table")
    private DiningTable diningTable;

    @Transient
    private String idDiningTable;
}
