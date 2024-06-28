package com.example.demo.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.member.Member;

@Controller
@RequestMapping("/auth/store")
public class StoreController {
	@Autowired
	private StoreService service;

	@GetMapping("/add")
	public String addform() {
		return "store/add";
	}

	@PostMapping("/add")
	public String add(StoreDto dto) { // 매장id, 점주id, 주소, false
		service.save(dto); // 한줄 추가
		return "redirect:/index_seller";
	}

	@GetMapping("/open")
	public String open(String storeid) { // 매장id, 점주id, 주소, false
		StoreDto sdto = service.getStore(storeid);
		sdto.setOpen(true);
		service.save(sdto); // open을 true로 변경
		return "redirect:/index_admin";
	}

	@GetMapping("/list")
	public String list(ModelMap map) {
		map.addAttribute("list", service.getAll());
		return "store/list";
	}

	@GetMapping("/get")
	public String get(String storeid, ModelMap map) {
		map.addAttribute("s", service.getStore(storeid));
		return "store/detail";
	}

	@GetMapping("/getbysid")
	public String getbysid(String sid, ModelMap map) {
		map.addAttribute("s", service.getStoreBySeller(new Member(sid, "", "", "", "")));
		return "store/detail";
	}

	@ResponseBody
	@GetMapping("/getbysidajax")
	public Map getbysid(String sid) {
		// 매장등록상태: 1.store없다 2.open(false) 3.open(true)
		StoreDto dto = service.getStoreBySeller(new Member(sid, "", "", "", ""));
		int state = 1; // 없다
		if (dto != null) {
			if (dto.isOpen()) {
				state = 3;
			} else {
				state = 2;
			}
		}
		Map map = new HashMap();
		map.put("state", state);
		return map;
	}

	@GetMapping("/del")
	public String del(String storeid) {
		service.delStore(storeid);
		return "redirect:/index.jsp";
	}

	@ResponseBody
	@GetMapping("/listajax")
	public Map listajax() {
		ArrayList<StoreDto> list = service.getByOpen(true);
		Map map = new HashMap();
		map.put("list", list);
		return map;
	}
}
