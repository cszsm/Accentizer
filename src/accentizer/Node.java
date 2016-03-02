package accentizer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zscse on 2016. 02. 24..
 */
public class Node {
    private boolean leaf;
    private char c;
    private int position;
    private int left;
    private int right;
    private int label;
    private int window;

    public Node(boolean leaf, char c, int position, int left, int right, int label, int window) {
        this.leaf = leaf;
        this.c = c;
        this.position = position;
        this.left = left;
        this.right = right;
        this.label = label;
        this.window = window;

        leaf = (left == -1 && right == -1);
    }

    public boolean isLeaf() {
        return leaf;
    }

    public int getPosition() {
        return position;
    }

    public int getLabel() {
        return label;
    }

    public int getNextDecision(LinkedList<Character> slideWindow) {
        return (slideWindow.get(window + position) == c) ? right : left;
    }
}
