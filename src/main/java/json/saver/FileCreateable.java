package json.saver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileCreateable {
    List<String> readInfo(File file) throws IOException;
    void jsonFileCreate(List<String> elements) throws IOException;
}
