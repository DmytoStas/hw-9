package json.saver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonFileCreator implements FileCreateable {


    /*
     * Метод readInfo()
     * Цей метод приймає файл
     * Читаємо вхідний файл і записуємо кожну строку в колекцію та закриваємо вхідний файл
     */
    @Override
    public List<String> readInfo(File file) {

        List<String> elements = new ArrayList<>();

        try (FileReader fileInputStream = new FileReader(String.valueOf(file))) {

            Scanner scanner = new Scanner(fileInputStream);

            while (scanner.hasNextLine()) {
                elements.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return elements;
    }


    /*
     * Метод jsonFileCreate()
     * На вхід приходить колекція із строками з методу readInfo()

     * Циклом проходимося по всій колекції починаючи з індексу 1(так як індекс 0 це назви полів, іх пропускаємо)
       і для кожного елементу колекції в циклі виконуємо таку логіку:
            - сплітимо в String масив по пробілу
            - беремо індекс 0 і 1 цього масиву і підставляємо в конструктор обєкту person
            - додаємо в нову колекцію кожен обєкт person з отриманими параметрами

     * Після закінчення роботи циклу записуємо колекцію з обєктами persons
       з допомогою утилітного методу writeJsonFile()
     */
    @Override
    public void jsonFileCreate(List<String> elements) {

        String[] splitElements;

        List<Object> persons = new ArrayList<>();

        Person person;

        for (int i = 1; i < elements.size(); i++) {
            splitElements = elements.get(i).split(" ");
            person = new Person(splitElements[0], Integer.parseInt(splitElements[1]));
            persons.add(person);
        }

        writeJsonFile(persons);
    }


    /*
     * Метод writeJsonFile()
     * На вхід отримуємо колекцію з обєктами persons
     * З допомогою бібліотеки Gson форматуємо колекцію у правильний формат Json в String обєкт
     * Записуємо відформатований обєкт у новий файл
     * Закриваємо файл після виконання роботи
     */
    public static void writeJsonFile(List<Object> persons) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonString = gson.toJson(persons);

        try (FileWriter writer = new FileWriter(".//src//main//resources//user.json")) {
            writer.write(jsonString);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Json file created!");
    }
}
