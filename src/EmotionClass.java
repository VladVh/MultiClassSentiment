import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vlad on 21.11.2016.
 */
public class EmotionClass {
    private final String NAME;

    private int wordsCount;
    private Map<String, Integer> tokenCount = new HashMap<>();
    private Map<String, Double> condProbabilities;

    public EmotionClass(String name) {
        NAME = name;
    }

    public void addData(String[] words) {
        for (String word : words) {
            if (tokenCount.containsKey(word)) {
                int count = tokenCount.get(word);
                tokenCount.replace(word, ++count);
            } else {
                tokenCount.put(word, 1);
            }
        }
        wordsCount += words.length;
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

    public Map<String, Double> getCondProbabilities() {
        return condProbabilities;
    }

    public void setCondProbabilities(Map<String, Double> condProbabilities) {
        this.condProbabilities = condProbabilities;
    }
}
