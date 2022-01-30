package words.counter;

import java.io.File;

public interface Countable {
    void wordCounter(File file);

    void printResult();
}
