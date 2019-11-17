package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "trx_payment")
@NoArgsConstructor @Setter @Getter
public class Payment {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idPayment;
    private BigDecimal pay;
    private BigDecimal change;
    private LocalDateTime paidAt;

    @Transient
    private String idOrder;

    @OneToOne
    private Order order;

    public Payment(BigDecimal pay, BigDecimal change, String idOrder) {
        this.pay = pay;
        this.change = change;
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(idPayment, payment.idPayment) &&
                pay.compareTo(payment.getPay())==0 &&
                pay.compareTo(payment.getChange())==0 &&
                Objects.equals(idOrder, payment.idOrder);
    }

}
