import java.util.ArrayList;
import java.util.List;

public class Node {

    private int x, y;
    private int fCost, gCost, hCost;
    private boolean isWalkable = true;
    private Node parentNode;
    private List<Node> neighbors = new ArrayList<>();

    public Node(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFCost() {
        return fCost;
    }

    public void setFCost(int fCost) {
        this.fCost = fCost;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbours(Grid grid) {
        int x = this.x;
        int y = this.y;

        //North ↑↑
        if (y > 0) this.neighbors.add(grid.getNode(x, y - 1));

        // West ↓↓
        if (y < grid.getRowCount() - 1) this.neighbors.add(grid.getNode(x, y + 1));

        // East →→
        if (x < grid.getRowCount() - 1) this.neighbors.add(grid.getNode(x + 1, y));

        // ←← South
        if (x > 0) this.neighbors.add(grid.getNode(x - 1, y));

        //DIAGONALS

        //North East ↗
        if (x > 0 && y != grid.getColumnCount() - 1) this.neighbors.add(grid.getNode(x - 1, y + 1));

        //North West ↖
        if (x > 0 && y > 0) this.neighbors.add(grid.getNode(x - 1, y - 1));

        //South East ↘
        if (x < grid.getRowCount() - 1 && y < grid.getColumnCount() - 1) this.neighbors.add(grid.getNode(x + 1, y + 1));

        //South West ↙
        if (x < grid.getRowCount() - 1 && y > 0) this.neighbors.add(grid.getNode(x + 1, y - 1));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    // gets the parent node direction
    public String printConnection() {
        if (this.parentNode == null) {
            return "Starts From";
        }
        if (this.parentNode.x == this.x + 1 && this.parentNode.y == this.y) return " ↑ ";
        if (this.parentNode.x == this.x - 1 && this.parentNode.y == this.y) return " ↓ ";
        if (this.parentNode.x == this.x && this.parentNode.y == this.y + 1) return " ← ";
        if (this.parentNode.x == this.x && this.parentNode.y == this.y - 1) return " → ";

        //Diagonals

        if (this.parentNode.x == this.x + 1 && this.parentNode.y + 1 == this.y) return " ↗ ";
        if (this.parentNode.x == this.x + 1 && this.parentNode.y - 1 == this.y) return " ↖ ";
        if (this.parentNode.x == this.x - 1 && this.parentNode.y + 1 == this.y) return " ↘ ";
        if (this.parentNode.x == this.x - 1 && this.parentNode.y - 1 == this.y) return " ↙ ";
        return null;
    }
}
