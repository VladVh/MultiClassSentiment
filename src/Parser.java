import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vlad on 19.11.2016.
 */
public class Parser {
    private static final String STRING_REGEX = "<(.+?)>";
    private static final String WORDS_REGEX = "[a-zA-Z']{2,20}";
    private static final String PARAMS_REGEX = "([A-Z']+(\\s)*([0-9]{1,2},*)+)";

    private static final Pattern FILE_PATTERN = Pattern.compile(STRING_REGEX);
    private static final Pattern WORDS_PATTERN = Pattern.compile(WORDS_REGEX);
    private static final Pattern SEGMENT_PATTERN = Pattern.compile(PARAMS_REGEX);

    private static final String STOP_WORDS_PATH = "stopWords.txt";
    private static final Set<String> stopWords = new TreeSet<>();

    static {
        String content = FileUtils.readFileToString(STOP_WORDS_PATH, "UTF-8");
        String[] words = content.split(" ");
        Collections.addAll(stopWords, words);
    }

    public static List<List<ParamsInfo>> readAllTrainFiles(String path) throws IOException {
        //List<List<String>> filesData = new ArrayList<>();
        List<List<ParamsInfo>> filesData = new ArrayList<>();

        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            String content = FileUtils.readFileToString(file, "UTF-8");
            Matcher matcher = FILE_PATTERN.matcher(content);
            List<ParamsInfo> fileInfo = new ArrayList<>();
            while (matcher.find()) {
                String group = matcher.group();

                Matcher paramsMatcher = SEGMENT_PATTERN.matcher(group);
                String params = paramsMatcher.group();
                List<String> emotions = parseParameters(params);

                List<String> words = getWordsFromText(group.toLowerCase());
                words = removeStopWords(words);

                fileInfo.add(new ParamsInfo(words, emotions));
            }
            filesData.add(fileInfo);
        }
        return filesData;
    }

    public static Map<String, List<String>> readAllTestFiles(String path) throws IOException {
        Map<String, List<String>> filesData = new HashMap<>();
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            String content = FileUtils.readFileToString(file, "UTF-8");
            List<String> words = getWordsFromText(content.toLowerCase());
            words = removeStopWords(words);

            filesData.put(file.getName(), words);
        }
        return filesData;
    }

    private static List<String> parseParameters(String params) {
        char prev = ' ';
        List<String> emotionClasses = new ArrayList<>();
        for (char ch : params.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                emotionClasses.add(String.valueOf(ch));
            } else if (Character.isDigit(ch)) {

            } else if (ch == '\'') {
                emotionClasses.add(String.valueOf(prev + '\''));
            }
            prev = ch;
        }
        return emotionClasses;
    }

    private static List<String> getWordsFromText(String text) {
        Matcher wordsMatcher = WORDS_PATTERN.matcher(text);
        List<String> words = new ArrayList<>();

        while (wordsMatcher.find()) {
            String word = wordsMatcher.group();
            if (!stopWords.contains(word)) {
                words.add(word);
            }
        }
        return words;
    }

    private static List<String> removeStopWords(List<String> words) {
        List<String> cleanWords = new ArrayList<>();
        for (String word : words) {
            if (!stopWords.contains(word)) {
                cleanWords.add(word);
            }
        }
        return cleanWords;
    }
}
