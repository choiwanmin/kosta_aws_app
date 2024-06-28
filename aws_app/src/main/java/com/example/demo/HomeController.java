package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.member.Member;
import com.example.demo.member.MemberDto;
import com.example.demo.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private MemberService service;

	@GetMapping("/") // >>루트 요청
	public String home() {
		return "index"; // >>인덱스 페이지 반환
	}

	@GetMapping("/join")
	public String joinform() {
		return "member/join";
	}

	@PostMapping("/join")
	public String join(MemberDto dto) {
		service.save(dto);
		return "redirect:/";
	}

	@GetMapping("/loginform")
	public String loginform() {
		return "member/login";
	}
	
	@GetMapping("/auth/login")
	public void authlogin() {
		
	}

	@GetMapping("/auth/logout")
	public String authlogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 관리자가 로그인 후 이동할 경로
	@RequestMapping("/index_admin")
	public void adminHome() {
	}

	// 판매자가 로그인 후 이동할 경로
	@RequestMapping("/index_seller")
	public void sellerHome() {
	}

	// 구매자가 로그인 후 이동할 경로
	@RequestMapping("/index_consumer")
	public void consumerHome() {
	}
	
	@ResponseBody
	@GetMapping("/idcheck")
	public Map idcheck(String id) {
		Map map = new HashMap();
		MemberDto mdto = service.getMember(id);
		boolean flag = false;
		if(mdto == null) {
			flag = true;
		}
		map.put("flag", flag);
		return map;
	}

}
