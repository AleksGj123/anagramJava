package filereader;

import java.io.IOException;
import java.util.List;

public interface FileReader {
    List<String> readSampleFile() throws IOException;
    List<String> readLargeFile() throws IOException;
    List<String> readVeryLargeFile() throws IOException;
}
