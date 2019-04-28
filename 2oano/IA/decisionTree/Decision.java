import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class Decision{
    
    static Atribute classe;
    public static void main(String[] args) {
        
        //leitura e parsing dos csv's
        String fileName = args[0];
        File file = new File(fileName);
        List<String[]> values = new ArrayList<String[]>();
        LinkedList<Atribute> atributos = new LinkedList<Atribute>();
        try{
            Scanner inputStream = new Scanner(file);
            String data = inputStream.next();
            String[] atributes = data.split(",");
            while(inputStream.hasNext()){
                data = inputStream.next();
                String[] aux = data.split(",");
                values.add(aux);
            }
            for (int i = 1; i<atributes.length-1; i++) {
                Atribute a = new Atribute(atributes[i], i, values);
                atributos.add(a);
            }
            classe = new Atribute(atributes[atributes.length-1], atributes.length-1, values);
            inputStream.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        //classe.printAtr();

        List<String[]> examples = new ArrayList<String[]>();
        DFS(ID3(values, atributos, examples), "");

        
    }

    //ALGORITMO

    public static Node ID3(List<String[]> examples, LinkedList<Atribute> atributos, List<String[]> parent_examples){
        if (examples.size()==0)
        return pluralityValue(parent_examples);
        else if (allSame(examples)){
            String s = examples.get(0)[classe.getIndex()];
            Node n = new Node(s, examples.size());
            return n;
        }
        else if (atributos.isEmpty())
        return pluralityValue(examples);
        else{
            //Atribute chosen;
            Atribute chosen=chosenOne(examples, atributos);
            Node tree = new Node(chosen);
            int i = 0;
            for (String value : chosen.getValues()) {
                List<String[]> exs = new ArrayList<String[]>();
                for (String[] var : examples) {
                    if(var[chosen.getIndex()].equals(value))
                        exs.add(var);
                }
                atributos.remove(chosen);
                Node subtree = ID3(exs, atributos, examples);
                tree.addChild(subtree, i);
                i++;
                //Node a = new Node(value);
            }
            return tree;
            //chosen.printAtr();
        }
    }

    //função para escolher o melhor atributo

    public static Atribute chosenOne(List<String[]> values, LinkedList<Atribute> atributos){
        Atribute chosen = null;
        double argmax=Double.MAX_VALUE;
        for (Atribute a : atributos) {
            double max=entropy(a, values);
            //System.out.println(max);
            //a.printAtr();
            if(max<argmax){
                argmax=max;
                chosen=a;
            }
        }
        return chosen;
    }

    //função entropia e importancia
    
    public static double entropy(Atribute a, List<String[]> values){
        //double total = values.size();
        //double gain=0;
        double entropy = 0;
        for (String aux : classe.getValues()) {
            //int i = 0;
            for (String var : a.getValues()) {
                entropy += prob(aux, var, a.getIndex(), values);
                //i++;
            }
            //gain += (i/total) * entropy;
        }
        return entropy;
    }
    
    //função probabilidade
    
    public static double prob(String aux, String var, int index, List<String[]> values){
        int counter = 0;
        int total = 0;
        for (String[] example : values) {
            if(example[index].equals(var)){
                if (example[classe.getIndex()].equals(aux))    counter++;
                total++;
            }
        }
        if(total==0 || counter==0) return 0;
        else return (double)total/values.size()*(-((double)counter/total * log2((double)counter/total)));
    }
    
    //função plurality value
    
    public static Node pluralityValue(List<String[]> values){
        int[] counter = new int[classe.noVal()];
        for (String[] var : values) {
            for (String cl : classe.getValues()) {
                int i = 0;
                if (cl.equals(var[classe.getIndex()])) {
                    counter[i]++;
                }
                i++;
            }
        }
        int max=0;
        int maxj=0;
        int count = 0;
        for(int j = 0; j<counter.length; j++) {
            if(counter[j]>max) {
                max=counter[j];
                maxj=j;
            }
            count += counter[j];
        }
        Node ret = new Node(classe.getValues().get(maxj), count);
        return ret;
    }

    //função auxiliar
    
    public static Boolean allSame(List<String[]> values){
        String[] aux = values.get(0);
        /*for (String a : aux) {
            System.out.print(a + " ");
        }*/
        String au = aux[classe.getIndex()];
        //System.out.println(au);
        for (String[] var : values) {
            if (!var[classe.getIndex()].equals(au)) return false;
        }
        return true;
    }

    ////////auxiliares matemáticas
    
    public static double logb( double a, double b ){
        return Math.log(a) / Math.log(b);
    }

    public static double log2( double a ){
        return logb(a,2);
    }

    //DFS

    public static void DFS(Node n, String space){
        System.out.println(space+n);
        if (n.isLeaf()) return;
        for (Node son : n.getChildren()) {
            DFS(son, space + "\t");
        }
    }
    
}
