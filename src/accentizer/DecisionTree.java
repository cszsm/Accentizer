package accentizer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zscse on 2016. 02. 24..
 */
public class DecisionTree {
    private List<Node> tree;
    private int window;

    public DecisionTree(List<Node> tree, int window) {
        this.tree = tree;
        this.window = window;
    }

    public int classify(LinkedList<Character> slideWindow) {
        int index = 0;
        int limit = 200;

        while (index < tree.size()) {
            index = tree.get(index).getNextDecision(slideWindow);
            if (tree.get(index).isLeaf()) {
                return tree.get(index).getLabel();
            }
            if (limit-- < 1) {
                return 0;
            }
        }
        return 0;
    }
}
