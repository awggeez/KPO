package app;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static String fileName;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите полное имя файла: ");
        fileName = scanner.nextLine();
//        StringBuilder line = new StringBuilder();
        try {
            FileIterator fileIterator = new FileIterator(fileName);
            while (fileIterator.hasNext()) {
//                если вдруг надо склеить все строки файла
//                line.append(fileIterator.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error!");
            return;
        }
//        System.out.println(line);
    }
}
