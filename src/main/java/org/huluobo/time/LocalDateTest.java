package org.huluobo.time;

import java.time.LocalDate;

public class LocalDateTest {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate end=LocalDate.of(2018, 10, 31);
		System.out.println("今天在指定日期之后："+today.isAfter(end));
		System.out.println("今天在指定日期之前："+today.isBefore(end));
	}

}
