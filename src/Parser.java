import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vlad on 19.11.2016.
 */
public class Parser {
    private static final String STRING_REGEX = "<(.+?)>";
    private static final String WORDS_REGEX = "[a-zA-Z]{2,20}";

    private static final Pattern FILE_PATTERN = Pattern.compile(STRING_REGEX);
    private static final Pattern WORDS_PATTERN = Pattern.compile(WORDS_REGEX);

    public static List<List<String>> readAllTrainFiles(String path) throws IOException {
        List<List<String>> filesData = new ArrayList<>();
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            String content = FileUtils.readFileToString(file, "UTF-8");
            Matcher matcher = FILE_PATTERN.matcher(content);
            List<String> strings = new ArrayList<>();
            while (matcher.find()) {
                String group = matcher.group();
                strings.add(group);
//                Matcher matcher1 = SEGMENT_PATTERN.matcher(group);
//                while (matcher1.find()) {
//                    System.out.println(matcher1.group());
//                }
                //System.out.println(matcher.group());
            }
            filesData.add(strings);
        }
        return filesData;
    }

    public static Map<String, List<String>> readAllTestFiles(String path) throws IOException {
        Map<String, List<String>> filesData = new HashMap<>();
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            String content = FileUtils.readFileToString(file, "UTF-8");
            //content.replaceAll("<", " ");
            List<String> words = new ArrayList<>();
            Matcher matcher = WORDS_PATTERN.matcher(content);
            while (matcher.find()) {
                words.add(matcher.group());
            }
            filesData.put(file.getName(), words);
        }
        return filesData;
    }
}
