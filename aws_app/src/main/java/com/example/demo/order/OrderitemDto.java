package com.example.demo.order;

import com.example.demo.cafe.Cafeprod2;
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
public class OrderitemDto {
	private int num;
	private Cafeprod2 prod;
	private int amount;
	private Cafeorder parent;
}
