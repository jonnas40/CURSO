import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Algoritmos2{

  public static Tabuleiro BFS(Tabuleiro tabI, Tabuleiro tabF){
    Tabuleiro w = new Tabuleiro(tabI.side);
    Queue<Tabuleiro> fila = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> visited = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> aux = new LinkedList<Tabuleiro>();
    fila.add(tabI);
    do{
      w = fila.remove();
      if(Arrays.equals(w.board,tabF.board)){
        break;
      }
      aux = Tabuleiro.tabSons(w, tabF, visited);
      visited.addAll(aux);
      fila.addAll(aux);
    }while(!fila.isEmpty());
    return w;
  }


  public static Tabuleiro DFS(Tabuleiro tabI, tabF){
    Tabuleiro w = new Tabuleiro(tabI.side);
    Stack<Tabuleiro> s = new Stack<Tabuleiro>();
    LinkedList<Tabuleiro> visited = new LinkedList<Tabuleiro>();
  }
}
