package accentizer;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zscse on 2016. 03. 06..
 */
public class Main {
    public static void main(String[] args) {
        Accentizer accentizer = new Accentizer();
        try {
            accentizer.load("D:\\Accentizer\\hunaccent-master\\tree");
            System.out.println(accentizer.accentize("arvizturo tukorfurogep"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
