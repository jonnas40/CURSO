import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;

//test

class Algoritmos{

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


  public static Tabuleiro DFS(Tabuleiro tabI, Tabuleiro tabF){
    Tabuleiro w = new Tabuleiro(tabI.side);
    LinkedList<Tabuleiro> visited = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> aux = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> fila = new LinkedList<Tabuleiro>();
    fila.addLast(tabI);
    do{
      w = fila.removeFirst();
      if(Arrays.equals(w.board,tabF.board)){
        break;
      }
      aux = Tabuleiro.tabSons(w, tabF, visited);
      visited.addAll(0, aux);
      fila.addAll(0, aux);
    }while(!fila.isEmpty());
    return w;
  }


  public static Tabuleiro LDFS(Tabuleiro tabI, Tabuleiro tabF, int lim){
    Tabuleiro w = new Tabuleiro(tabI.side);
    LinkedList<Tabuleiro> visited = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> aux = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> fila = new LinkedList<Tabuleiro>();
    fila.addLast(tabI);
    fila.addLast(tabI);
    do{
      w = fila.removeFirst();
      if(Arrays.equals(w.board,tabF.board)){
        break;
      }
      if(w.depth<=lim){
        aux = Tabuleiro.tabSons(w, tabF, visited);
        visited.addAll(0, aux);
        fila.addAll(0, aux);
      }
    }while(!fila.isEmpty());
    return w;
  }


  public static Tabuleiro IDFS(Tabuleiro tabI, Tabuleiro tabF){
    Tabuleiro w = new Tabuleiro(tabI.side);
    for (int i = 0; i < 20; i++) {
      w=LDFS(tabI, tabF, i);
      if (Arrays.equals(w.board, tabF.board)) return w;
    }
    return tabI;
  }


  public static Tabuleiro GreedyM(Tabuleiro tabI, Tabuleiro tabF){
    PriorityQueue<Tabuleiro> pq = new PriorityQueue<Tabuleiro>(10, new greedyMComparator());
    LinkedList<Tabuleiro> aux = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> visited = new LinkedList<Tabuleiro>();
    Tabuleiro w = new Tabuleiro(tabI.side);
    pq.add(tabI);
    do{
      w=pq.poll();
      if(Arrays.equals(w.board,tabF.board)) break;
      aux = Tabuleiro.tabSonsGreedy(w, tabF, visited);
      visited.addAll(0, aux);
      pq.addAll(aux);
    }while (!pq.isEmpty());
    return w;
  }


  public static Tabuleiro GreedyO(Tabuleiro tabI, Tabuleiro tabF){
    PriorityQueue<Tabuleiro> pq = new PriorityQueue<Tabuleiro>(10, new greedyOComparator());
    LinkedList<Tabuleiro> aux = new LinkedList<Tabuleiro>();
    LinkedList<Tabuleiro> visited = new LinkedList<Tabuleiro>();
    Tabuleiro w = new Tabuleiro(tabI.side);
    pq.add(tabI);
    do{
      w=pq.poll();
      if(Arrays.equals(w.board,tabF.board)) break;
      aux = Tabuleiro.tabSons(w, tabF, visited);
      visited.addAll(0, aux);
      pq.addAll(aux);
    }while (!pq.isEmpty());
    return w;
  }


  public static Tabuleiro AStar(Tabuleiro tabI, Tabuleiro tabF){
    PriorityQueue<Tabuleiro> pq = new PriorityQueue<Tabuleiro>(1000000, new AOComparator());
    LinkedList<Tabuleiro> aux = new LinkedList<Tabuleiro>();
    Map<int[],Integer> visited = new HashMap<int[],Integer>();
    Tabuleiro w = new Tabuleiro(tabI.side);
    pq.add(tabI);
    do{
      w=pq.poll();
      if(Arrays.equals(w.board,tabF.board)) break;
      aux = Tabuleiro.tabSonsA(w, tabF, visited);
      pq.addAll(aux);
    }while(!pq.isEmpty());
    return w;
  }

}

class greedyOComparator implements Comparator<Tabuleiro>{

  public int compare(Tabuleiro s1, Tabuleiro s2) {
      if (s1.scoreO < s2.scoreO)
          return -1;
      else if (s1.scoreO > s2.scoreO)
          return 1;
      return 0;
      }
}

class greedyMComparator implements Comparator<Tabuleiro>{

  public int compare(Tabuleiro s1, Tabuleiro s2) {
      if (s1.scoreM < s2.scoreM)
          return -1;
      else if (s1.scoreM > s2.scoreM)
          return 1;
      return 0;
      }
}

class AOComparator implements Comparator<Tabuleiro>{

  public int compare(Tabuleiro s1, Tabuleiro s2) {
      if ( (s1.depth + s1.scoreO) < (s2.depth + s2.scoreO) )
          return -1;
      else if ( (s1.depth + s1.scoreO) > (s2.depth + s2.scoreO) )
          return 1;
      return 0;
      }
}

class AMComparator implements Comparator<Tabuleiro>{

  public int compare(Tabuleiro s1, Tabuleiro s2) {
      if ( (s1.depth + s1.scoreM) < (s2.depth + s2.scoreM) )
          return -1;
      else if ( (s1.depth + s1.scoreM) > (s2.depth + s2.scoreM) )
          return 1;
      return 0;
      }
}
