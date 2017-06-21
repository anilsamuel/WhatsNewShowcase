package hackerrank.CrackingTheCodingInterviewChallenges;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		TwoStacks.main(args);
	}

	static class LeftRotation {
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			int n = scanner.nextInt();
			if (n < 1 || n > Math.pow(10, 5)) {
				scanner.close();
				return;
			}
			int[] data = new int[n];
			int d = scanner.nextInt();
			if (1 <= d && d <= n) {
				for (int i = 0; i < n; i++)
					data[i] = scanner.nextInt();
			}

			int h = n - d;
			if (h > 0) {
				int[] part2 = Arrays.copyOfRange(data, data.length - h, data.length);
				int[] part1 = Arrays.copyOfRange(data, 0, data.length - h);
				int[] newArr = new int[n];
				System.arraycopy(part2, 0, newArr, 0, part2.length);
				System.arraycopy(part1, 0, newArr, part2.length, part1.length);
				java.util.stream.IntStream.of(newArr).forEach(i -> System.out.print(i + " "));
			} else {
				java.util.stream.IntStream.of(data).forEach(i -> System.out.print(i + " "));
			}

			scanner.close();
		}
	}

	static class MakingAnagram {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			String a = in.next();
			String b = in.next();
			System.out.println(numberNeeded(a, b));
			in.close();
		}

		public static int numberNeeded(String first, String second) {
			char[] arr0 = first.toCharArray();
			char[] arr1 = second.toCharArray();
			for (char ch : arr0)
				second = second.replaceFirst("" + ch, "");
			for (char ch : arr1)
				first = first.replaceFirst("" + ch, "");
			return first.length() + second.length();
		}
	}

	static class RansomNote {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int m = in.nextInt();
			int n = in.nextInt();
			String magazine[] = new String[m];
			for (int magazine_i = 0; magazine_i < m; magazine_i++) {
				magazine[magazine_i] = in.next();
			}
			String ransom[] = new String[n];
			for (int ransom_i = 0; ransom_i < n; ransom_i++) {
				ransom[ransom_i] = in.next();
			}

			List<String> l = new ArrayList<String>(Arrays.asList(magazine));
			boolean feasible = true;
			for (String word : ransom) {
				if (!l.contains(word)) {
					feasible = false;
					break;
				} else {

					l.remove(word);
				}
			}
			if (feasible)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}

	static class DetectCycle {
		class Node {
			int data;
			Node next;
		}

		List<Node> visited = new ArrayList<Node>();

		boolean hasCycle(Node head) {
			boolean ret = false;
			for (Node n = head; n != null; n = n.next) {
				if (visited.contains(head)) {
					ret = true;
					break;
				} else
					visited.add(head);
			}
			return ret;
		}

		public static void main(String[] args) {

		}
	}

	static class BalancedBrackets {
		// }][}}(}][))] - NO
		// [](){()} - YES
		// () - YES
		// ({}([][]))[]() - YES
		// {)[](}]}]}))}(())( - NO
		public static boolean isBalanced(String expression) {
			if (expression.length() % 2 != 0)
				return false;
			else {
				Deque<Character> q = new ArrayDeque<Character>();
				for (char ch : expression.toCharArray()) {
					try {
						switch (ch) {
						case '{':
							q.push(ch);
							break;
						case '}':
							char c = q.peek();
							if (c != '{')
								return false;
							else {
								q.pop();
								break;
							}
						case '(':
							q.push(ch);
							break;
						case ')':
							c = q.peek();
							if (c != '(')
								return false;
							else {
								q.pop();
								break;
							}
						case '[':
							q.push(ch);
							break;
						case ']':
							c = q.peek();
							if (c != '[')
								return false;
							else {
								q.pop();
								break;
							}
						}
					} catch (Exception e) {
						return false;
					}
				}
				if (q.isEmpty())
					return true;
				else
					return false;
			}
		}

		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int t = in.nextInt();
			for (int a0 = 0; a0 < t; a0++) {
				String expression = in.next();
				System.out.println((isBalanced(expression)) ? "YES" : "NO");
			}
			in.close();
		}
	}

	static class TwoStacks {
		public static class MyQueue<T> {
			Stack<T> stackNewestOnTop = new Stack<T>();
			Stack<T> stackOldestOnTop = new Stack<T>();

			public void enqueue(T value) { // Push onto newest stack
				stackNewestOnTop.push(value);
			}

			public T peek() {
				return stackNewestOnTop.peek();
			}

			public T dequeue() {
				return stackNewestOnTop.pop();
			}
		}

		public static void main(String[] args) {
			MyQueue<Integer> queue = new MyQueue<Integer>();

			Scanner scan = new Scanner(System.in);
			int n = scan.nextInt();

			for (int i = 0; i < n; i++) {
				int operation = scan.nextInt();
				if (operation == 1) { // enqueue
					queue.enqueue(scan.nextInt());
				} else if (operation == 2) { // dequeue
					queue.dequeue();
				} else if (operation == 3) { // print/peek
					System.out.println(queue.peek());
				}
			}
			scan.close();
		}
	}
}
