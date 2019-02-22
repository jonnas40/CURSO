import java.util.LinkedList;
import java.util.Queue;

class Algoritmos{

  public static Tabuleiro BFS(Tabuleiro tabI, Tabuleiro tabF){
    Tabuleiro w = new Tabuleiro(tabI.side*tabI.side);
    //Tabuleiro y = new Tabuleiro(tabI.side*tabI.side);
    Queue<Tabuleiro> fila = new LinkedList<Tabuleiro>();
    fila.add(tabI);
    do{
      w.copyTab(fila.remove());
      for(int i =0; w.adjs!=null; i++) {
        //y.copyTab(w.adjs[i]);
        if(w.adjs[i].compareTo(tabF)) return w.adjs[i];
        fila.add(w.adjs[i]);
      }
    }while(!fila.isEmpty());
    return null;
  }
  
}
