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
        
        for (String[] s : values) {
            for (int i = 0; i < s.length; i++) {
                System.out.print(s[i] + " ");
                //System.out.println(atributes[i] + " ");
            }
            System.out.println();
        }
        
        /*
        for (Atribute var : atributos) {
            System.out.println(var.getName());
            for (String opt : var.getValues()) {
                System.out.println(opt);
            }
            System.out.println();
        }*/

        //System.out.println(pluralityValue(values));
        List<String[]> examples = new ArrayList<String[]>();
        ID3(values, atributos, examples);
    }

    //ALGORITMO

    public static void ID3(List<String[]> values, LinkedList<Atribute> atributos, List<String[]> examples){
        List<String[]> exs = new ArrayList<String[]>();
        /*if (values.size()==0)
            return pluralityValue(examples);
        else if (allSame(values))
            return values.get(0)[classe.getIndex()];
        else if (atributos.isEmpty())
            return pluralityValue(values);
        else{*/
            //Atribute chosen;
            /*double argmax=Double.MAX_VALUE;
            for (Atribute a : atributos) {
                double max=entropy(a, values);
                if(max<argmax){
                    argmax=max;
                }
            }*/
            Atribute chosen=chosenOne(values, atributos);
            //adiciona à arvore
            for (String value : chosen.getValues()) {
                for (String[] var : examples) {
                    if(var[chosen.getIndex()].equals(value))
                        exs.add(var);
                }
            }
        //}
        chosen.printAtr();
    }

    //função para escolher o melhor atributo

    public static Atribute chosenOne(List<String[]> values, LinkedList<Atribute> atributos){
        Atribute chosen = null;
        double argmax=Double.MAX_VALUE;
        for (Atribute a : atributos) {
            double max=entropy(a, values);
            System.out.println(max);
            a.printAtr();
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
    
    public static String pluralityValue(List<String[]> values){
        int[] counter = new int[classe.noVal()];
        for (String[] var : values) {
            int i = 0;
            for (String cl : classe.getValues()) {
                if (cl.equals(var[classe.getIndex()])) {
                    counter[i]++;
                }
                i++;
            }
        }
        int max=0;
        int maxj=0;
        for(int j = 0; j<counter.length; j++) {
            if(counter[j]>max) {
                max=counter[j];
                maxj=j;
            }
        }
        return classe.getValues().get(maxj);
    }

    //função auxiliar
    
    public static Boolean allSame(List<String[]> values){
        String[] aux = values.get(0);
        String au = aux[classe.getIndex()];
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

    
    
}
