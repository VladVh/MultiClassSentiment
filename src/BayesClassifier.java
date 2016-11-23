import java.util.*;

/**
 * Created by Vlad on 21.11.2016.
 */
public class BayesClassifier {
    private static int docsCount = 0;
    private static Set<String> vocabulary = new HashSet<>();

    private static final EmotionClass[] emotions = new EmotionClass[] {new EmotionClass("A"), new EmotionClass("B"),
            new EmotionClass("C"),new EmotionClass("C'"),new EmotionClass("D"),new EmotionClass("E"),new EmotionClass("E'"),
            new EmotionClass("F"),new EmotionClass("G"),new EmotionClass("H"),new EmotionClass("I"),new EmotionClass("J"),
            new EmotionClass("K"),new EmotionClass("L"),new EmotionClass("L'"),new EmotionClass("M"),new EmotionClass("N"),
            new EmotionClass("N'"),new EmotionClass("O"),new EmotionClass("O'"),new EmotionClass("P"),new EmotionClass("Q"),
            new EmotionClass("R"),new EmotionClass("S"),new EmotionClass("T"),new EmotionClass("U"),new EmotionClass("V"),
            new EmotionClass("W"),new EmotionClass("X"),new EmotionClass("Y"),new EmotionClass("Y'"),new EmotionClass("Z")};

    private static Map<String, Double> likelihoodPerClass;


    public static void train(List<List<ParamsInfo>> data) {
        for (List<ParamsInfo> fragments : data) {
            for (ParamsInfo info : fragments) {

                List<EmotionClass> emotionClasses = new ArrayList<>();
                for (String emotionString : info.getEmotions()) {
                    emotionClasses.add(getEmotionByString(emotionString));
                }

                List<String> wordsList = info.getWords();
                for (String word : wordsList) {
                    vocabulary.add(word);
                }
                for (EmotionClass emotionClass : emotionClasses) {
                    emotionClass.addData(wordsList);
                }
            }
            //docsCount++;
        }

    }

//    private static void train() {
//        for (EmotionClass emotionClass : emotions) {
//            int wordsCount = emotionClass.getWordsCount();
//            Map<String, Double> condProbabilities = new HashMap<>();
//
//            Map<String, Integer> tokens = emotionClass.getTokenCount();
//            for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
//                //double value = entry.getValue() + 1
//            }
//        }
//    }




    private static EmotionClass getEmotionByString(String name) {
        for (EmotionClass emotionClass : emotions) {
            if (emotionClass.toString().equals(name)) {
                return emotionClass;
            }
        }
        return null;
    }

    public static void classify(Map<String, List<String>> data) {
        for (List<String> words : data.values()) {
            likelihoodPerClass = new HashMap<>();

            for (EmotionClass emotionClass : emotions) {
                int wordsCount = emotionClass.getWordsCount();
                Map<String, Integer> tokenCount = emotionClass.getTokenCount();
                double totalValue = 0;
                double value;
                for (String word : words) {
                    value = Math.log((tokenCount.get(word) + 1) / (wordsCount + vocabulary.size()));
                    totalValue += value;
                }
                likelihoodPerClass.put(emotionClass.toString(), totalValue);
            }

            Map.Entry<String, Double> max = likelihoodPerClass.entrySet().iterator().next();
            for (Map.Entry<String, Double> entry : likelihoodPerClass.entrySet()) {
                if (entry.getValue() > max.getValue()) {
                    max = entry;
                }
            }
            System.out.println(max.getKey() + " " + max.getValue());
        }

        //compare

    }
}
