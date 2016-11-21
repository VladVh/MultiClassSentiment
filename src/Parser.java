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
    private static final String STRING_PATTERN = "<(.+?)>";

    private static final Pattern FILE_PATTERN = Pattern.compile(STRING_PATTERN);


    private static final Map<String, List<String>> filesData = new HashMap<String, List<String>>();

    public static void readAllFiles(String path) throws IOException {
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            String content = FileUtils.readFileToString(file, "UTF-8");
            Matcher matcher = FILE_PATTERN.matcher(content);
            List<String> strings = new ArrayList<String>();
            while (matcher.find()) {
                String group = matcher.group();
                strings.add(group);
//                Matcher matcher1 = SEGMENT_PATTERN.matcher(group);
//                while (matcher1.find()) {
//                    System.out.println(matcher1.group());
//                }
                //System.out.println(matcher.group());
            }
            filesData.put(file.getName(), strings);
        }
    }

    public static Map<String, List<String>> getData() {
        return filesData;
    }
}
