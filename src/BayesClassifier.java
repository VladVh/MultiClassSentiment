import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vlad on 21.11.2016.
 */
public class BayesClassifier {
    private static Map<String, List<String>> filesData;

    private static int docsCount = 0;
    private static Set<String> vocabulary = new HashSet<>();

    private static final EmotionClass[] emotions = new EmotionClass[] {new EmotionClass("A"), new EmotionClass("B"),
            new EmotionClass("C"),new EmotionClass("C'"),new EmotionClass("D"),new EmotionClass("E"),new EmotionClass("E'"),
            new EmotionClass("F"),new EmotionClass("G"),new EmotionClass("H"),new EmotionClass("I"),new EmotionClass("J"),
            new EmotionClass("K"),new EmotionClass("L"),new EmotionClass("L'"),new EmotionClass("M"),new EmotionClass("N"),
            new EmotionClass("N'"),new EmotionClass("O"),new EmotionClass("O'"),new EmotionClass("P"),new EmotionClass("Q"),
            new EmotionClass("R"),new EmotionClass("S"),new EmotionClass("T"),new EmotionClass("U"),new EmotionClass("V"),
            new EmotionClass("W"),new EmotionClass("X"),new EmotionClass("Y"),new EmotionClass("Y'"),new EmotionClass("Z")};

    private static final String PARAMS_PATTERN = "([A-Z']+(\\s)*([0-9]{1},*)+)";
    private static final Pattern SEGMENT_PATTERN = Pattern.compile(PARAMS_PATTERN);

    public BayesClassifier(Map<String, List<String>> data) {
        this.filesData = data;
    }

    public static void train(Map<String, List<String>> data) {
        for (List<String> fragments : data.values()) {
            for (String fragment : fragments) {
                Matcher matcher = SEGMENT_PATTERN.matcher(fragment);
                String params = matcher.group();

                List<EmotionClass> emotionClasses = parseParameters(params);

                String words = fragment.substring(0, fragment.indexOf(params));
                String[] wordsList = words.split(" ");

                for (String word : wordsList) {
                    vocabulary.add(word);
                }
                for (EmotionClass emotionClass : emotionClasses) {
                    emotionClass.addData(wordsList);
                }
//                while (matcher1.find()) {
//
//                }
            }
            docsCount++;
        }
    }

    private static List<EmotionClass> parseParameters(String params) {
        char prev = ' ';
        List<EmotionClass> emotionClasses = new ArrayList<>();
        for (char ch : params.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                emotionClasses.add(getEmotionByString(String.valueOf(ch)));
            } else if (Character.isDigit(ch)) {

            } else if (ch == '\'') {
                emotionClasses.add(getEmotionByString(String.valueOf(prev + '\'')));
            }
            prev = ch;
        }
        return emotionClasses;
    }


    private static EmotionClass getEmotionByString(String name) {
        for (EmotionClass emotionClass : emotions) {
            if (emotionClass.toString().equals(name)) {
                return emotionClass;
            }
        }
        return null;
    }
}
