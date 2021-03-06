package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vlad on 21.11.2016.
 */
public class EmotionClass {
    private final String NAME;

    private int wordsCount;
    private Map<String, Integer> tokenCount = new HashMap<>();

    public EmotionClass(String name) {
        NAME = name;
    }

    public void addData(List<String> words) {
        for (String word : words) {
            if (tokenCount.containsKey(word)) {
                int count = tokenCount.get(word);
                tokenCount.replace(word, ++count);
            } else {
                tokenCount.put(word, 1);
            }
        }
        wordsCount += words.size();
    }

    @Override
    public String toString() {
        return NAME;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public Map<String, Integer> getTokenCount() {
        return tokenCount;
    }
}
