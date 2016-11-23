package model;

import java.util.List;

/**
 * Created by v.voitsekh on 23.11.2016.
 */
public class ParamsInfo {
    private List<String> words;
    private List<String> emotions;

    public ParamsInfo(List<String> words, List<String> emotions) {
        this.words = words;
        this.emotions = emotions;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getEmotions() {
        return emotions;
    }
}
