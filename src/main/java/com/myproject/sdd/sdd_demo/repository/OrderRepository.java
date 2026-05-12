package com.myproject.sdd.sdd_demo.repository;

import com.myproject.sdd.sdd_demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
