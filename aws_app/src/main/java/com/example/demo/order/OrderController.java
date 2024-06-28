package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/order")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@PostMapping("/add")
	public String add(CafeorderDto co) {
		System.out.println("order controller:" + co);
		service.addOrder(co);
		return "redirect:/index_consumer";
	}
	
	@GetMapping("/ostate")
	public String ostate(int ostate, String storeid,ModelMap map) {
		map.addAttribute("list", service.getOrdersByOstate(ostate, storeid));
		map.addAttribute("ostate", ostate);
		return "cafe/olist";
	}
	
	@GetMapping("/editOstate")
	public String editOstate(int num, int ostate) {
		service.editOstate(num, ostate);
		return "redirect:/index_seller";
	}
	
	@GetMapping("/mylist")
	public String mylist(String id, ModelMap map) {
		map.addAttribute("list", service.getOrdersById(id));
		return "order/list";
	}
}
