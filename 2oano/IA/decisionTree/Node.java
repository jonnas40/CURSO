import java.util.*;

public class Node {
 
    private String data = null;

    private int counter;
    
    private Node[] children;
        
    public Node(Atribute data) {
        this.data = data.getName();
        this.children = new Node[data.getValues().size()];
    }

    public Node(String name, int counter){
        this.data = name;
        this.counter = counter;
    }
    
    public void addChild(Node child, int index) {
        this.children[index] = child;
    }
    
    public Node[] getChildren() {
        return children;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public Boolean isLeaf() {
        return this.children==null;
    }

    public String toString(){
        if(this.isLeaf()){
            return data + " " + counter;
        }
        else
            return data;
    }
    
   }
