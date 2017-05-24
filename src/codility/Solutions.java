package codility;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solutions {

	public static void main(String[] args) {
		System.out.println("EquilibriumIndex(-1, 3, -4, 5, 1, -6, 2, 1) = " + new EquilibriumIndex().solution(new int[] { -1, 3, -4, 5, 1, -6, 2, 1 }));
		System.out.println("ArrayLeader(4, 2, 2, 3, 2, 4, 2, 2, 6, 4) = " + new ArrayLeader().solution(new int[] { 4, 2, 2, 3, 2, 4, 2, 2, 6, 4 }));
		System.out.println("ArrayLeader(1, 1, 1, 50, 1) = " + new ArrayLeader().solution(new int[] { 1, 1, 1, 50, 1 }));
		// new ArrayLeader().solution(new Integer[] { 1, 4, 3, 2, 3 });
	}

	static class EquilibriumIndex {
		int solution(int[] A) {
			for (int i = 1; i < A.length; i++) {
				int[] a1 = Arrays.copyOfRange(A, 0, i);
				int[] a2 = Arrays.copyOfRange(A, i + 1, A.length);
				int sum1 = IntStream.of(a1).sum();
				int sum2 = IntStream.of(a2).sum();
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
				.collect(Collectors.toMap(
					k -> k, 
					v -> Stream.of(A).parallel().filter(
						a -> a == v
					).count()
				)
			);
			Map<Integer, Long> result = new LinkedHashMap<Integer, Long>();
			occur.entrySet().stream()
				.sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
				.forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
			Entry<Integer, Long> leader = result.entrySet().iterator().next();
			return leader.getValue() > (double) A.length / 2 ? leader.getKey() : -1;
		}
	}
}
