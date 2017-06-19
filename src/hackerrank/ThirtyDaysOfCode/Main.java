package hackerrank.ThirtyDaysOfCode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws ParseException {
		Day29.main(args);
	}
	
	public static Object copy(Object orig) {
		Object obj = null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(orig);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			obj = in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static interface PrintableNode {
		/** Get left child */
		PrintableNode getLeft();

		/** Get right child */
		PrintableNode getRight();

		/** Get text to be printed */
		String getText();
	}	 

	public static void print(PrintableNode root) {
		List<List<String>> lines = new ArrayList<List<String>>();

		List<PrintableNode> level = new ArrayList<PrintableNode>();
		List<PrintableNode> next = new ArrayList<PrintableNode>();

		level.add(root);
		int nn = 1;

		int widest = 0;

		while (nn != 0) {
			List<String> line = new ArrayList<String>();

			nn = 0;

			for (PrintableNode n : level) {
				if (n == null) {
					line.add(null);

					next.add(null);
					next.add(null);
				} else {
					String aa = n.getText();
					line.add(aa);
					if (aa.length() > widest)
						widest = aa.length();

					next.add(n.getLeft());
					next.add(n.getRight());

					if (n.getLeft() != null)
						nn++;
					if (n.getRight() != null)
						nn++;
				}
			}

			if (widest % 2 == 1)
				widest++;

			lines.add(line);

			List<PrintableNode> tmp = level;
			level = next;
			next = tmp;
			next.clear();
		}

		int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
		for (int i = 0; i < lines.size(); i++) {
			List<String> line = lines.get(i);
			int hpw = (int) Math.floor(perpiece / 2f) - 1;

			if (i > 0) {
				for (int j = 0; j < line.size(); j++) {

					// split node
					char c = ' ';
					if (j % 2 == 1) {
						if (line.get(j - 1) != null) {
							c = (line.get(j) != null) ? '┴' : '┘';
						} else {
							if (j < line.size() && line.get(j) != null)
								c = '└';
						}
					}
					System.out.print(c);

					// lines and spaces
					if (line.get(j) == null) {
						for (int k = 0; k < perpiece - 1; k++) {
							System.out.print(" ");
						}
					} else {

						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? " " : "─");
						}
						System.out.print(j % 2 == 0 ? "┌" : "┐");
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? "─" : " ");
						}
					}
				}
				System.out.println();
			}

			// print line of numbers
			for (int j = 0; j < line.size(); j++) {

				String f = line.get(j);
				if (f == null)
					f = "";
				int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
				int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

				// a number
				for (int k = 0; k < gap1; k++) {
					System.out.print(" ");
				}
				System.out.print(f);
				for (int k = 0; k < gap2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

			perpiece /= 2;
		}
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
			// System.out.println(s);
			String[] arr = s.split("0");
			List<String> l = Arrays.asList(arr);
			// System.out.println(l);
			System.out.println(l.stream()
					// .forEach(o -> System.out.println(o))
					.sorted((o1, o2) -> o2.length() - o1.length()).findFirst().get().length());
			in.close();
		}
	}

	static class Day12 {
		static class Person {
			protected String firstName;
			protected String lastName;
			protected int idNumber;

			// Constructor
			Person(String firstName, String lastName, int identification) {
				this.firstName = firstName;
				this.lastName = lastName;
				this.idNumber = identification;
			}

			// Print person data
			public void printPerson() {
				System.out.println("Name: " + lastName + ", " + firstName + "\nID: " + idNumber);
			}

		}

		static class Student extends Person {

			private int[] testScores;

			/*
			 * Class Constructor
			 * 
			 * @param firstName - A string denoting the Person's first name.
			 * 
			 * @param lastName - A string denoting the Person's last name.
			 * 
			 * @param id - An integer denoting the Person's ID number.
			 * 
			 * @param scores - An array of integers denoting the Person's test
			 * scores.
			 */
			// Write your constructor here
			public Student(String firstName, String lastName, int id, int[] testScores2) {
				super(firstName, lastName, id);
				testScores = testScores2;
			}

			/*
			 * Method Name: calculate
			 * 
			 * @return A character denoting the grade.
			 */
			// Write your method here
			public char calculate() {
				OptionalInt sum = IntStream.of(testScores).reduce((l, r) -> (l + r));
				double avg = sum.getAsInt() / testScores.length;
				if (avg >= 90 && avg <= 100)
					return 'O';
				else if (avg >= 80 && avg < 90)
					return 'E';
				else if (avg >= 70 && avg < 80)
					return 'A';
				else if (avg >= 55 && avg < 70)
					return 'P';
				else if (avg >= 40 && avg < 55)
					return 'D';
				else
					return 'T';
			}
		}

		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			String firstName = scan.next();
			String lastName = scan.next();
			int id = scan.nextInt();
			int numScores = scan.nextInt();
			int[] testScores = new int[numScores];
			for (int i = 0; i < numScores; i++) {
				testScores[i] = scan.nextInt();
			}
			scan.close();

			Student s = new Student(firstName, lastName, id, testScores);
			s.printPerson();
			System.out.println("Grade: " + s.calculate());
		}
	}

	static class Day13 {
		static abstract class Book {
			String title;
			String author;

			Book(String title, String author) {
				this.title = title;
				this.author = author;
			}

			abstract void display();
		}

		static class MyBook extends Book {
			int price;

			MyBook(String title, String author, int price) {
				super(title, author);
				this.price = price;
			}

			@Override
			void display() {
				System.out.println(String.format("Title: %s\nAuthor: %s\nPrice: %d", title, author, price));
			}
		}

		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			String title = scanner.nextLine();
			String author = scanner.nextLine();
			int price = scanner.nextInt();
			scanner.close();

			Book book = new MyBook(title, author, price);
			book.display();
		}
	}

	static class Day14 {
		static class Difference {
			private int[] elements;
			public int maximumDifference;

			public Difference(int[] a) {
				elements = a;
			}

			public void computeDifference() {
				IntSummaryStatistics stats = IntStream.of(elements).sorted().summaryStatistics();
				maximumDifference = stats.getMax() - stats.getMin();
			}
		}

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			sc.close();
			Difference difference = new Difference(a);
			difference.computeDifference();
			System.out.print(difference.maximumDifference);
		}
	}

	static class Day15 {
		static class Node {
			int data;
			Node next;

			Node(int d) {
				data = d;
				next = null;
			}
		}

		public static Node insert(Node head, int data) {
			// Complete this method
			Node n = new Node(data);
			if (head == null)
				return n;
			else {
				Node current = head;
				while (current.next != null)
					current = current.next;
				current.next = n;
				return head;
			}
		}

		public static void display(Node head) {
			Node start = head;
			while (start != null) {
				System.out.print(start.data + " ");
				start = start.next;
			}
		}

		public static void main(String args[]) {
			Scanner sc = new Scanner(System.in);
			Node head = null;
			int N = sc.nextInt();

			while (N-- > 0) {
				int ele = sc.nextInt();
				head = insert(head, ele);
			}
			display(head);
			sc.close();
		}
	}

	static class Day16 {
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			String S = in.next();
			try {
				System.out.println(Integer.parseInt(S));
			} catch (Exception e) {
				System.out.println("Bad String");
			}
		}
	}

	static class Day17 {
		static class Calculator {
			public int power(int n, int p) {
				if (n < 0 || p < 0)
					throw new RuntimeException("n and p should be non-negative");
				return (int) Math.pow(n, p);
			}
		}

		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int t = in.nextInt();
			while (t-- > 0) {

				int n = in.nextInt();
				int p = in.nextInt();
				Calculator myCalculator = new Calculator();
				try {
					int ans = myCalculator.power(n, p);
					System.out.println(ans);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			in.close();
		}
	}

	static class Day18 {

		static Queue<Character> queue = new LinkedList<Character>();
		static Stack<Character> stack = new Stack<Character>();

		private void enqueueCharacter(Character c) {
			queue.add(c);
		}

		private Character dequeueCharacter() {
			return queue.remove();
		}

		private void pushCharacter(Character c) {
			stack.push(c);
		}

		private Character popCharacter() {
			return stack.pop();
		}

		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			scan.close();

			// Convert input String to an array of characters:
			char[] s = input.toCharArray();

			// Create a Solution object:
			Day18 p = new Day18();

			// Enqueue/Push all chars to their respective data structures:
			for (char c : s) {
				p.pushCharacter(c);
				p.enqueueCharacter(c);
			}

			// Pop/Dequeue the chars at the head of both data structures and
			// compare them:
			boolean isPalindrome = true;
			for (int i = 0; i < s.length / 2; i++) {
				if (p.popCharacter() != p.dequeueCharacter()) {
					isPalindrome = false;
					break;
				}
			}

			// Finally, print whether string s is palindrome or not.
			System.out.println(
					"The word, " + input + ", is " + ((!isPalindrome) ? "not a palindrome." : "a palindrome."));
		}

	}

	static class Day19 {
		static interface AdvancedArithmetic {
			int divisorSum(int n);
		}

		static class Calculator implements AdvancedArithmetic {
			@Override
			public int divisorSum(int n) {
				int sum = 0;
				for (int i = 1; i <= n; i++)
					sum += (n % i == 0) ? i : 0;
				return sum;
			}
		}

		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			int n = scan.nextInt();
			scan.close();

			AdvancedArithmetic myCalculator = new Calculator();
			int sum = myCalculator.divisorSum(n);
			System.out.println("I implemented: " + myCalculator.getClass().getInterfaces()[0].getName());
			System.out.println(sum);
		}
	}

	static class Day20 {
		static class Sorting {
			private static void printArray(String s, int[] x) {
				System.out.print(s + " Array: ");
				for (int i : x) {
					System.out.print(i + " ");
				}
				System.out.println();
			}

			public static int bubbleSort(int[] x) {
				// printArray("Initial", x);

				int endPosition = x.length - 1;
				int swapPosition, numSwaps = 0;

				while (endPosition > 0) {
					swapPosition = 0;

					for (int i = 0; i < endPosition; i++) {

						if (x[i] > x[i + 1]) {
							// Swap elements 'i' and 'i + 1':
							int tmp = x[i];
							x[i] = x[i + 1];
							x[i + 1] = tmp;

							swapPosition = i;
							numSwaps++;
						} // end if

						// printArray("Current", x);
					} // end for

					endPosition = swapPosition;
				} // end while

				// printArray("Sorted", x);
				return numSwaps;
			} // end bubbleSort

			public static void main(String[] args) {
				Scanner scan = new Scanner(System.in);
				int n = scan.nextInt();
				int[] arr = new int[n];
				for (int i = 0; i < n; i++) {
					arr[i] = scan.nextInt();
				}
				scan.close();
				int numSwaps = bubbleSort(arr);
				System.out.println(String.format("Array is sorted in %d swaps.\nFirst Element: %d\nLast Element: %d",
						numSwaps, arr[0], arr[arr.length - 1]));
			}
		}
	}

	static class Day21 {
		static class Printer<T> {
			/**
			 * Method Name: printArray Print each element of the generic array
			 * on a new line. Do not return anything.
			 * 
			 * @param A
			 *            generic array
			 **/

			// Write your code here
			void printArray(T[] arr) {
				for (T i : arr)
					System.out.println(i);
			}
		}

		static class Generics {

			public static void main(String args[]) {
				Scanner scanner = new Scanner(System.in);
				int n = scanner.nextInt();
				Integer[] intArray = new Integer[n];
				for (int i = 0; i < n; i++) {
					intArray[i] = scanner.nextInt();
				}

				n = scanner.nextInt();
				String[] stringArray = new String[n];
				for (int i = 0; i < n; i++) {
					stringArray[i] = scanner.next();
				}

				Printer<Integer> intPrinter = new Printer<Integer>();
				Printer<String> stringPrinter = new Printer<String>();
				intPrinter.printArray(intArray);
				stringPrinter.printArray(stringArray);
				if (Printer.class.getDeclaredMethods().length > 1) {
					System.out.println("The Printer class should only have 1 method named printArray.");
				}
			}
		}
	}

	static class Day22 {
		static class Node {
			Node left, right;
			int data;

			Node(int data) {
				this.data = data;
				left = right = null;
			}
		}

		static class Solution {
			public static int getHeight(Node current) {
				// Write your code here
				if (current == null)
					return -1;
				int height = 1, hl = getHeight(current.left), hr = getHeight(current.right);
				height = 1 + (hl > hr ? hl : hr);
				return height;
			}

			public static Node insert(Node root, int data) {
				if (root == null) {
					return new Node(data);
				} else {
					Node cur;
					if (data <= root.data) {
						cur = insert(root.left, data);
						root.left = cur;
					} else {
						cur = insert(root.right, data);
						root.right = cur;
					}
					return root;
				}
			}

			public static void main(String args[]) {
				Scanner sc = new Scanner(System.in);
				int T = sc.nextInt();
				Node root = null;
				while (T-- > 0) {
					int data = sc.nextInt();
					root = insert(root, data);
				}
				int height = getHeight(root);
				System.out.println(height);
				sc.close();
			}
		}
	}

	static class Day23 {
/*
inOrder(t) {
    if(t is not empty) {
        inOrder( left subtree of t )
        process t's root element
        inOrder( right subtree of t )
    } 
} 

postOrder(t) {
    if(t is not empty) {
        postOrder( left subtree of t )
        postOrder( right subtree of t )
        process t's root element
    } 
} 

Depth First Search
preOrder(t) {
    if(t is not empty) {
        process t's root element
        preOrder( left subtree of t )
        preOrder( right subtree of t )
    } 
} 

Breadth First Search
levelOrder(BinaryTree t) {
    if(t is not empty) {
        // enqueue current root
        queue.enqueue(t)
        
        // while there are nodes to process
        while( queue is not empty ) {
            // dequeue next node
            BinaryTree tree = queue.dequeue();
            
            process tree's root;
            
            // enqueue child elements from next level in order
            if( tree has non-empty left subtree ) {
                queue.enqueue( left subtree of t )
            }
            if( tree has non-empty right subtree ) {
                queue.enqueue( right subtree of t )
            }
        }
    } 
} 
*/
		static class Node implements Serializable, PrintableNode{
			/**
			 * 
			 */
			private static final long serialVersionUID = 9196711492381931450L;
			
			Node left, right;
			int data;

			Node(int data) {
				this.data = data;
				left = right = null;
			}

			@Override
			public PrintableNode getLeft() {
				return left;
			}

			@Override
			public PrintableNode getRight() {
				return right;
			}

			@Override
			public String getText() {
				return "" + data;
			}
		}

		public static Node insert(Node root, int data) {
			if (root == null) {
				return new Node(data);
			} else {
				Node cur;
				if (data <= root.data) {
					cur = insert(root.left, data);
					root.left = cur;
				} else {
					cur = insert(root.right, data);
					root.right = cur;
				}
				return root;
			}
		}

		public static void main(String args[]) {
			Scanner sc = new Scanner(System.in);
			int T = sc.nextInt();
			Node root = null;
			while (T-- > 0) {
				int data = sc.nextInt();
				root = insert(root, data);
			}
			sc.close();
			
			print(root);
			
//			System.out.print("inOrder:");
//			inOrder(root);
//			System.out.print("\npostOrder:");
//			postOrder(root);
//			System.out.print("\npreOrder:");
//			preOrder(root);
//			System.out.print("\nlevelOrder:");
			levelOrder(root);
		}
		
		private static void inOrder(Node n){
			if(n != null){
				inOrder(n.left);
				System.out.print(n.data + " ");
				inOrder(n.right);
			}
		}
		
		private static void postOrder(Node n){
			if(n != null){
				postOrder(n.left);
				postOrder(n.right);
				System.out.print(n.data + " ");
			}
		}
		
		private static void preOrder(Node n){
			if(n != null){
				preOrder(n.left);
				preOrder(n.right);
				System.out.print(n.data + " ");
			}
		}
		
		private static void levelOrder(Node n){
			Queue<Node> q = new LinkedList<Node>();
			if(n != null){
				q.add(n);
				
				while(!q.isEmpty()){
					n = q.remove();
					System.out.print(n.data + " ");
					if(n.left != null)
						q.add(n.left);
					if(n.right != null)
						q.add(n.right);
				}
			}
		}
	}
	
	static class Day24 {
		static class Node {
			int data;
			Node next;

			Node(int d) {
				data = d;
				next = null;
			}

		}
		private static SortedSet<Integer> set = new TreeSet<Integer>();
		
		public static Node removeDuplicates1(Node head) {
			// Write your code here
			Node cur = head, prev = null;
			while (cur != null) {
				if (!set.contains(cur.data)) {
					set.add(cur.data);
					prev = cur;
				} else {
					prev.next = cur.next;
				}
				cur = cur.next;
			}
			return head;
		}
		
		public static Node removeDuplicates2(Node head) {
			Node newHead = null;
			for(Node curr = head; curr != null; curr = curr.next){
				if(!set.contains(curr.data)){
					newHead = insert(newHead, curr.data);
					set.add(curr.data);
				}
			}
			return newHead;
		}

		public static Node insert(Node head, int data) {
			Node p = new Node(data);
			if (head == null)
				head = p;
			else if (head.next == null)
				head.next = p;
			else {
				Node start = head;
				while (start.next != null)
					start = start.next;
				start.next = p;

			}
			return head;
		}

		public static void display(Node head) {
			Node start = head;
			while (start != null) {
				System.out.print(start.data + " ");
				start = start.next;
			}
		}

		public static void main(String args[]) {
			Scanner sc = new Scanner(System.in);
			Node head = null;
			int T = sc.nextInt();
			while (T-- > 0) {
				int ele = sc.nextInt();
				head = insert(head, ele);
			}
			head = removeDuplicates1(head);
			display(head);

		}
	}

	static class Day25 {
		public static void main(String[] args) {
			/*
			 * Enter your code here. Read input from STDIN. Print output to
			 * STDOUT. Your class should be named Solution.
			 */
			Scanner scanner = new Scanner(System.in);
			int n = scanner.nextInt();
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = scanner.nextInt();
			}
			for(int i : arr){
				if(!isPrime(i))
					System.out.println(i + ":Not prime");
				else
					System.out.println(i + ":Prime");
			}
			scanner.close();
		}

		private static boolean isPrime(int i) {
			if(i < 2)
				return false;
			else{
				if(i % 2 == 0)
					return false;
				else {
					boolean ret = true;
					double j = i;//Math.sqrt(i);
					while(j-- > 2){
						if(i%j == 0){
							ret = false;
							break;
						}
					}
/*					double sq = Math.sqrt(i);
					for(int j = 2; i <= sq; j++) {
					    if(i%j == 0){ //NOT PRIME
					    	ret = false;
					    	break;
					    }
					}
*/					return ret;
				}
			}
		}
	}

	static class Day26 {
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			int aD = scanner.nextInt();
			int aM = scanner.nextInt();
			int aYYYY = scanner.nextInt();
			int eD = scanner.nextInt();
			int eM = scanner.nextInt();
			int eYYYY = scanner.nextInt();
			scanner.close();

			try {
				java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy");
				java.time.LocalDate eDate = java.time.LocalDate.parse(String.format("%02d%02d%d", eD, eM, eYYYY),
						formatter);
				java.time.LocalDate aDate = java.time.LocalDate.parse(String.format("%02d%02d%d", aD, aM, aYYYY),
						formatter);
				long fine = 0;
				long years = java.time.temporal.ChronoUnit.YEARS.between(eDate, aDate);
				long months = java.time.temporal.ChronoUnit.MONTHS.between(eDate, aDate);
				long days = java.time.temporal.ChronoUnit.DAYS.between(eDate, aDate);
				if (years > 0) {
					fine = 10000;
				} else if (months > 0) {
					fine = 500 * months;
				} else if (days > 0) {
					fine = 15 * days;
				} else if (eDate.equals(aDate)) {
					fine = 0;
				}

				System.out.println(fine);
			} catch (Exception e) {
				System.out.println(0);
			}
		}
	}

	static class Day28 {
		public static void main(String[] args){
			Scanner scanner = new Scanner(System.in);
			int n = scanner.nextInt();
			if(n < 2 || n > 30){
				scanner.close();
				return;
			}
			String[][] data = new String[n][2];
			while(n-- > 0){
				data[n][0] = scanner.next();
				data[n][1] = scanner.next();
			}
			scanner.close();
			
			java.util.Arrays.sort(data, new java.util.Comparator<String[]>() {
			    public int compare(String[] a, String[] b) {
			        return b[0].compareTo(a[0]);
			    }
			});
			
			java.util.regex.Pattern fn = java.util.regex.Pattern.compile("^[a-z]{1,20}");
			java.util.regex.Pattern e = java.util.regex.Pattern.compile("([a-z]+)@([a-z]+\\.[a-z]+){1}");
			java.util.regex.Pattern gmail = java.util.regex.Pattern.compile("([a-z])+@gmail.com");
			for(int i = data.length - 1; i >= 0; i--){
				if(fn.matcher(data[i][0]).matches() && 
						e.matcher(data[i][1]).matches() && 
						gmail.matcher(data[i][1]).matches()
					)
                    System.out.println(data[i][0]);
			}
		}
	}

	static class Day29 {
		static class BinaryString {

			BinaryString(String string, Integer integer) {
				for (byte b : string.getBytes()) {
					// Perform a bitwise operation using byte and integer
					// operands, save result as tmp:
					int tmp = b | integer;
					System.out.println(Integer.toBinaryString(b) + " OR " + Integer.toBinaryString(integer) + " = "
							+ Integer.toBinaryString(tmp) + " = " + tmp);
				}
			}

			public static void main(String[] args) {
				new BinaryString("HackerRank", 8675309);
			}
		}
		
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			int t = scanner.nextInt();
			if(t < 1 || t > Math.pow(10, 3)){
				scanner.close();
				return;
			}
			int[][] data = new int[t][2];
			while(t-- > 0){
				data[t][0] = scanner.nextInt();
				data[t][1] = scanner.nextInt();
			}
			for(int i = data.length - 1; i >= 0; i--){
				if (2 <= data[i][0] && data[i][0] <= Math.pow(10, 3) && 2 <= data[i][1] && data[i][1] <= data[i][0]) {
					int result = 0;
					for (int a = 1; a <= data[i][0] - 1; a++) {
						for (int b = a + 1; b <= data[i][0] - 1; b++) {
							int ab = a & b;
							System.out.print(String.format("%d & %d = %d : ", a, b, ab));
							/*System.out.println(Integer.toBinaryString(a) + " AND " + Integer.toBinaryString(b) + " = "
									+ Integer.toBinaryString(ab) + " = " + ab);*/
							if(ab > result && ab < data[i][1]){
								System.out.println(String.format("%d > %d && %d < %d", ab, result, result, data[i][1]));
								result = ab;
							}
						}
					}
					System.out.println(result);
				}
			}
			
			scanner.close();
		}
	}
}
