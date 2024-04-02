package com.tay.sort;

import com.tay.sort.EasySorter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

// 假设EasySorterImproved类已经定义好了，如前面的实现

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

		List<SortRule<MyBean>> sortRules = Arrays.asList(
				new SortRule<>(MyBean::getScore, true), // 按 score 降序
				new SortRule<>(MyBean::getIndex, false), // 按 index 升序
				new SortRule<>(MyBean::getName, false) // 按 index 升序
		);

		// 根据新的API调用排序方法
		List<MyBean> result = EasySorter.sort(list, sortRules, true); // 假设我们想要升序，nulls first

		result.forEach(System.out::println);
	}
}
