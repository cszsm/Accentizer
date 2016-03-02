package accentizer;

import java.io.FileNotFoundException;

/**
 * Created by zscse on 2016. 02. 25..
 */
public class Main {
    public static void main(String[] args) {
        Accentizer accentizer = new Accentizer();
        try {
            accentizer.load("src/resources/trees");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        for (String word :
                args) {
            accentizer.accentize(word);
        }
    }
}
