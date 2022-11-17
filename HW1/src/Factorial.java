public class Factorial {
    public static void main(String[] args) {
        factorial(6);
    }

    public static void factorial(int num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        System.out.println(result);
    }
}
