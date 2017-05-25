package codility;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class Solutions {

	public static int[] randomArray() {
		Random r = new Random();
		int s = r.nextInt(100 - 10) + 10;
		int[] arr = new int[s];
		for (int i = 0; i < arr.length; i++)
			arr[i] = r.nextInt();
		return arr;
	}

	public static String asList(int[] arr) {
		final StringBuffer s = new StringBuffer();
		IntStream.of(arr).forEach(i -> s.append(i + ","));
		return s.toString();
	}

	@Test
	public void testEquilibriumIndex() {
		Random r = new Random();
		int s = r.nextInt(100 - 10) + 10;
		for (int i = 0; i < s; i++) {
			int[] arr = randomArray();
			System.out.println("EquilibriumIndex(" + asList(arr) + ") = " + new EquilibriumIndex().solution(arr));
		}
	}

	public static void main(String[] args) {
		new Solutions().testEquilibriumIndex();

		System.out.println("ArrayLeader(4, 2, 2, 3, 2, 4, 2, 2, 6, 4) = "
				+ new ArrayLeader().solution(new int[] { 4, 2, 2, 3, 2, 4, 2, 2, 6, 4 }));
		System.out.println("ArrayLeader(1, 1, 1, 50, 1) = " + new ArrayLeader().solution(new int[] { 1, 1, 1, 50, 1 }));
		// new ArrayLeader().solution(new Integer[] { 1, 4, 3, 2, 3 });
	}

	static class EquilibriumIndex {
		int solution(int[] A) {
			for (int i = 1; i < A.length; i++) {
				int[] a1 = Arrays.copyOfRange(A, 0, i);
				int[] a2 = Arrays.copyOfRange(A, i + 1, A.length);
				long sum1 = IntStream.of(a1).sum();
				long sum2 = IntStream.of(a2).sum();
				if (sum1 == sum2)
					return i;
			}
			return -1;
		}
	}

	static class ArrayLeader {
		int solution(int[] A) {
			return solution(IntStream.of(A).boxed().toArray(Integer[]::new));
		}

		int solution(final Integer[] A) {
			Map<Integer, Long> occur = Stream.of(A).distinct()
					.collect(Collectors.toMap(k -> k, v -> Stream.of(A).parallel().filter(a -> a == v).count()));
			Map<Integer, Long> result = new LinkedHashMap<Integer, Long>();
			occur.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
					.forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
			Entry<Integer, Long> leader = result.entrySet().iterator().next();
			return leader.getValue() > (double) A.length / 2 ? leader.getKey() : -1;
		}
	}
}
