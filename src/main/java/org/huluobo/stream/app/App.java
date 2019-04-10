package org.huluobo.stream.app;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.huluobo.stream.bean.Dish;

import static java.util.stream.Collectors.*; 

public class App {

	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));
		
		//热量>500 的菜名 分页结果存的新的集合
		menu.stream().filter(a->a.getCalories()>500).map(Dish::getName).limit(3).collect(toList());
		
		
		List<Integer> numbers1 = Arrays.asList(1, 2, 3); 
		List<Integer> numbers2 = Arrays.asList(3, 4); 
		//numbers1和numbers2进行组合数对 总和能被3整除的数对 
		List<int[]> pairs = numbers1.stream().flatMap(i->numbers2.stream().filter(j->(i+j)%3==0).map(j->new int []{i,j})).collect(toList());
		pairs.forEach(a->Arrays.stream(a).forEach(System.out::print));
		
		numbers1.stream().reduce(0, (a,b)-> a+b);
		numbers1.stream().reduce(0, Integer::sum);
		
		IntSummaryStatistics menuStatistics =  menu.stream().collect(summarizingInt(Dish::getCalories)); 
		
		//按照食物热量进行分组 低 普通 高
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() <= 400) return CaloricLevel.DIET; 
			else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;         
			else return CaloricLevel.FAT;  } )); 

	}
	public enum CaloricLevel { DIET, NORMAL, FAT } 
}
