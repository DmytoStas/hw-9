package words.counter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class WordsCounter implements Countable {
    //Цей клас має 2 поля:

    // - maxWordCount зберігає в собі число - найбільша кількісить дублікатів
    private static int maxWordCount;

    // - sortedArr зберігає в собі колекцію - результ виконаної роботи всього класу
    private static ArrayList<String> sortedArr = new ArrayList<>();

    @Override
    public void wordCounter(File file) throws IOException {
        //Цей метод приймає файл із словами і за допомогою утилітного методу readInfo() формується масив wordList
        //Метод має внутрішні поля uniqueWords (для збору унікальних слів) і
        //listOfCountedWords (для збору унікальхих слів + число - кількісь дублікатів цього слова)
        //Колекція uniqueWords приймає в себе масив всіх слів wordList і зберігає тільки унікальні екземпляри
        //Поле count потрібне для підрагунку дублікатів
        //Циклом проходимося по кожному елементу колекції uniqueWords при цьому:
        // - порівнюємо чи  maxWordCount менший за count, якщо так то перезапиши maxWordCount
        // - обнулюємо поле count
        // - вкладеним циклом пробігаємося по масиву всих слів і порівнюємо їх з елементом uniqueWord
        //якщо вониспівпадаються, то count + 1
        // - додаємо елемент(який зараз на перевірці) + count(його кількість дублікатів у реченні)
        // в нову колекцію listOfCountedWords
        //По завершенню роботи циклу викликаємо метод sort(), який сортує колекцію listOfCountedWords
        //по кількості слів від більшого значення до меншого

        String[] wordList = readInfo(file);

        Collection<String> uniqueWords = new HashSet<>(Arrays.asList(wordList));

        List<String> listOfCountedWords = new ArrayList<>();

        int count = 0;
        for (Object uniqueWord : uniqueWords) {
            if (maxWordCount < count) {
                maxWordCount = count;
            }
            count = 0;
            for (String word : wordList) {
                if (uniqueWord.equals(word)) {
                    count++;
                }
            }
            listOfCountedWords.add(uniqueWord + " " + count);
        }
        sort(listOfCountedWords);
    }

    public static String[] readInfo(File file) throws IOException {
        //Цей утилітний метод приймає файл із словами
        //Читаємо вхідний файл і записуємо кожну строку в joiner
        //та закриваємо вхідний файл
        //Метод повертає масив слів

        StringJoiner joiner = new StringJoiner(" ");

        InputStream fileInputStream = new FileInputStream(String.valueOf(file));
        Scanner scanner = new Scanner(fileInputStream);

        while (scanner.hasNextLine()) {
            joiner.add(scanner.nextLine());
        }
        fileInputStream.close();

        return joiner.toString().split("\\s+");
    }

    public static void sort(List<String> countedList) {
        //Метод утилітний приймає колекцію countedList із порахованими словами
        //В методі є поле wordsArr - строковий масив
        //З допомогою циклу, який працює як лічильник від значення maxWordCount в сторону зменшення до 1-ці, виконуємо алгоритм:
        // - владеним циклом проходимося по колекції countedList і кожен element сплітиться в wordsArr
        // - якщо значення лічильника дорівнює числу із element(кількість слів), то записуємо його в поле класу sortedArr

        String[] wordsArr;
        for (int p = maxWordCount; p >= 1; p--) {
            for (String element : countedList) {
                wordsArr = element.split("\\s+");
                if (p == Integer.parseInt(wordsArr[1])) {
                    sortedArr.add(element);
                }
            }
        }
    }

    @Override
    public void printResult() {
        //Метод виводить в консоль кожен елемент поля класу sortedArr в новий рядок
        for (Object element : sortedArr) {
            System.out.println(element);
        }
    }
}
