package com.enigma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "trx_order_detail")
@NoArgsConstructor @Getter @Setter
public class OrderDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idOrderDetail;

    @ManyToOne
    @JoinColumn(name = "id_order")
    @JsonIgnore
    private Order idOrder;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu idMenu;

    @Transient
    private String idMenuTransient;

    private Integer amount;
    private String description;
    private BigDecimal subTotalPrice;

    public OrderDetail(String idMenuTransient, Integer amount, String description) {
        this.idMenuTransient = idMenuTransient;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(idOrderDetail, that.idOrderDetail) &&
                Objects.equals(idOrder, that.idOrder) &&
                Objects.equals(idMenu, that.idMenu) &&
                Objects.equals(idMenuTransient, that.idMenuTransient) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description) &&
                subTotalPrice.compareTo(that.getSubTotalPrice())==0;
    }

}
