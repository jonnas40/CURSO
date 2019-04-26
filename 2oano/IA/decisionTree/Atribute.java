import java.util.*;

class Atribute{

    String name;
    ArrayList<String> values = new ArrrayList<String>();
    int index;

    public Atribute(String name, int index, ArrayList<String[]> examples){
        this.name = name;
        this.index = index;
        for (String[] s : examples) {
            if(!values.contains(s[index]))
                values.add(s[index]);
        }
    }

}