package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

public class EasySorterTest {
	@Data
	@AllArgsConstructor
	static class MyBean {
		private String name;
		private Integer score;
		private Integer index;
	}

	public static void main(String[] args) {
		MyBean a = new MyBean("A", 98, 3);
		MyBean b = new MyBean("B", 67, 5);
		MyBean c = new MyBean("C", null, 4);
		MyBean d = new MyBean("D", 89, 1);
		MyBean e = new MyBean("E", 98, null);
		MyBean f = new MyBean("F", 89, 6);
		MyBean g = new MyBean("G", 98, 3);
		List<MyBean> list = new ArrayList<>();

		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		list.add(g);
	
		List<MyBean> result = EasySorter.sort(list, "-score,index,-name", false);

		result.forEach(System.out::println);

	}
}
