package number.validator;

import java.io.File;
import java.io.IOException;


public class NumberValidatorTest {

    public static void main(String[] args) throws IOException {

        Validator numbers = new NumberValidator();

        numbers.checkValidNumbers(new File(".//src//main//resources//numberList.txt"));

        System.out.println(numbers);

    }
}



