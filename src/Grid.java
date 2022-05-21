import java.util.ArrayList;

public class Grid {
    private ArrayList<Node[]> nodeRows = new ArrayList<>();

    public Grid(){}

    public Grid(ArrayList<Node[]> nodes) {
        this.nodeRows = nodes;
    }
    public int getRowCount() {
        return this.nodeRows.size();
    }

    public int getColumnCount() {
        return this.nodeRows.get(0).length;
    }

    public ArrayList<Node[]> getNodes() {
        return nodeRows;
    }
    public Node getNode(int x, int y){
        return nodeRows.get(x)[y];
    }

    public void setNodes(ArrayList<Node[]> nodes) {
        this.nodeRows = nodes;
    }

    public void addANodeRow(Node[] nodeRow){
        this.nodeRows.add(nodeRow);
    }

    public Node[] getANodeRow(int nodeRow){
        return nodeRows.get(nodeRow);
    }

}
