package com.enigma.entities;

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
    private Order idOrder;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu idMenu;

    @Transient
    private String idMenuTransient;

    private Integer quantity;
    private String description;
    private BigDecimal subTotalPrice;

}
