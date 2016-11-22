import java.io.IOException;

/**
 * Created by Vlad on 19.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            BayesClassifier.train(Parser.readAllTrainFiles("E:\\Навчання\\5 - КУРС\\Марченко\\Docs\\part 3\\train"));
            BayesClassifier.classify(Parser.readAllTestFiles("E:\\Навчання\\5 - КУРС\\Марченко\\Docs\\part 3\\test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
