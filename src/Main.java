import java.io.IOException;

/**
 * Created by Vlad on 19.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            BayesClassifier.train(Parser.readAllTrainFiles("E:\\University\\Docs\\train"));
            BayesClassifier.classify(Parser.readAllTestFiles("E:\\University\\Docs\\test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
