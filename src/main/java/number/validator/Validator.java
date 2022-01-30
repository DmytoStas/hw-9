package number.validator;

import java.io.File;
import java.io.IOException;


public interface Validator {
    void checkValidNumbers(File file) throws IOException;
}
