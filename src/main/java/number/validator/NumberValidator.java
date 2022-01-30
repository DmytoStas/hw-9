package number.validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class NumberValidator<E> implements Validator {

    //Поле allNumbersArray - це колекція яка зберігає всі номери з прочинаого файлу
    private final List<E> allNumbersArray = new ArrayList<>();

    //Поле validNumber накопичує тільки валідні номери
    private final StringJoiner validNumber = new StringJoiner("\n");

    @Override
    public void checkValidNumbers(File file) throws IOException {
        /*
        *Метод отримує на вхід файл із списком номерів і записує кожен рядок у колекцію allNumbersArray
        * Закривамо вчідний файл після виконання циклу
        * Після цього запускаємо метод saveValidNumbers
        */

        InputStream fileInputStream = new FileInputStream(String.valueOf(file));
        Scanner scanner = new Scanner(fileInputStream);

        while (scanner.hasNextLine()) {
            allNumbersArray.add((E) scanner.nextLine());
        }
        fileInputStream.close();

        saveValidNumbers();
    }

    public void saveValidNumbers() {
        /*
        * Метод має в собі поле arr - це масив з колекції allNumbersArray
        * Пробігаємося по масиву і кожен елемент перевіряємо утилітним методом validationChecker,
        * якщо він повертає true то записуємо елемент в поле класу validNumber
         */

        String[] arr = allNumbersArray.toArray(new String[0]);

        for (String element : arr) {
            if (validationChecker(element)) {
                validNumber.add(element);
            }
        }
    }

    public static boolean validationChecker(String number) {
        //Метод отримує на вхід номер і форматує його в масив itemsChar
        //Якщо виконується умови для цього масиву то повертається true

        char[] itemsChar;
        if ((itemsChar = number.toCharArray()).length == 14) {
            if ((itemsChar[0] == '(' && itemsChar[4] == ')') && itemsChar[9] == '-') {
                return true;
            }
        } else if ((itemsChar = number.toCharArray()).length == 12) {
            if (itemsChar[3] == '-' && itemsChar[7] == '-') {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Valid numbers: \n" + validNumber;
    }
}
