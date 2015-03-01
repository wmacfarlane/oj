import java.util.Scanner;

class Test {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int x = sc.nextInt();
			if (x == 42)
				break;
			System.out.println(x);
		}
	}
}
