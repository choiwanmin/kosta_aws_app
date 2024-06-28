package com.example.demo.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 추가, 수정
	public MemberDto save(MemberDto dto) {
		// dao.save() 반환값: 방금 추가/수정한 그 행을 검색해서 entity에 담아서 반환
		Member m = dao.save(new Member(dto.getId(), passwordEncoder.encode(dto.getPwd()), dto.getName(), dto.getEmail(), dto.getType()));
		return new MemberDto(m.getId(), m.getPwd(), m.getName(), m.getEmail(), m.getType());
	}

	// id로 검색
	public MemberDto getMember(String id) {
		// dao.findById(pk): pk기준으로 검색
		Member m = dao.findById(id).orElse(null);// orElse(null): 검색결과 없으면 널 반환
		if (m == null) {
			return null;
		}
		return new MemberDto(m.getId(), m.getPwd(), m.getName(), m.getEmail(), m.getType());
	}

	// 전체검색: findAll()
	public ArrayList<MemberDto> getAll() {
		List<Member> l = dao.findAll();
		ArrayList<MemberDto> list = new ArrayList<>();
		for (Member m : l) {
			list.add(new MemberDto(m.getId(), m.getPwd(), m.getName(), m.getEmail(), m.getType()));
		}
		return list;
	}
	
	//id기준 삭제
	public void delMember(String id) {
		dao.deleteById(id);
	}
	
	//type을 검색
	public ArrayList<MemberDto> getByType(String type) {
		List<Member> l = dao.findByType(type);
		ArrayList<MemberDto> list = new ArrayList<>();
		for (Member m : l) {
			list.add(new MemberDto(m.getId(), m.getPwd(), m.getName(), m.getEmail(), m.getType()));
		}
		return list;
	}
}







