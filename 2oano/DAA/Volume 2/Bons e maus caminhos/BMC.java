import java.util.*;

class Arco {
  int no_final;

  Arco(int fim){
    no_final = fim;
  }

  int extremo_final() {
    return no_final;
  }
}


class No {
  //int label;
  LinkedList<Arco> adjs;

  No() {
    adjs = new LinkedList<Arco>();
  }
}


class Grafo0 {
  No verts[];
  int nvs, narcos;

  public Grafo0(int n) {
    nvs = n;
    narcos = 0;
    verts  = new No[n+1];
    for (int i = 0 ; i <= n ; i++)
    verts[i] = new No();
    // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
  }

  public int num_vertices(){
    return nvs;
  }

  public int num_arcos(){
    return narcos;
  }

  public LinkedList<Arco> adjs_no(int i) {
    return verts[i].adjs;
  }

  public void insert_new_arc(int i, int j){
    verts[i].adjs.addFirst(new Arco(j));
    narcos++;
  }

  public Arco find_arc(int i, int j){
    for (Arco adj: adjs_no(i))
    if (adj.extremo_final() == j) return adj;
    return null;
  }
}


public class BMC{
  static String bfs(Grafo0 g, int s){
    boolean[] visitado=new boolean[g.num_vertices()+1];
    Queue<Integer> fila = new LinkedList<Integer>();
    visitado[s]=true;
    String res="";
    res+=(char)(s+64);
    fila.add(s);
    do{
      int w=fila.remove();
      for(Arco y: g.adjs_no(w)){
        int x=y.extremo_final();
        if(!visitado[x]){
          res+=(char)(x+64);
          fila.add(x);
          visitado[x]=true;
        }
      }
    }while(!fila.isEmpty());
    return res;
  }
  static void resolve(String s1, String s2,Grafo0 g){
    int size=Math.min(s1.length(),s2.length());
    for(int i=0; i<size;i++){
      if(s1.charAt(i)!=s2.charAt(i)){
        g.insert_new_arc(((int)s1.charAt(i)-64),((int)s2.charAt(i)-64));
        break;
      }
    }
  }
  public static String find(char[] cur, Grafo0 g){
    for (int i=0; i<cur.length-1; i++) {
      if(g.find_arc(((int)cur[i]-64),((int)cur[i+1]-64))==null){
        return "Nao";
      }
    }
    return "Sim";

  }
  public static void main(String [] args){
    Scanner stdin=new Scanner(System.in);
    Grafo0 g=new Grafo0(27);
    String prev=stdin.nextLine();
    //char a= prev.charAt(0);
    String cur=stdin.nextLine();
    if(!cur.equals("#")){
      do{
        resolve(prev,cur, g);
        prev=cur;
        cur=stdin.nextLine();
      }while (!cur.equals("#"));
    }
    cur=stdin.nextLine();
    while(!cur.equals("#")){
      System.out.println(find(cur.toCharArray(), g));
      cur=stdin.nextLine();
    }
  }
}
