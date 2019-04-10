package org.huluobo.stream.t7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private final long[] numbers;// 要求求和的数组
	private final int start;// 子任务处理的数组开始位置
	private final int end;// 子任务处理的数组结束位置

	public static final long THRESHOLD = 10_000;// 不再将任务分 解为子任务的 数组大小

	// 公告构造函数，用来创建主任务
	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	// 私有构造函数，用来创建子任务
	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		int length = end - start;
		if (length <= THRESHOLD) {
			return computeSequentially();
		}
		// 创建一个子任 务来为数组的 前一半求和
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
		// 利用另一个 ForkJoinPool 线程异步执行新 创建的子任务
		leftTask.fork();
		// 创建一个任务 为数组的后一 半求和
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
		// 同步执行第二个子 任务，有可能允许进 一步递归划分
		Long rightResult = rightTask.compute();
		// 读取第一个子任务的结果， 如果尚未完成就等待
		Long leftResult = leftTask.join();
		return leftResult + rightResult;
	}

	// 在子任 务不再 可分时 计算结 果的简 单算法
	private long computeSequentially() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			{
				sum += numbers[i];
			}
		}
		return sum;
	}

	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}

}
