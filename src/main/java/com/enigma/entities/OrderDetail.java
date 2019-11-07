package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "trx_order_detail")
@NoArgsConstructor @Getter @Setter
public class OrderDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id_order_detail;
    private String id_purchase;
    private String id_food;
    private Integer quantity;
    private String description;
    private BigDecimal sub_total_price;

    public OrderDetail(String id_purchase, String id_food, Integer quantity, String description, BigDecimal sub_total_price) {
        this.id_purchase = id_purchase;
        this.id_food = id_food;
        this.quantity = quantity;
        this.description = description;
        this.sub_total_price = sub_total_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(id_order_detail, that.id_order_detail) &&
                Objects.equals(id_purchase, that.id_purchase) &&
                Objects.equals(id_food, that.id_food) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(description, that.description) &&
                Objects.equals(sub_total_price, that.sub_total_price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_order_detail, id_purchase, id_food, quantity, description, sub_total_price);
    }
}
