package json.saver;

import java.io.File;
import java.util.List;

public interface FileCreateable {
    List<String> readInfo(File file);

    void jsonFileCreate(List<String> elements);
}
