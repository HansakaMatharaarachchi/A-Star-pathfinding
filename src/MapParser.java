import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapParser {
    private Node sourceNode;
    private Node destinationNode;

    public Grid readMapFromFile(String filePath) {
        final BufferedReader reader;
        Grid map = new Grid();
        int lineLength = 0;
        int lineNumber = 0;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineNumber + 1 == 1) lineLength = line.length();

                if (lineLength == line.length()) {
                    Node[] nodeRow = new Node[lineLength];
                    for (int i = 0; i < lineLength; i++) {
                        char c = line.charAt(i);
                        switch (c) {
                            case 'S':
                                if (sourceNode == null) {
                                    sourceNode = new Node(lineNumber, i);
                                    nodeRow[i] = sourceNode;
                                    break;
                                }
                                System.out.println("Error!! 2 Starting positions are given");
                                break;
                            case 'F':
                                if (destinationNode == null) {
                                    destinationNode = new Node(lineNumber, i);
                                    nodeRow[i] = destinationNode;
                                    break;
                                }
                                System.out.println("Error!! 2 Finishing positions are given");
                                break;
                            case '0':
                                Node rock = new Node(lineNumber, i);
                                rock.setWalkable(false);
                                nodeRow[i] = rock;
                                break;
                            case '.':
                                Node ice = new Node(lineNumber, i);
                                ice.setWalkable(true);
                                nodeRow[i] = ice;
                                break;
                            default:
                                System.out.println("Invalid Characters Are Found In The Given Map");
                                break;
                        }
                    }
                    map.addANodeRow(nodeRow);
                    lineNumber++;
                } else {
                    System.out.println("Invalid Map Format");
                    break;
                }
            }
            reader.close();
            return addNeighbours(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public Grid addNeighbours(Grid map) {
        for (int x = 0; x < map.getColumnCount(); x++) {
            for (int y = 0; y < map.getRowCount(); y++) {
                map.getNode(x, y).addNeighbours(map);
            }
        }
        return map;
    }
}
