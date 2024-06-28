package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;
import com.example.demo.member.MemberDao;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// 디비에서 아이디 검색, 없으면 예외 발생, 있으면 파람으로 받은 유저 네임 출력
		// 지정된 메서드 작성하면 알아서 실행, 실행 되는지 알 수 없음
		Member m = dao.findById(username).orElseThrow(() -> new UsernameNotFoundException("not found id:" + username));
		// 검색 결과 없으면 예외 발생
		System.out.println("security service:" + m);
		return new MyUserDetails(m); //>>시큐릿티 vo에 정보를 담아서 반환하는 메서드
	}

}
