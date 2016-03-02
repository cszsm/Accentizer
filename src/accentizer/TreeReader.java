package accentizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zscse on 2016. 02. 28..
 */
public class TreeReader {

    public DecisionTree readFromFile(String path) throws FileNotFoundException {
        ArrayList<Node> tree = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        int nodeCount = scanner.nextInt();
        tree.ensureCapacity(nodeCount);
        int window = scanner.nextInt();

        scanner.nextLine();

        String nextLine;
        boolean leaf;
        char c;
        int position;
        int leftIndex;
        int rightIndex;
        int label;

        while (scanner.hasNextLine()) {
            nextLine = scanner.nextLine();

            c = nextLine.charAt(0);

            nextLine = nextLine.substring(2);

            String[] strings = nextLine.split(" ");

            position = Integer.valueOf(strings[0]);
            leftIndex = Integer.valueOf(strings[1]);
            rightIndex = Integer.valueOf(strings[2]);

            leaf = false;
            if(leftIndex == -1 && rightIndex == -1) {
                label = Integer.valueOf(strings[3]);
                leaf = true;
            } else {
                label = -1;
            }

            tree.add(new Node(leaf, c, position, leftIndex, rightIndex, label, window));
        }


        return new DecisionTree(tree, window);
    }
}
