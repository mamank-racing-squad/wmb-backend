package com.enigma;

import java.math.BigDecimal;

public class main {
    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal(90092);
        BigDecimal num2 = new BigDecimal(1000000);

        System.out.println(num1.compareTo(num2)<0);

    }
}
