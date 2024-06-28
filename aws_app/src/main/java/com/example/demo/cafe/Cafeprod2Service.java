package com.example.demo.cafe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.store.Store;

@Service
public class Cafeprod2Service {
	@Autowired
	private Cafeprod2Dao dao;

	public Cafeprod2Dto save(Cafeprod2Dto dto) {
		Cafeprod2 cp = dao
				.save(new Cafeprod2(dto.getNum(), dto.getName(), dto.getPrice(), dto.getStore(), dto.getFname()));
		return new Cafeprod2Dto(cp.getNum(), cp.getName(), cp.getPrice(), cp.getStore(), cp.getFname(), null);
	}

	public Cafeprod2Dto getProd(int num) {
		Cafeprod2 cp = dao.findById(num).orElse(null);
		if (cp == null) {
			return null;
		}
		return new Cafeprod2Dto(cp.getNum(), cp.getName(), cp.getPrice(), cp.getStore(), cp.getFname(), null);
	}

	public ArrayList<Cafeprod2Dto> getbyStore(String store) {
		List<Cafeprod2> l = dao.findByStore(new Store(store, null, "", false));
		ArrayList<Cafeprod2Dto> list = new ArrayList<Cafeprod2Dto>();
		for (Cafeprod2 cp : l) {
			list.add(new Cafeprod2Dto(cp.getNum(), cp.getName(), cp.getPrice(), cp.getStore(), cp.getFname(), null));
		}
		return list;
	}
	
	public void delProd(int num) {
		dao.deleteById(num);
	}
}
