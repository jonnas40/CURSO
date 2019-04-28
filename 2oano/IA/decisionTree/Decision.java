import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class Decision{

    public static void main(String[] args) {
        
        //leitura e parsing dos csv's
        String fileName = args[0];
        File file = new File(fileName);
        ArrayList<String[]> values = new ArrayList<String[]>();
        try{
            Scanner inputStream = new Scanner(file);
            String data = inputStream.next();
            String[] atributes = data.split(",");
            while(inputStream.hasNext()){
                data = inputStream.next();
                String[] aux = data.split(",");
                values.add(aux);
            }
            inputStream.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        /*for (String[] s : values) {
            for (int i = 0; i < s.length; i++) {
                System.out.print(s[i] + " ");
            }
            System.out.println();
        }*/
        
        //criação de objetos de atributos
        LinkedList<Atribute> atributos = new LinkedList<Atribute>();

        for (int i = 0; i<atributes.length; i++) {
            Atribute a = new Atribute(atributes[i], i, values);
            atributos.add(a);
        }

    }

    
    
}
