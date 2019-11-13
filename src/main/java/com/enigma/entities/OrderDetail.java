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
    private Menu menu;

    @Transient
    private String idMenu;

    private Integer amount;
    private String description;
    private BigDecimal subTotalPrice;

    public OrderDetail(String idMenu, Integer amount, String description) {
        this.idMenu = idMenu;
        this.amount = amount;
        this.description = description;
    }

    public String getIdMenu() {
        if (getMenu() != null) setIdMenu(getMenu().getIdMenu());
        return idMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(idOrderDetail, that.idOrderDetail) &&
                Objects.equals(idOrder, that.idOrder) &&
                Objects.equals(menu, that.menu) &&
                Objects.equals(idMenu, that.idMenu) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description) &&
                subTotalPrice.compareTo(that.getSubTotalPrice())==0;
    }

}
