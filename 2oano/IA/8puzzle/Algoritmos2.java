import java.util.LinkedList;
import java.util.Queue;

class Algoritmos2{

  public static Tabuleiro BFS(Tabuleiro tabI, Tabuleiro tabF){
    Tabuleiro fin = new Tabuleiro(tabF.side);
    Tabuleiro w = new Tabuleiro(tabI.side);
    Queue<Tabuleiro> fila = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> visited = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> aux = new LinkedList<Tabuleiro>();
    fila.add(tabI);
    do{
      w = fila.remove();
      if(w.board == tabF.board){
        fin = w;
        break;
      }
      aux = Tabuleiro.tabSons(w, tabF, visited);
      visited.addAll(aux);
      fila.addAll(aux);
    }while(!fila.isEmpty());
    return fin;
  }

}
