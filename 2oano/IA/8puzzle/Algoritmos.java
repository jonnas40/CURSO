import java.util.LinkedList;
import java.util.Queue;

class Algoritmos{

  public Tabuleiro BFS(Tabuleiro tabI, Tabuleiro tabF){
    Tabuleiro w = new Tabuleiro(tabI.side*tabI.side);
    Queue<Tabuleiro> fila = new LinkedList<Tabuleiro>();
    fila.add(tabI);
    do{
      w.copyTab(fila.remove());
      for(Tabuleiro y: w.adjs_no()){
        if(!y.compareTo(tabF)) fila.add(y);
        else return y;
      }
    }while(!fila.isEmpty());
    return null;
  }
  
}
