package com.example.demo.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.member.Member;

import lombok.Getter;
import lombok.Setter;

//>>인증 Dto 역할
@Setter
@Getter
//>>인증에 활용되는 객체
public class MyUserDetails implements UserDetails {

	/**
	 * 인증에 필요한 vo를 갖는 객체
	 */
	private static final long serialVersionUID = 1L;

	// 인증값 객체를 final로 정의 >>비교값이기에 중간에 바뀌면 안됨
	private final Member m;

	public MyUserDetails(Member m) { // >>생성자로 의존성 받아오기
		this.m = m;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		// >>인증 정보를 컬렉션 형태로 담아서 반환
		// >>타입별로 권한을 주기 위해, 인증 유형 권한 유형으로 정보를 추가적으로 전달
		List<GrantedAuthority> list = new ArrayList<>();
		//>>사용자 타입에 따라서 권한 부여
		String role = "";
		if(m.getType().equals("admin")) {
			role = "ROLE_ADMIN";
		} else if (m.getType().equals("seller")) {
			role = "ROLE_SELLER";
		} else if (m.getType().equals("consumer")) {
			role = "ROLE_CONSUMER";
		}
		list.add(new SimpleGrantedAuthority(role));
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return m.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return m.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
