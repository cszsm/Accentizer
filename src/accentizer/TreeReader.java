package accentizer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zscse on 2016. 02. 28..
 */
public class TreeReader {

    // NOTE: need a method that closes the Scanner or BufferedReader

    public DecisionTree readFromFile(String path) throws FileNotFoundException {
        ArrayList<Node> tree = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        int nodeCount = scanner.nextInt();
        tree.ensureCapacity(nodeCount);
        int window = scanner.nextInt();

        scanner.nextLine();

        String line;
        boolean leaf;
        char c;
        int position;
        int leftIndex;
        int rightIndex;
        int label;
        String[] words;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            c = line.charAt(0);

            line = line.substring(2);

            words = line.split(" ");

            position = Integer.valueOf(words[0]);
            leftIndex = Integer.valueOf(words[1]);
            rightIndex = Integer.valueOf(words[2]);

            leaf = false;
            if (leftIndex == -1 && rightIndex == -1) {
                label = Integer.valueOf(words[3]);
                leaf = true;
            } else {
                label = -1;
            }

            tree.add(new Node(leaf, c, position, leftIndex, rightIndex, label, window));
        }

        return new DecisionTree(tree, window);
    }

    public DecisionTree readInputStream(String path) throws IOException {
        ArrayList<Node> tree = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = bufferedReader.readLine();
        String[] words = line.split(" ");
        int nodeCount = Integer.valueOf(words[0]);
        tree.ensureCapacity(nodeCount);
        int window = Integer.valueOf(words[1]);

        line = bufferedReader.readLine();
        while (line != null) {
            tree.add(new Node(line, window));
            line = bufferedReader.readLine();
        }

        return new DecisionTree(tree, window);
    }
}
