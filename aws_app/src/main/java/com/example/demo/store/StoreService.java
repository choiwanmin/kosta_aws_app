package com.example.demo.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class StoreService {
	@Autowired
	private StoreDao dao;

	// 매장추가/주소수정
	public void save(StoreDto dto) {
		dao.save(new Store(dto.getStoreid(), dto.getSid(), dto.getAddress(), dto.isOpen()));
	}

	// 매장목록
	public ArrayList<StoreDto> getAll() {
		List<Store> l = dao.findAll();
		ArrayList<StoreDto> list = new ArrayList<StoreDto>();
		for (Store s : l) {
			list.add(new StoreDto(s.getStoreid(), s.getSid(), s.getAddress(), s.isOpen()));
		}
		return list;
	}

	// 매장id로 검색
	public StoreDto getStore(String storeid) {
		Store s = dao.findById(storeid).orElse(null);
		if (s == null) {
			return null;
		}
		return new StoreDto(s.getStoreid(), s.getSid(), s.getAddress(), s.isOpen());
	}

	// 점주로 매장검색
	public StoreDto getStoreBySeller(Member id) {
		Store s = dao.findBySid(id);
		if (s == null) {
			return null;
		}
		return new StoreDto(s.getStoreid(), s.getSid(), s.getAddress(), s.isOpen());
	}

	// 매장삭제
	public void delStore(String storeid) {
		dao.deleteById(storeid);
	}
	
	//
	public ArrayList<StoreDto> getByOpen(boolean flag) {
		ArrayList<Store> l = dao.findByOpen(flag);
		ArrayList<StoreDto> list = new ArrayList<>();
		for(Store s : l) {
			list.add(new StoreDto(s.getStoreid(), s.getSid(), s.getAddress(), s.isOpen()));
		}
		return list;
	}
}
