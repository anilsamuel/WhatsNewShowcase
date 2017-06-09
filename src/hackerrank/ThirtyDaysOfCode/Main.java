package hackerrank.ThirtyDaysOfCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Day1.main(args);
		// Day2.main(args);
		// Day3.main(args);
		// Day4.main(args);
		// Day5.main(args);
		// Day6.main(args);
		// Day7.main(args);
		// Day8.main(args);
		// Day9.main(args);
		Day10.main(args);
	}

	static class Day1 {
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			double mealCost = scan.nextDouble(); // original meal price
			int tipPercent = scan.nextInt(); // tip percentage
			int taxPercent = scan.nextInt(); // tax percentage
			scan.close();

			// Write your calculation code here.
			double tip = mealCost * tipPercent / 100;
			double tax = mealCost * taxPercent / 100;

			// cast the result of the rounding operation to an int and save it
			// as totalCost
			int totalCost = (int) Math.round(mealCost + tip + tax);

			// Print your result
			System.out.println(String.format("The total meal cost is %d dollars.", totalCost));

			scan.close();
		}
	}

	static class Day2 {
		public static void main(String[] args) {
			int i = 4;
			double d = 4.0;
			String s = "HackerRank ";

			Scanner scan = new Scanner(System.in);
			/* Declare second integer, double, and String variables. */
			int i2 = -1;
			double d2 = -1;
			String s2 = null;

			/*
			 * Read and save an integer, double, and String to your variables.
			 */
			// Note: If you have trouble reading the entire String, please go
			// back and review the Tutorial closely.
			i2 = scan.nextInt();
			d2 = scan.nextDouble();
			scan.nextLine();
			s2 = scan.nextLine();
			/* Print the sum of both integer variables on a new line. */
			System.out.println(String.format("%d", (i + i2)));

			/* Print the sum of the double variables on a new line. */
			System.out.println(String.format("%.1f", (d + d2)));

			/*
			 * Concatenate and print the String variables on a new line; the 's'
			 * variable above should be printed first.
			 */
			System.out.println(String.format("%s%s", s, s2));

			scan.close();
		}
	}

	static class Day3 {
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			int n = scan.nextInt();
			scan.close();
			String ans = "";

			// if 'n' is NOT evenly divisible by 2 (i.e.: n is odd)
			if (n < 1 || n > 100)
				return;
			if (n % 2 == 1) {
				ans = "Weird";
			} else {
				if (n > 2 && n <= 5)
					ans = "Not Weird";
				else if (n > 6 && n <= 20)
					ans = "Weird";
				else
					ans = "Not Weird";
			}
			System.out.println(ans);
		}
	}

	static class Day4 {
		private int age;

		public Day4(int initialAge) {
			// Add some more code to run some checks on initialAge
			if (initialAge < 0) {
				System.out.println("Age is not valid, setting age to 0.");
				age = 0;
			} else
				age = initialAge;
		}

		public void amIOld() {
			// Write code determining if this person's age is old and print the
			// correct statement:
			if (age < 13)
				System.out.println("You are young.");
			else if (age >= 13 && age < 18)
				System.out.println("You are a teenager.");
			else
				System.out.println("You are old.");
		}

		public void yearPasses() {
			age++;
		}

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int T = sc.nextInt();
			for (int i = 0; i < T; i++) {
				int age = sc.nextInt();
				Day4 p = new Day4(age);
				p.amIOld();
				for (int j = 0; j < 3; j++) {
					p.yearPasses();
				}
				p.amIOld();
				System.out.println();
			}
			sc.close();
		}
	}

	static class Day5 {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			for (int i = 1; i <= 10; i++)
				System.out.println(String.format("%d x %d = %d", n, i, n * i));
		}
	}

	static class Day6 {
		public static void main(String[] args) {
			/*
			 * Enter your code here. Read input from STDIN. Print output to
			 * STDOUT. Your class should be named Solution.
			 */
			Scanner in = new Scanner(System.in);
			int T = in.nextInt();
			if (T < 1 || T > 10) {
				in.close();
				return;
			}
			for (int i = 1; i <= T; i++) {
				String S = in.next();
				if (S.length() < 2 || S.length() > 10000)
					continue;
				String odd = "", even = "";
				char[] arr = S.toCharArray();
				for (int j = 0; j < arr.length; j++) {
					if (j % 2 == 0)
						even += arr[j];
					else
						odd += arr[j];
				}
				System.out.println(String.format("%s %s", even, odd));
			}
			in.close();
		}
	}

	static class Day7 {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			if (n < 1 || n > 1000) {
				in.close();
				return;
			}
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				int j = in.nextInt();
				if (j < 1 || j > 10000)
					continue;
				arr[i] = j;
			}
			for (int i = n - 1; i >= 0; i--)
				System.out.print(String.format("%d ", arr[i]));
			in.close();
		}
	}

	static class Day8 {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			Map<String, Integer> phoneBook = new HashMap<String, Integer>();
			if (n < 1 || n > Math.pow(10, 5)) {
				in.close();
				return;
			}
			for (int i = 0; i < n; i++) {
				String name = in.next();
				int phone = in.nextInt();
				// Write code here
				phoneBook.put(name, phone);
			}
			List<String> searchFor = new ArrayList<String>();
			while (in.hasNext()) {
				String s = in.next();
				// Write code here
				searchFor.add(s);
			}
			for (String s : searchFor) {
				Integer phone = phoneBook.get(s);
				if (phone != null)
					System.out.println(String.format("%s=%d", s, phone));
				else
					System.out.println("Not found");
			}
			in.close();
		}
	}

	static class Day9 {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			if (n < 2 || n > 12) {
				in.close();
				return;
			}
			System.out.println(factorial(n));
			in.close();
		}

		static int factorial(int n) {
			if (n == 1)
				return n;
			else
				return n * factorial(n - 1);
		}
	}

	static class Day10 {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			if (n < 2 || n > Math.pow(10, 6)) {
				in.close();
				return;
			}
			String s = Integer.toBinaryString(n);
			//System.out.println(s);
			String[] arr = s.split("0");
			List<String> l = Arrays.asList(arr);
			//System.out.println(l);
			System.out.println(l.stream()
				//.forEach(o -> System.out.println(o))
				.sorted((o1, o2) -> o2.length() - o1.length())
				.findFirst().get().length()
			);
			in.close();
		}
	}
}
