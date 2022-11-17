import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int length = scanner.nextInt();

        System.out.println("Введите элементы массива: ");
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("До BubbleSort: ");
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();

        bubbleSort(array);

        System.out.println("После BubbleSort: ");
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
    
    public static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}
