package com.example.demo.member;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, String> {//JpaRepository:db작업 메서드 정의
	ArrayList<Member> findByType(String type);
}

