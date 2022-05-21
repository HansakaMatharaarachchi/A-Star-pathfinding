import java.util.*;

//A* Algorithm
public class PathFinder {
    private static final int MOVE_STRAIGHT_COST = 10;
    private static final int MOVE_DIAGONAL_COST = 14;

    private final Stack<Node> result = new Stack<>();

    public PathFinder(Node source, Node destination) {
        Queue<Node> openSet = new PriorityQueue<>((node, node1) -> {
            if ((node.getFCost() > node1.getFCost()) || ((node.getFCost() == node1.getFCost()) && (node.getHCost() > node1.getFCost()))) {
                return 1;
            } else if ((node.getFCost() < node1.getFCost()) || ((node.getFCost() == node1.getFCost()) && (node.getHCost() < node1.getFCost()))) {
                return -1;
            } else {
                return 0;
            }
        });
        Set<Node> closedSet = new HashSet<>();

        openSet.add(source);

        while (!openSet.isEmpty()) {
            // retrieve and remove the node which has the best F score (least F score)
            Node current = openSet.poll();

            if (current == destination) {
                //path has been found
                Node tmpNode = current;
                result.push(tmpNode);
                while (tmpNode.getParentNode() != null) {
                    tmpNode = tmpNode.getParentNode();
                    result.push(tmpNode);
                }
                return;
            }

            closedSet.add(current);

            // check all neighbours of the current node
            for (Node neighbour : current.getNeighbors()) {
                if (!closedSet.contains(neighbour) && neighbour.isWalkable()) {
                    // distance from source node to the neighbor through current node
                    int tentativeGCost = current.getGCost() + getDistanceCost(current, neighbour);
                    boolean newPath = false;

                    if (openSet.contains(neighbour)) {
                        // update the G cost if new G cost is better
                        if (tentativeGCost < neighbour.getGCost()) {
                            neighbour.setGCost(tentativeGCost);
                            openSet.remove(neighbour);
                            newPath = true;
                        }
                    } else {
                        neighbour.setGCost(tentativeGCost);
                        newPath = true;
                    }
                    if (newPath) {
                        neighbour.setHCost(getDistanceCost(neighbour, destination));
                        neighbour.setFCost(neighbour.getGCost() + neighbour.getHCost());
                        neighbour.setParentNode(current);
                        openSet.add(neighbour);
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

    //Calculates Manhattan distance ideal for - N S E W
    private static int getManhattanDistance(Node i, Node j) {
        return Math.abs(i.getX() - j.getX()) + Math.abs(i.getY() - j.getY());
    }

    public Stack<Node> getResult() {
        return result;
    }
}
