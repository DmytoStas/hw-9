package words.counter;

import java.io.File;
import java.io.IOException;

public interface Countable {
    void wordCounter(File file) throws IOException;
    void printResult();
}
