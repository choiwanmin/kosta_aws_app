package com.example.demo.order;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.member.Member;
import com.example.demo.store.Store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cafeorder {
	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "seq_cafeorder", allocationSize = 1) // 시퀀스 생성
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cafeorder")
	private int num;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member oid;
	private Date odate;
	private int payment;
	private int ostate;//1:주문 2:주문수락 3:음식준비완료 4:픽업
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Store store;
	
	@PrePersist
	public void setDate() {
		odate = new Date();
	}
}