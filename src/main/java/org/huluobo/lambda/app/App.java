package org.huluobo.lambda.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList; 

import org.huluobo.lambda.bean.Apple;

public class App {
	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}

	public interface Predicate<T> {
		boolean test(T t);
	}

	static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	// 获取集合中符合p条件的元素组成的新集合
	static List<String> outString(List<String> inventory, Predicate<String> p) {
		List<String> result = new ArrayList<>();
		for (String str : inventory) {
			if (p.test(str)) {
				result.add(str);
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		List<Apple> inventory = new ArrayList<>();
		//获取集合中绿色的苹果
		//filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
		
		//1写法一：排序
		//inventory.sort((Apple a, Apple b) -> a.getWeight().compareTo(b.getWeight()));
		//import static java.util.Comparator.comparing;静态辅助方法
		//2写法二：排序。类型推断
		//inventory.sort(comparing(a -> a.getWeight()));
		//3写法三：方法引用排序，加上reversed()按重量递 减排序 
		//inventory.sort(comparing(Apple::getWeight).reversed());
		//thenComparing()进一步排序
		//inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
		
		
		// 转换
		//inventory.stream();
		// 转换之后可以分片执行，对于多核cpu处理优化
		//inventory.parallelStream();
		
		// 创建一个线程并启动
		// new Thread(() -> System.out.println("hello")).start();
		
		// 读取一行
		// String oneLine = processFile((BufferedReader br) -> br.readLine());
		// 读取两行
		// String twoLines = processFile((BufferedReader br) -> br.readLine() +br.readLine());

		// 字符串长度大于3的元素组成的新集合
		List<String> attr = new ArrayList<>();
		attr.add("a");
		attr.add("abc");
		attr.add("qwer");
		attr = outString(attr, (String a) -> a.length() >= 3);
		System.out.println(attr);

		// 使用jdk8自带的Predicate
		// java.util.function.Predicate<String> p=(String a)->a.length()>=3;
		// 参数仅有一个类型的时候，参数两边括号和声明类型可以省略
		// java.util.function.Predicate<String> p=a->a.length()>=3;
		// 多个参数，参数类型声明可以省略(类型推断机制)
		// Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

		// 方法引用
		//Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s); 
		Function<String, Integer> stringToInteger = Integer::parseInt;
		//BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element); 
		BiPredicate<List<String>, String> contains = List::contains;
		//两个参数的构造函数，jdk只提供了最多两个参数的函数式接口，更多参数的自己创造
		BiFunction<String,Integer,Apple> appf=Apple::new;
		Apple apple=appf.apply("red", 140);
		
		//import static java.util.stream.Collectors.toList; 
		//筛选 排序 提取字段值 将提取的字段值放入集合中
		List<String> color=	inventory.stream().filter(a->a.getWeight()>100).sorted(comparing(Apple::getWeight)).map(Apple::getColor).collect(toList());
		
	}


	// 函数式接口-行为抽象
	@FunctionalInterface
	public interface BufferedReaderProcessor {
		String process(BufferedReader b) throws IOException;
	}

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			return p.process(br);
		}
	}

	/*
	 * public static String processFile() throws IOException { try (BufferedReader
	 * br = new BufferedReader(new FileReader("data.txt"))) { return br.readLine();
	 * } }
	 */

}
