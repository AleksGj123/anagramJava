package app;

import filereader.DefaultFileReader;
import filereader.FileReader;
import service.AnagramService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Application {

    public static final String ModeParallel = "parallel";
    public static final String ModeSimple = "simple";
    public static final Integer SplitThreshold = 7;

    private final AnagramService anagramService;
    private final FileReader fileReader;

    // input options
    public static final String option1 = "1";
    public static final String option2 = "2";
    public static final String option3 = "3";

    public Application(
            AnagramService anagramService,
            DefaultFileReader fileReader
    ) {
        this.anagramService = anagramService;
        this.fileReader = fileReader;
    }

    public void runAnagram() {
        System.out.println("Select on of the following options:");
        System.out.println("(1) solve sample");
        System.out.println("(2) solve 100k words");
        System.out.println("(3) solve ~8 million words");

        Scanner scanner = new Scanner(System.in);
        String keyboardInput = scanner.next();
        Map<String, List<String>> stringListMap = null;
        try {
            List<String> input;
            switch (keyboardInput) {
                case option1 -> {
                    input = fileReader.readSampleFile();
                    stringListMap = runAnagramAndMeasure(input);
                }
                case option2 -> {
                    input = fileReader.readLargeFile();
                    stringListMap = runAnagramAndMeasure(input);
                }
                case option3 -> {
                    input = fileReader.readVeryLargeFile();
                    stringListMap = runAnagramAndMeasure(input);
                }
                default -> System.out.println("No valid input [" + keyboardInput + "]");
            }
            if (stringListMap != null) {
                System.out.println("result:" + stringListMap.values());
            }
        } catch (Exception exception) {
            System.out.println("Error found:" + exception.getMessage());
        }
    }

    private Map<String, List<String>> runAnagramAndMeasure(List<String> input) throws ExecutionException, InterruptedException {
        Map<String, List<String>> stringListMap;
        long start = System.currentTimeMillis();
        stringListMap = anagramService.groupAnagrams(input);
        long stop = System.currentTimeMillis();
        System.out.println("Took:" + (stop - start));
        return stringListMap;
    }
}
