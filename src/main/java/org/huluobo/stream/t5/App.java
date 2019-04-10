package org.huluobo.stream.t5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.huluobo.stream.bean.Dish;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList; 
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;; 

public class App {

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		// 找出2011年发生的所有交易，并按交易额排序（从低到高）。 
		List<Transaction> tr2011 = transactions.stream().filter(a->a.getYear()==2011).sorted(comparing(Transaction::getValue)).collect(toList());
		// 交易员都在哪些不同的城市工作过？ 
		Set<String> cities = transactions.stream().map(a->a.getTrader().getCity()).distinct().collect(toSet());
		//查找所有来自于Cambridge的交易员，并按姓名排序。 
		List<Trader> traders = transactions.stream().map(Transaction::getTrader).filter(a->a.getCity()=="Cambridge").distinct().sorted(comparing(Trader::getName)).collect(toList());
		// 返回所有交易员的姓名字符串，按字母顺序排序。 
		String traderStr = transactions.stream().map(a->a.getTrader().getName()).distinct().sorted().reduce("",(n,m)->n+m);
		//有没有交易员是在米兰工作的？ 
		boolean milanBased = transactions.stream().anyMatch(a->a.getTrader().getCity().equals("Milan"));
		//打印生活在剑桥Cambridge的交易员的所有交易额 
		transactions.stream().filter(a->a.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
		// 所有交易中，高的交易额是多少 
		Optional<Integer> highestValue =transactions.stream().map(Transaction::getValue).reduce(Integer::max);
		// 找到交易额小的交易 
		Optional<Transaction> smallestTransaction = transactions.stream().min(comparing(Transaction::getValue));
		//分组
		Map<Trader, List<Transaction>> map=transactions.stream().collect(groupingBy(Transaction::getTrader));
		
		//勾股数流 
		Stream<int[]> pythagoreanTriples = IntStream.range(1, 100).boxed().flatMap(a->IntStream.range(a, 100).filter(b->Math.sqrt(a*a+b*b)%1==0).mapToObj(b->new int[] {a,b,(int) Math.sqrt(a * a + b * b)}));
		Stream<double[]> pythagoreanTriples2 = IntStream.range(1, 100).boxed().flatMap(a->IntStream.range(a, 100).mapToObj(b->new double[] {a,b,(int) Math.sqrt(a * a + b * b)})).filter(t->t[2]%1==0);
		
		//一个文件中有多少各不相同的词
		long uniqueWords = 0; 
		try(Stream<String> lines = Files.lines(Paths.get("e:/data.txt"),Charset.defaultCharset())) {
			uniqueWords=lines.flatMap(a->Arrays.stream(a.split(" "))).distinct().count();
			System.out.println(uniqueWords);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		//斐波那契
		Stream.iterate(new int[] {0,1},t->new int[] {t[1],t[0]+t[1]}).limit(5).forEach(t->System.out.print("("+t[0]+","+t[1]+")"));

	}
	
	public boolean isPrime(int candidate) {    
		//待测数平方根
		//因为如果它不是质数，那么它一定可以表示成两个数（除了1和它本身）相乘，这两个数必然有一个小于等于它的平方根。
		int candidateRoot = (int) Math.sqrt((double) candidate);    
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}
	
	public Map<Boolean, List<Integer>> partitionPrimes(int n) {     
		return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate))); 
	}
	
}
