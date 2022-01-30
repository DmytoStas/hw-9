package number.validator;

import java.io.File;

public class NumberValidatorTest {

    public static void main(String[] args) {

        Validator numbers = new NumberValidator();

        numbers.checkValidNumbers(new File(".//src//main//resources//numberList.txt"));

        System.out.println(numbers);

    }
}



