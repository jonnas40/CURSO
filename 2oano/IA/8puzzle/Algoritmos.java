import java.util.LinkedList;
import java.util.Queue;

class Algoritmos{
  public Tabuleiro BFS(Tabuleiro tabI, Tabuleiro tabF){
    Tabuleiro w = new Tabuleiro(tabI.side*tabI.side);
    Queue<Tabuleiro> fila = new LinkedList<Tabuleiro>();
    fila.add(tabI);
    do{
      w.copyTab(fila.remove());
      for(Arco y: g.adjs_no(w)){
        int x=y.extremo_final();
        if(!visitado[x]){
          res+=(char)(x+64);
          fila.add(x);
          visitado[x]=true;
        }
      }
    }while(!fila.isEmpty());
    return null;
  }
}
