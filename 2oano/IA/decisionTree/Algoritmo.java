import java.util.*;

class Algoritmo{

    public static double entropy(String valor, int index, ArrayList<String[]> examples, Atributo classe){
        double entropy = 0;
        for (String aux : classe.values) {
            entropy += -(prob(valor, index, examples, aux) * log2(prob(valor, atributo.index, examples, aux)));
        }
    }  

    public static double prob(String valor, int index, ArrayList<String[]> examples, String classe){
        int counter = 0;
        int total = 0;
        for (String[] aux : examples) {
            if(aux[index].equals(valor)) counter++;
            total++;
        }
        return (double)counter/total;
    }

    


    public static double logb( double a, double b ){
        return Math.log(a) / Math.log(b);
    }

    public static double log2( double a ){
        return logb(a,2);
    }
    
}