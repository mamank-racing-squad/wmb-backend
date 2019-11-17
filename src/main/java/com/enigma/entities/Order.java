package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trx_order")
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idOrder;

    private String costumerName;
    private Integer totalCostumer;
    private BigDecimal totalPrice;
    private LocalDateTime createAt;
    private String description;

    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "id_dining_table")
    private DiningTable diningTable;

    @OneToMany(mappedBy = "idOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Transient
    private String idDiningTable;

    public Order(String costumerName, Integer totalCostumer, LocalDateTime createAt, List<OrderDetail> orderDetails, String idDiningTable, String description, Boolean isPaid) {
        this.costumerName = costumerName;
        this.totalCostumer = totalCostumer;
        this.createAt = createAt;
        this.orderDetails = orderDetails;
        this.idDiningTable = idDiningTable;
        this.description = description;
        this.isPaid = isPaid;
    }

    public String getIdDiningTable() {
        if (this.getDiningTable() != null) setIdDiningTable(getDiningTable().getIdDiningTable());
        return idDiningTable;
    }

    public void dining() {
        this.isPaid = false;
    }

    public void paid() {
        this.isPaid = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder) &&
                Objects.equals(costumerName, order.costumerName) &&
                Objects.equals(totalCostumer, order.totalCostumer) &&
                totalPrice.compareTo(order.getTotalPrice()) == 0 &&
                Objects.equals(createAt, order.createAt) &&
                Objects.equals(description, order.description);
    }


}
