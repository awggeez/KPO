import com.sun.tools.javac.Main;

import java.util.Scanner;

public class Letters {
    private static final String VOWELS_RU = "аеёиоуыэюяАЕЁИОУЫЭЮЯ";
    private static final String NOT_VOWELS_RU = "бвгджзйклмнпрстфхцчшщБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";
    private static final String VOWELS_EN = "aeiouyAEIOUY";
    private static final String NOT_VOWELS_EN = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();
        countVowelsAndNotVowelsLetters(sentence);
    }

    public static void countVowelsAndNotVowelsLetters(String sentence) {
        int countVowels = 0;
        int countNotVowels = 0;
        for (int i = 0; i < sentence.length(); i++) {
            String letter = Character.toString(sentence.charAt(i));
            if (VOWELS_RU.contains(letter) || VOWELS_EN.contains(letter)) {
                countVowels++;
            } else if (NOT_VOWELS_RU.contains(letter) || NOT_VOWELS_EN.contains(letter)) {
                countNotVowels++;
            }
        }
        System.out.println("Гласных букв: " + countVowels + "\n" + "Согласных букв: " + countNotVowels);
    }
}