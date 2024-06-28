package com.example.demo.store;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;
@Repository
public interface StoreDao extends JpaRepository<Store, String> {
	//점주로 매장 검색
	Store findBySid(Member m);
	ArrayList<Store> findByOpen(boolean open);
}
