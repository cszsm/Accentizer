package accentizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by zscse on 2016. 02. 24..
 */
public class Accentizer {
    private Map<Character, DecisionTree> trees;

    // Hungarian accents in the order of the classifiers labeling
    private Map<Character, List<String>> accentMap;

    // Hungarian uppercase accents in the order of the classifiers labeling
    private Map<Character, List<String>> upperAccentMap;
    private int window;

    public Accentizer() {
        trees = new HashMap<>();
        accentMap = initializeAccentMap();
        upperAccentMap = initializeUpperAccentMap();
        window = 4;
    }

    public void load(String baseDir) throws IOException {
        // I don't know yet if the iteration order is relevant
        for (Map.Entry<Character, List<String>> vowels :
                accentMap.entrySet()) {
            char latinizedVowel = vowels.getKey();
            String fileName = baseDir + "/" + latinizedVowel;

            TreeReader treeReader = new TreeReader();
//            DecisionTree decisionTree = treeReader.readFromFile(fileName);
            DecisionTree decisionTree = treeReader.readInputStream(fileName);
            trees.put(latinizedVowel, decisionTree);
//            trees.get(latinizedVowel).readFromFile(fileName);
        }
    }

    public void load(char c, InputStream inputStream) throws IOException {
        TreeReader treeReader = new TreeReader();
        trees.put(c, treeReader.readInputSream(inputStream));
    }

    public Set<Character> getVowels() {
        return trees.keySet();
    }

    public String accentize(String text) {
        String result = "";
        String paddedText = padText(text);

        int fullWindow = 2 * window + 1;
        LinkedList<Character> slideWindow = new LinkedList<>();

        for (int i = 0; i < fullWindow; i++) {
            slideWindow.addLast(normalize(paddedText.charAt(i)));
        }

        int inputPosition = window;
        for (int i = fullWindow; i < paddedText.length(); i++) {
            char middle = slideWindow.get(window);
            if (accentMap.containsKey(middle)) {
                int label = trees.get(middle).classify(slideWindow);

                if (Character.isUpperCase(paddedText.charAt(inputPosition))) {
                    result += upperAccentMap.get(middle).get(label);
//                    result.add(upperAccentMap.get(middle).get(label));
//                    System.out.print(upperAccentMap.get(middle).get(label));
                } else {
                    result += accentMap.get(middle).get(label);
//                    result.add(accentMap.get(middle).get(label));
//                    System.out.print(accentMap.get(middle).get(label));
                }
            } else {
                result += paddedText.charAt(inputPosition);
//                result.add(paddedText.charAt(inputPosition));
//                System.out.print(paddedText.charAt(inputPosition));
            }

            inputPosition++;

            char norm = normalize(paddedText.charAt(i));
            slideWindow.addLast(norm);
            slideWindow.removeFirst();
        }
//        System.out.println();
        return result;
    }

    private Map<Character, List<String>> initializeAccentMap() {
        List<String> aVowels = new ArrayList<>();
        aVowels.add("a");
        aVowels.add("á");

        List<String> eVowels = new ArrayList<>();
        eVowels.add("e");
        eVowels.add("é");

        List<String> iVowels = new ArrayList<>();
        iVowels.add("i");
        iVowels.add("í");

        List<String> oVowels = new ArrayList<>();
        oVowels.add("o");
        oVowels.add("ó");
        oVowels.add("ö");
        oVowels.add("ő");

        List<String> uVowels = new ArrayList<>();
        uVowels.add("u");
        uVowels.add("ú");
        uVowels.add("ü");
        uVowels.add("ű");

        // HashMap for maximum speed
        // TreeMap for sorted collection
        Map<Character, List<String>> accentMap = new HashMap<>();
        accentMap.put('a', aVowels);
        accentMap.put('e', eVowels);
        accentMap.put('i', iVowels);
        accentMap.put('o', oVowels);
        accentMap.put('u', uVowels);

        return accentMap;
    }

    private Map<Character, List<String>> initializeUpperAccentMap() {
        List<String> aVowels = new ArrayList<>();
        aVowels.add("A");
        aVowels.add("Á");

        List<String> eVowels = new ArrayList<>();
        eVowels.add("E");
        eVowels.add("É");

        List<String> iVowels = new ArrayList<>();
        iVowels.add("I");
        iVowels.add("Í");

        List<String> oVowels = new ArrayList<>();
        oVowels.add("O");
        oVowels.add("Ó");
        oVowels.add("Ö");
        oVowels.add("Ő");

        List<String> uVowels = new ArrayList<>();
        uVowels.add("U");
        uVowels.add("Ú");
        uVowels.add("Ü");
        uVowels.add("Ű");

        Map<Character, List<String>> accentMap = new HashMap<>();
        accentMap.put('a', aVowels);
        accentMap.put('e', eVowels);
        accentMap.put('i', iVowels);
        accentMap.put('o', oVowels);
        accentMap.put('u', uVowels);

        return accentMap;
    }

    // There should be a new class for this function
    private String padText(String text) {
        String paddedText = "";
        char padding = '_';

        for (int i = 0; i < window - 1; i++) {
            paddedText += padding;
        }

        paddedText += ' ' + text + '\n' + ' ';

        for (int i = 0; i < window - 1; i++) {
            paddedText += padding;
        }

        return paddedText;
    }

    // Maybe there should be another class for this function
    private static char normalize(char c) {
        if (Character.isWhitespace(c)) {
            return ' ';
        }
        if (Character.isDigit(c)) {
            return '0';
        }
        if (isPunctuation(c)) {
            return '_';
        }
        if (Character.isAlphabetic(c)) {
            return Character.toLowerCase(c);
        }
        return '*';
    }

    // I don't know where this should be...
    private static boolean isPunctuation(char c) {
        String punctuations = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        for (int i = 0; i < punctuations.length(); i++) {
            if (c == punctuations.charAt(i)) {
                return true;
            }
        }
        return false;
    }
}
