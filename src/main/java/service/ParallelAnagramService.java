package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import app.Application;

public class ParallelAnagramService implements AnagramService {
    @Override
    public Map<String, List<String>> groupAnagrams(List<String> input) throws ExecutionException, InterruptedException {

        // split the list into small and longer words
        List<String> shotWords = input.stream().filter(s -> s.length() <= Application.SplitThreshold)
                .collect(Collectors.toList());
        List<String> longerWords = input.stream().filter(s -> s.length() > Application.SplitThreshold)
                .collect(Collectors.toList());

        System.out.println("short words size " + shotWords.size());
        System.out.println("longer words size " + longerWords.size());

        // compute them async / parallel
        CompletableFuture<HashMap<String, List<String>>> future1 = CompletableFuture.supplyAsync(() ->
                AnagramService.doGroupAnagrams(shotWords)
        );
        CompletableFuture<HashMap<String, List<String>>> future2 = CompletableFuture.supplyAsync(() ->
                AnagramService.doGroupAnagrams(longerWords)
        );

        // merge the two maps together in the end
        CompletableFuture<Map<String, List<String>>> resultFuture = future1.thenCombine(future2, ((map1, map2) ->
                Stream.concat(map1.entrySet().stream(), map2.entrySet()
                        .stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        ));
        return resultFuture.get();
    }
}
