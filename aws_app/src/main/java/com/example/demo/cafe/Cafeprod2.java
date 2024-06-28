package com.example.demo.cafe;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.store.Store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cafeprod2 {
	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "seq_cafeprod2", allocationSize = 1) // 시퀀스 생성
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cafeprod2")
	private int num;
	private String name;
	private int price;

	@ManyToOne
	@JoinColumn(nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Store store;
	private String fname;
}
