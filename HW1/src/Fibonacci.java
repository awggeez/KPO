import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        fibonacci(number);
    }
    public static void fibonacci(int num) {
        int currentNumber = 0;
        int nextNumber = 1;
        if (num < 3) {
            if (num == 1) {
                System.out.println(currentNumber);
            } else if (num == 2) {
                System.out.println(currentNumber + ", " + nextNumber);
            }
        }
        System.out.print(currentNumber + ", " + nextNumber);
        int sum;
        for (int i = 0; i < num - 2; i++) {
            sum = currentNumber + nextNumber;
            System.out.print(", " + sum);
            currentNumber = nextNumber;
            nextNumber = sum;
        }
    }
}
