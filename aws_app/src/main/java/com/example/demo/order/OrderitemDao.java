package com.example.demo.order;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderitemDao extends JpaRepository<Orderitem, Integer> {
	ArrayList<Orderitem> findByParent(Cafeorder order);
}
