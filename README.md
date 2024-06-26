# EasySorter
a powerful "scala like" utility for sorting a list of elements with order by assigned fields of the element 


## Quickstart

### Clone, build and install
``` bash
git clone https://github.com/tangaiyun/EasySorter.git
cd EasySorter
mvn clean install
```

### Add to your POM
Then create a Maven project，and you need to add dependency in your pom.xml

``` xml
        <dependency>
            <groupId>com.tay.sort</groupId>
            <artifactId>EasySort</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

### Usage
please refer to the EasySorterTest.java
``` java

package com.tay.sort;

import com.tay.sort.EasySorter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
		MyBean g = new MyBean("G", 98, 2);
		List<MyBean> list = new ArrayList<>();

		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		list.add(g);

		List<SortRule<MyBean>> sortRules = Arrays.asList(
				new SortRule<>(MyBean::getScore, true, true), // 按 score 降序
				new SortRule<>(MyBean::getIndex, false,false), // 按 index 升序
				new SortRule<>(MyBean::getName, false,true) // 按 index 升序
		);

		// 根据新的API调用排序方法
		List<MyBean> result = EasySorter.sort(list, sortRules); // 假设我们想要升序，nulls first

		result.forEach(System.out::println);
	}
}

```
the output is:
```
EasySorterTest.MyBean(name=G, score=98, index=2)
EasySorterTest.MyBean(name=A, score=98, index=3)
EasySorterTest.MyBean(name=E, score=98, index=null)
EasySorterTest.MyBean(name=D, score=89, index=1)
EasySorterTest.MyBean(name=F, score=89, index=6)
EasySorterTest.MyBean(name=B, score=67, index=5)
EasySorterTest.MyBean(name=C, score=null, index=4)
```

### Advanced introduce

there is only one class "EasySorter", and there is only one method for sorting a list of elements:

```
public static <T> List<T> sort(List<T> source, List<SortRule<T>> sortRules)
```
#### source: 
```
elements for sorting
```
### sortRules: 
```
a list of SortRule
```
### SortRule
``` java
public class SortRule<T> {
    @Getter
    private Function<T, Comparable> keyExtractor; // 键提取器，用于从元素中提取比较键
    @Getter
    private boolean isDesc; // 排序方向标志，true为降序，false为升序
    @Getter
    private boolean isNullFirst; // 指示null值是排在前面


    // 构造函数也需要添加相应的参数
    public SortRule(Function<T, Comparable> keyExtractor, boolean isDesc, boolean isNullFirst) {
        this.keyExtractor = keyExtractor;
        this.isDesc = isDesc;
        this.isNullFirst = isNullFirst;
    }
}
```


	


