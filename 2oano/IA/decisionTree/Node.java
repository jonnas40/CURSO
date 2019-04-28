import java.util.*;

public class Node {
 
    private String data = null;

    private int counter = null;
    
    private List<Node> children = new ArrayList<>();
    
    private Node parent = null;
    
    public Node(Atribute data) {
        this.data = data.getName();

    }

    public Node(String name, int counter){
        this.data = name;
        this.counter = counter;
    }
    
    public Node addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }
    
    public void addChildren(List<Node> children) {
        for (Node child : children) {
            child.setParent(this);
            this.children.add(child);
        }
    }
    
    public List<Node> getChildren() {
        return children;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    private void setParent(Node parent) {
        this.parent = parent;
    }
    
    public Node getParent() {
        return parent;
    }

    public Boolean isLeaf() {
        return this.getChildren().isEmpty();
    }
    
   }
