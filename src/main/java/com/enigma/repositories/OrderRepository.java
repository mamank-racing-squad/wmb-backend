package com.enigma.repositories;

import com.enigma.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByOrderByCreateAtDesc();
    List<Order> findByIsPaidFalse();
    @Query(nativeQuery = true, value = "SELECT sum(total_price) FROM trx_order WHERE is_paid = true")
    BigDecimal sumTotalIncome();


}
