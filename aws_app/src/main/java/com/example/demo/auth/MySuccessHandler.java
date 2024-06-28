package com.example.demo.auth;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//인증 성공시 실행될 핸들러
public class MySuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String type = "";
		if (loginId == null) {
			session.setAttribute("loginId", authentication.getName()); // 인증한 사람 id
//			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("type1"))) {
//				type = "type1";
//			} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("type2"))) {
//				type = "type2";
//			}

			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
				type = "admin";
			} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SELLER"))) {
				type = "seller";
			} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CONSUMER"))) {
				type = "consumer";
			}
			session.setAttribute("type", type);
		}
		System.out.println("MySuccessHandler:" + authentication.getName());

		// 인증 후 클라이언트가 요청한 페이지로 이동
//		RequestCache requestCache = new HttpSessionRequestCache();
//		SavedRequest saveRequest = requestCache.getRequest(request, response); // 현재 저장된 url을 읽을 수 있는 객체
//		// url: http://localhost:8081/member/add?id=aaa....
//		String path = saveRequest.getRedirectUrl().split("8081")[1].split("\\?")[0]; // >> 파라미터 앞에 까지 url 잘라냄
//		System.out.println(path);
//
//		String path = "/index_type2";
//		if(type.equals("type1")) {
//			path = "/index_type1";
//		}

		String path = "/index_" + type;

		request.getRequestDispatcher(path).forward(request, response); // >>리퀘스트 디스패쳐로 포워드로 이동
	}

}
