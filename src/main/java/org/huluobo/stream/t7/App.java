package org.huluobo.stream.t7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {
		//System.out.println("Sequential sum done in:" + measureSumPerf(App::parallelRangedSum, 10_000_000) + " msecs");
		System.out.println(
				"ForkJoin sum done in: " + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs");

	}

	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		//记录运行时间最短的时间-毫秒
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result: " + sum);
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}

	// 原始for循环 3 msecs
	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}

	// 顺序流执行 95 msecs
	public static long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
	}

	// 并行处理 232 msecs
	public static long parallelSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
	}

	// LongStream顺序流 减少拆箱操作 4 msecs
	public static long rangedSum(long n) {
		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}

	// LongStream并行流 减少拆箱操作 2 msecs
	public static long parallelRangedSum(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}
}
