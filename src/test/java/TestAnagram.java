import org.junit.Test;
import service.ParallelAnagramService;
import service.SimpleAnagramService;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class TestAnagram {

    final List<String> input = Arrays.asList("act", "cat", "tree", "race", "care", "acre", "bee");
    final List<String> expectedPartResult1 = Arrays.asList("act", "cat");
    final List<String> expectedPartResult2 = Arrays.asList("race", "care", "acre");
    final List<String> expectedPartResult3 = Collections.singletonList("tree");
    final List<String> expectedPartResult4 = Collections.singletonList("bee");


    @Test
    public void testSimpleAnagram() {

        SimpleAnagramService anagramService = new SimpleAnagramService();
        HashMap<String, List<String>> listHashMap = anagramService.groupAnagrams(input);
        List<List<String>> result = new ArrayList<>(listHashMap.values());

        assert (result.contains(expectedPartResult1));
        assert (result.contains(expectedPartResult2));
        assert (result.contains(expectedPartResult3));
        assert (result.contains(expectedPartResult4));

        assert (4 == result.size());
    }

    @Test
    public void testParallelAnagram() {

        ParallelAnagramService anagramService = new ParallelAnagramService();
        Map<String, List<String>> listHashMap = null;
        try {
            listHashMap = anagramService.groupAnagrams(input);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (listHashMap != null){
            List<List<String>> result = new ArrayList<>(listHashMap.values());

            assert (result.contains(expectedPartResult1));
            assert (result.contains(expectedPartResult2));
            assert (result.contains(expectedPartResult3));
            assert (result.contains(expectedPartResult4));

            assert (4 == result.size());
        }
    }
}
