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
        LinkedList<Atribute> atributos2 = new LinkedList<Atribute>();
        try{
            Scanner inputStream = new Scanner(file);
            String data = inputStream.next();
            String[] atributes = data.split(",");
            while(inputStream.hasNext()){
                data = inputStream.next();
                String[] aux = data.split(",");
                values.add(aux);
            }
            values=discrete(values);
            for (int i = 1; i<atributes.length-1; i++) {
                Atribute a = new Atribute(atributes[i], i, values);
                atributos.add(a);
                atributos2.add(a);
            }
            classe = new Atribute(atributes[atributes.length-1], atributes.length-1, values);
            inputStream.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        /*String exampleFile = args[1];
        File exmp = new File(exampleFile);
        List<String[]> exa = new ArrayList<String[]>();
        try {
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                String dat = input.next();
                String[] au = dat.split(",");
                exa.add(au);
            }
            exa = discrete(exa);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/




        /*for (String[] var : exa) {
            for (String ex : var) {
                System.out.print(ex + " ");
            }
            System.out.println();
        }*/

        //classe.printAtr();


        List<String[]> examples = new ArrayList<String[]>();
        Node tree = ID3(values, atributos, examples);
        DFS(tree, "");

        Scanner sc = new Scanner(System.in);
        List<String[]> exa = new ArrayList<String[]>();
        String in = sc.next();
        while (!in.equals("done")) {
            String[] buf = in.split(",");
            exa.add(buf);
            in = sc.next();
        }
        try {
          exa = discrete(exa);
          int i = 1;
          for (String[] ex : exa) {
            System.out.println("Exemplo " + i + ":" + classify(tree, ex, atributos2));
            i++;
          }
        }catch(IndexOutOfBoundsException e) {
          return;
        }
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
                tree.addChild(subtree, i, value);
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

    //classificação de exemplos dados

    public static String classify(Node tree, String[] example, LinkedList<Atribute> atributos){
        if (tree.isLeaf())
            return tree.getData();

        String atr = tree.getData();
        int index = 0;
        //System.out.println(atr);
        for (Atribute a : atributos) {
            //System.out.println(a.getName());
            if (atr.equals(a.getName())){
                index = a.getIndex();
            }
        }
        for (Node son : tree.getChildren()) {
            //System.out.println(son.getParent());
            //System.out.println(example[index]);
            if (example[index].equals(son.getParent())){
                //System.out.println(son.getData());
                return classify(son, example, atributos);
            }
        }
        return "Not found";
    }


    //torna os dados continuos em discretos


    public static List<String[]> discrete(List<String[]> values){
        String[] ex = values.get(0);
        for (int i = 1; i < ex.length; i++) {
            try{
                double aux = Double.parseDouble(ex[i]);
                double m = media(values, i);
                values = troca(values, i, m);
            }
            catch(NumberFormatException e){}
        }
        return values;
    }

    public static double media(List<String[]> values, int index){
        double acca = 0;
        int i = 0;
        for (i = 0; i < values.size(); i++) {
            acca += Double.parseDouble(values.get(i)[index]);
        }
        return acca/i;
    }

    public static List<String[]> troca(List<String[]> values, int index, double media){
        double aux = 0;
        String med = String.format("%.2f", media);
        //System.out.println(med);
        for (String[] ex : values) {
            aux = Double.parseDouble(ex[index]);
            if (aux<media) {
                ex[index] = "<" + med;
            }
            else{
                ex[index] = ">" + med;
            }
        }
        return values;
    }
}
