import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TestScanner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/home/nogueira/Downloads/restaurant.csv"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            System.out.print(scanner.next()+"|");
        }
        scanner.close();
    }

}