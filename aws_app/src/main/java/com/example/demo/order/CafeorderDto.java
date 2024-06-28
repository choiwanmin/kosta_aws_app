package com.example.demo.order;

import java.util.ArrayList;
import java.util.Date;

import com.example.demo.member.Member;
import com.example.demo.store.Store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CafeorderDto {
	private int num;
	private Member oid;
	private Date odate;
	private int payment;
	private int ostate;
	private Store store;
	ArrayList<OrderitemDto> items;
}
