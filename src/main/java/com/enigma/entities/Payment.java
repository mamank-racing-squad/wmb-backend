package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@NoArgsConstructor @Setter @Getter
public class Payment {
    private BigDecimal pay;

    public Payment(BigDecimal pay) {
        this.pay = pay;
    }
}
