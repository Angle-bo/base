package org.huluobo.stream.t6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList; 

public class App {
	public static void main(String[] args) {
		Line line = new Line("A", "B", 1);
		List<Travel> travels = Arrays.asList(new Travel(line, new Person("张三")), new Travel(line, new Person("李四")),
				new Travel(new Line("B", "A", 2), new Person("王五")),
				new Travel(new Line("A", "C", 3), new Person("赵四")));

		// 分组，比较的是对象的地址。两个new的情况属于两个对象，不会分到一组
		//Map<Line, List<Travel>> map = travels.stream().collect(groupingBy(Travel::getLine));

		//travels.stream().collect(groupingBy(Travel::getLine, groupingBy(Travel::getPerson)));
		List<Line> lines=travels.stream().map(Travel::getLine).collect(toList());
		Map<String, Map<String, Map<Long, List<Line>>>> map=lines.stream().collect(groupingBy(Line::getLeave,groupingBy(Line::getArrival,groupingBy(Line::getTime))));
		System.out.println(map);
		System.out.println(java.util.concurrent.ForkJoinPool.commonPool().getParallelism());
	}
}

class Line {
	private String leave; // 出发点
	private String arrival;// 到达地
	private long time;// 出发时间

	public Line(String leave, String arrival, long time) {
		super();
		this.leave = leave;
		this.arrival = arrival;
		this.time = time;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}

class Person {
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Travel {
	private Line line;
	private Person person;

	public Travel(Line line, Person person) {
		super();
		this.line = line;
		this.person = person;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}