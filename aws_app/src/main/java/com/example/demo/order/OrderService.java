package com.example.demo.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.store.Store;

@Service
public class OrderService {
	@Autowired
	private CafeorderDao dao1;
	@Autowired
	private OrderitemDao dao2;

	// 주문
	public void addOrder(CafeorderDto dto) {
		Cafeorder cp = dao1
				.save(new Cafeorder(dto.getNum(), dto.getOid(), dto.getOdate(), dto.getPayment(), 1, dto.getStore()));
		for (OrderitemDto o : dto.getItems()) {
			dao2.save(new Orderitem(o.getNum(), o.getProd(), o.getAmount(), cp));
		}
	}

	// 주문항목목록 검색
	public ArrayList<OrderitemDto> getOrderItems(Cafeorder co) {
		List<Orderitem> l = dao2.findByParent(co);
		ArrayList<OrderitemDto> list = new ArrayList<>();
		for (Orderitem o : l) {
			list.add(new OrderitemDto(o.getNum(), o.getProd(), o.getAmount(), o.getParent()));
		}
		return list;
	}

	// 주문목록
	public ArrayList<CafeorderDto> getOrdersById(String id) {
		List<Cafeorder> l = dao1.findByOid(new Member(id, "", "", "", ""));
		ArrayList<CafeorderDto> list = new ArrayList<>();
		for (Cafeorder co : l) {
			CafeorderDto c = new CafeorderDto(co.getNum(), co.getOid(), co.getOdate(), co.getPayment(), co.getOstate(),
					co.getStore(), null);
			c.setItems(getOrderItems(co));
			list.add(c);
		}
		return list;
	}

	// 상태별검색
	public ArrayList<CafeorderDto> getOrdersByOstate(int ostate, String storeid) {
		List<Cafeorder> l = dao1.findByOstateAndStore(ostate, new Store(storeid, null, "", false));
		ArrayList<CafeorderDto> list = new ArrayList<>();
		for (Cafeorder co : l) {
			CafeorderDto c = new CafeorderDto(co.getNum(), co.getOid(), co.getOdate(), co.getPayment(), co.getOstate(),
					co.getStore(), null);
			c.setItems(getOrderItems(co));
			list.add(c);
		}
		return list;
	}

	// 번호로검색
	public CafeorderDto getOrder(int num) {
		Cafeorder o = dao1.findById(num).orElse(null);
		return new CafeorderDto(o.getNum(), o.getOid(), o.getOdate(), o.getPayment(), o.getOstate(), o.getStore(),
				getOrderItems(o));
	}

	// 주문상태변경
	public void editOstate(int num, int ostate) {
		Cafeorder o = dao1.findById(num).orElse(null);
		if (o != null) {
			o.setOstate(ostate);
			dao1.save(o);
		}
	}
}
