import java.util.*;

public class Node {
 
    private String data = null;

    private int counter;
    
    private Node[] children;

    private String parentName = null;
        
    public Node(Atribute data) {
        this.data = data.getName();
        this.children = new Node[data.getValues().size()];
    }

    public Node(String name, int counter){
        this.data = name;
        this.counter = counter;
    }
    
    public void addChild(Node child, int index, String parentName) {
        this.children[index] = child;
        this.children[index].parentName = parentName;
    }
    
    public Node[] getChildren() {
        return children;
    }

    public String getParent(){
        return parentName;
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
            return parentName + ": " + data + " " + counter;
        }
        else{
            if(this.parentName == null)
                return data;
            else 
                return parentName + ": " + data;
        }
    }
    
   }
