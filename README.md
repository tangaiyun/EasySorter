# EasySorter
a very powerful utility for sorting a list of elements order by fieds of the element 


## Quickstart

### Clone, build and install
``` bash
git clone https://github.com/tangaiyun/EasySorter.git
cd EasySorter
mvn clean install
```

### Add to your POM
Then create a Spring boot API project refer to sample project "demo1"，and you need to add dependency in pom.xml

``` xml
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>EasySort</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

### Usage
please refer to the EasySorterTest.java
``` java

import com.example.demo.EasySorter;

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
```
the output is:
```
EasySorterTest.MyBean(name=G, score=98, index=3)
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
public static <T> List<T> sort(List<T> source, String sortString, boolean isNullFirst)
```
#### source: 
```
elements for sorting
```
### sortString: 
```
a list of field names of element, such as "-score,index,-name", for this sortString, which means the element will be ordered by "score desc, index asc, name desc"
```
#### isNullFirst:
```
if true, if the value of the field is null, then it will be arranged firstly. if false,  if the value of the field is null, then it will be arranged at last.
```

	


