package com.example.demo.order;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;
import com.example.demo.store.Store;
@Repository
public interface CafeorderDao extends JpaRepository<Cafeorder, Integer> {
	ArrayList<Cafeorder> findByOid(Member oid);
	ArrayList<Cafeorder> findByOstateAndStore(int ostate, Store store);
}
