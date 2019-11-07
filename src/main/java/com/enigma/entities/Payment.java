package com.enigma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor @Setter @Getter
public class Payment {
    private BigDecimal pay;
    private BigDecimal change;
}
