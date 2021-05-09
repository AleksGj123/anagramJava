package filereader;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DefaultFileReader implements FileReader {

    @Override
    public List<String> readSampleFile() throws IOException {
        return getStringList("sample.txt");
    }

    @Override
    public List<String> readLargeFile() throws IOException {
        return getStringList("100000.txt");
    }

    @Override
    public List<String> readVeryLargeFile() throws IOException {
        return getStringList("8m.txt");
    }

    private ArrayList<String> getStringList(String fileName) throws IOException {
        InputStream resourceAsStream = DefaultFileReader.class.getResourceAsStream("/" + fileName);

        final ArrayList<String> result = new ArrayList<>();
        if (resourceAsStream != null) {
            Scanner scanner = new Scanner(resourceAsStream);
            while (scanner.hasNext()) {
                String next = scanner.next();
                result.add(next);
            }
            return result;
        } else {
            throw new IOException("could not get sample.txt");
        }
    }
}
