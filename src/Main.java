import java.io.IOException;

/**
 * Created by Vlad on 19.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Parser.readAllFiles("E:\\Навчання\\5 - КУРС\\Марченко\\Docs\\part 3\\");
            BayesClassifier.train(Parser.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
