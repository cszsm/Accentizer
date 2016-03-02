package accentizer;

import java.io.*;
import java.util.Scanner;

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

        String text = "Mendoza es Gabriel megprobalja megvedeni a kozosseget a portugal koloniak kegyetlensegeitol, amelyek megprobaljak rabszolgava tenni a guaranikat a madridi szerzodes altal garantalt uj hatalmuk segitsegevel.";
        System.out.println(text + " =>");
        accentizer.accentize(text);
        // "src/resources/trees/a
    }
}
