package json.saver;

import java.io.File;
import java.util.List;

public class JsonSaverTest {
    public static void main(String[] args) {

        FileCreateable newJsonFile = new JsonFileCreator();

        List<String> readLine = newJsonFile.readInfo(new File(".//src//main//resources//jsonList.txt"));

        newJsonFile.jsonFileCreate(readLine);
    }
}
