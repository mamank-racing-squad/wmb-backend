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
import java.util.Objects;

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

    @OneToMany(mappedBy = "idOrder", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Transient
    private String idDiningTable;

    public Order(String costumerName, Integer totalCostumer, LocalDateTime createAt, BigDecimal payment, BigDecimal change, List<OrderDetail> orderDetails, String idDiningTable) {
        this.costumerName = costumerName;
        this.totalCostumer = totalCostumer;
        this.createAt = createAt;
        this.payment = payment;
        this.change = change;
        this.orderDetails = orderDetails;
        this.idDiningTable = idDiningTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder) &&
                Objects.equals(costumerName, order.costumerName) &&
                Objects.equals(totalCostumer, order.totalCostumer) &&
                totalPrice.compareTo(order.getTotalPrice())==0 &&
                Objects.equals(createAt, order.createAt) &&
                payment.compareTo(order.getPayment())==0 &&
                change.compareTo(order.getChange())==0;
//                &&
//                Objects.equals(diningTable, order.diningTable) &&
//                Objects.equals(orderDetails, order.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, costumerName, totalCostumer, totalPrice, createAt, payment, change, diningTable, orderDetails, idDiningTable);
    }

}
