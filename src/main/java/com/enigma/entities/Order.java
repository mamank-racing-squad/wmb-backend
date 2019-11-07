package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trx_order")
@NoArgsConstructor @Getter @Setter
public class Order {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idOrder;

    private String costumerName;
    private Integer totalCostumer;
    private BigDecimal totalPrice;
    private LocalDateTime createAt;

    private BigDecimal payment;
    private BigDecimal change;

    @ManyToOne
    @JoinColumn(name = "id_dining_table")
    private DiningTable diningTable;

    @OneToMany(mappedBy = "idOrder", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Transient
    private String idDiningTable;
}
