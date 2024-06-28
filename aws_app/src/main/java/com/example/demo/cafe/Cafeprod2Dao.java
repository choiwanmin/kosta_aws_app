package com.example.demo.cafe;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.store.Store;

@Repository
public interface Cafeprod2Dao extends JpaRepository<Cafeprod2, Integer> {
	ArrayList<Cafeprod2> findByStore(Store store);
}