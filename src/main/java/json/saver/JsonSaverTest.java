package json.saver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonSaverTest {
    public static void main(String[] args) throws IOException {

        FileCreateable newJsonFile = new JsonFileCreator();

        List<String> readLine = newJsonFile.readInfo(new File(".//src//main//resources//jsonList.txt"));

        newJsonFile.jsonFileCreate(readLine);
    }
}
