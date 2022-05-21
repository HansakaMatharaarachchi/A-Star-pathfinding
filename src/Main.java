import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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
        System.out.println(source + " >> TO >> " + destination);

        PathFinder pathFinder = new PathFinder(source, destination);
        ArrayList<Node> result = pathFinder.getResult();

        if (result != null && !result.isEmpty()) {
            Collections.reverse(result);
            System.out.println("Time Took : " + stopwatch.elapsedTime() + "\n");
            for (Node node : result) {
                System.out.println(node.printConnection() + " " + node);
            }
            System.out.println("\nStep Count: " + (result.size() - 1));
        }
    }
}
