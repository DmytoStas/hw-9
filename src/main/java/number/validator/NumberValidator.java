package number.validator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class NumberValidator implements Validator {
    //Цей клас має 2 поля:

    //Поле allNumbersArray - це колекція яка зберігає всі номери з прочинаого файлу
    private final List<String> allNumbersList = new ArrayList<>();

    //Поле validNumber накопичує тільки валідні номери
    private final StringJoiner validNumber = new StringJoiner("\n");


    /*
     * Метод checkValidNumbers()
     * Метод отримує на вхід файл із списком номерів і записує кожен рядок у колекцію allNumbersArray
     * Закривамо вчідний файл після виконання циклу
     * Після цього запускаємо метод saveValidNumbers
     */
    @Override
    public void checkValidNumbers(File file) {

        try (FileReader fileInputStream = new FileReader(String.valueOf(file))) {

            Scanner scanner = new Scanner(fileInputStream);

            while (scanner.hasNextLine()) {
                allNumbersList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        saveValidNumbers();
    }


    /*
     * Метод saveValidNumbers()
     * Метод має в собі поле arr - це масив з колекції allNumbersArray
     * Пробігаємося по масиву і кожен елемент перевіряємо утилітним методом validationChecker,
     * якщо він повертає true то записуємо елемент в поле класу validNumber
     */
    public void saveValidNumbers() {

        String[] arr = allNumbersList.toArray(new String[0]);

        for (String element : arr) {
            if (validationChecker(element)) {
                validNumber.add(element);
            }
        }
    }


    /*
     * Метод validationChecker()
     * Метод отримує на вхід номер і форматує його в масив itemsChar
     * Якщо виконується умови для цього масиву то повертається true
     */
    public static boolean validationChecker(String number) {

        char[] itemsChar;

        if ((itemsChar = number.toCharArray()).length == 14) {
            return (itemsChar[0] == '(' && itemsChar[4] == ')') && itemsChar[9] == '-';
        } else if ((itemsChar = number.toCharArray()).length == 12) {
            return itemsChar[3] == '-' && itemsChar[7] == '-';
        }
        return false;
    }

    @Override
    public String toString() {
        return "Valid numbers: \n" + validNumber;
    }
}
