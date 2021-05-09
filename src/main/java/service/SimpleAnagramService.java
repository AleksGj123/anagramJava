package service;

import java.util.HashMap;
import java.util.List;

public class SimpleAnagramService implements AnagramService{
    @Override
    public HashMap<String, List<String>> groupAnagrams(List<String> input) {
        return AnagramService.doGroupAnagrams(input);
    }
}
