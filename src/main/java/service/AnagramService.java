package service;

import java.util.*;
import java.util.concurrent.ExecutionException;

public interface AnagramService {
    /**
     * Takes a list of strings as input and groups words (strings) with the same letters
     *
     * @param input  a list of Strings
     * @return a map with the sorted letters to list of the grouped words
     */
    Map<String, List<String>> groupAnagrams(List<String> input) throws ExecutionException, InterruptedException;

    static HashMap<String, List<String>> doGroupAnagrams(List<String> input) {
        HashMap<String, List<String>> anagrams = new HashMap<>();

        for (String element : input) {
            char[] chars = element.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);

            List<String> anagramList = anagrams.get(sortedString);
            if (anagramList == null) {
                List<String> newAnagramList = new ArrayList<>();
                newAnagramList.add(element);
                anagrams.put(sortedString, newAnagramList);
            }else{
                anagramList.add(element);
            }
        }
        return anagrams;
    }

}
