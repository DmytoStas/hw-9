package words.counter;

import java.io.File;

public class WordCounterTest {
    public static void main(String[] args) {

        Countable sentence = new WordsCounter();

        sentence.wordCounter(new File(".//src//main//resources//wordsList.txt"));

        sentence.printResult();
    }
}
