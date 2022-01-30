package words.counter;

import java.io.File;
import java.io.IOException;

public class WordCounterTest {
    public static void main(String[] args) throws IOException {

        Countable sentence = new WordsCounter();

        sentence.wordCounter(new File(".//src//main//resources//wordsList.txt"));

        sentence.printResult();
    }
}
