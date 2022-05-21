import java.util.*;

//A* Algorithm
public class PathFinder {
    private static final int MOVE_STRAIGHT_COST = 10;
    private static final int MOVE_DIAGONAL_COST = 14;

    private ArrayList<Node> result = new ArrayList<>();

    public PathFinder(Node source, Node destination) {
        ArrayList<Node> openSet = new ArrayList<>();
        ArrayList<Node> closedSet = new ArrayList<>();

        openSet.add(source);

        while (!openSet.isEmpty()) {
            //get the node which has the best F score (least F score)
            Node current = openSet.get(0);
            for (Node node : openSet) {
                if (node.getFCost() < current.getFCost() || (node.getFCost() == current.getFCost() && node.getHCost() < current.getHCost()))
                    current = node;
            }

            //path has been found
            if (current == destination) {
                Node tmpNode = current;
                result.add(tmpNode);
                while (tmpNode.getParentNode() != null) {
                    tmpNode = tmpNode.getParentNode();
                    result.add(tmpNode);
                }
                return;
            }

            openSet.remove(current);
            closedSet.add(current);

            // check for all possible neighbours for the next successor
            for (Node neighbour : current.getNeighbors()) {
                if (!closedSet.contains(neighbour) && neighbour.isWalkable()) {
                    int tentativeGCost = current.getGCost() + getDistanceCost(current,neighbour);
                    boolean newPath = false;
                    if (openSet.contains(neighbour)) {
                        if (tentativeGCost < neighbour.getGCost()) {
                            neighbour.setGCost(tentativeGCost);
                            newPath = true;
                        }
                    } else {
                        neighbour.setGCost(tentativeGCost);
                        newPath = true;
                        openSet.add(neighbour);
                    }
                    if (newPath) {
                        neighbour.setHCost(getDistanceCost(neighbour, destination));
                        neighbour.setFCost(neighbour.getGCost() + neighbour.getHCost());
                        neighbour.setParentNode(current);
                    }
                }
            }
        }
        System.out.println("No possible paths");
    }

    //Calculates distance between two nodes - ideal for directions with diagonals
    private static int getDistanceCost(Node i, Node j) {
        int xD = Math.abs(i.getX() - j.getX());
        int yD = Math.abs(i.getY() - j.getY());
        int remaining = Math.abs(xD - yD);
        return MOVE_DIAGONAL_COST * Math.min(xD, yD) + MOVE_STRAIGHT_COST * remaining;
    }
    //Calculates Manhattan distance ideal - s for N S E W
    private static int getManhattanDistance(Node i, Node j)
    {
        return Math.abs(i.getX() - j.getX()) + Math.abs(i.getY() - j.getY());
    }
    public ArrayList<Node> getResult() {
        return result;
    }
}
