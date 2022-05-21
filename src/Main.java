import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Node source, destination;
        MapParser parser = new MapParser();
        String filepath;
        if (args.length == 0) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println("SelectedFile : " + fileChooser.getSelectedFile());
            } else {
                System.out.println("No Selection ");
                System.exit(0);
            }
            filepath = fileChooser.getSelectedFile().getPath();
        } else {
            filepath = args[0];
        }

        Stopwatch stopwatch = new Stopwatch();
        parser.readMapFromFile(filepath);

        source = parser.getSourceNode();
        destination = parser.getDestinationNode();
        System.out.println(source + " >> TO >> " + destination + "\n");

        PathFinder pathFinder = new PathFinder(source, destination);
        Stack<Node> result = pathFinder.getResult();

        double elapsedTime = stopwatch.elapsedTime();
        int stepCount = result.size() - 1;
        while (!result.isEmpty()) {
            Node node = result.pop();
            System.out.println(node.printConnection() + " " + node);
            if (destination == node)
                System.out.println("\n----Destination reached----\nStep Count: " + stepCount);
        }
        System.out.println("Time Took : " + elapsedTime);
    }
}
