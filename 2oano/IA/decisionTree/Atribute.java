import java.util.*;

class Atribute{

    String name;
    List<String> values = new ArrayList<String>();
    int index;

    public Atribute(String name, int index, List<String[]> examples){
        this.name = name;
        this.index = index;
        for (String[] s : examples) {
            if(!values.contains(s[index]))
                values.add(s[index]);
        }
    }

    public String getName(){
        return this.name;
    }

    public List<String> getValues(){
        return this.values;
    }

    public int noVal(){
        return this.values.size();
    }

    public int getIndex(){
        return this.index;
    }

    public void printAtr(){
        System.out.println("Atributo: " + this.name);
        System.out.print("Valores: ");
        for (String val : this.values) {
            System.out.print(val + " ");
        }
        System.out.println();
        System.out.println("---------");
    }

}