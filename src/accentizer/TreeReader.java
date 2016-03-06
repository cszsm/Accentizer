package accentizer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zscse on 2016. 02. 28..
 */
class TreeReader {

    private Closeable closeable;

    // NOTE: need a method that closes the Scanner or BufferedReader

    public DecisionTree readFromFile(String path) throws FileNotFoundException {
        ArrayList<Node> tree = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        closeable = scanner;

        int nodeCount = scanner.nextInt();
        tree.ensureCapacity(nodeCount);
        int window = scanner.nextInt();

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            tree.add(new Node(scanner.nextLine(), window));
        }

        return new DecisionTree(tree, window);
    }

    public DecisionTree readInputStream(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        return readInputSream(fileInputStream);
    }

    public DecisionTree readInputSream(InputStream inputStream) throws IOException {
        ArrayList<Node> tree = new ArrayList<>();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        closeable = bufferedReader;

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

    public void close() throws IOException {
        closeable.close();
    }
}
