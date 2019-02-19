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


class Socio{

    public static void DFS_visit(int h, Grafo0 g, boolean[] vis, Stack<Integer> s){
      vis[h]=true;
      for ( Arco y : g.adjs_no(h) ) {
        int w = y.extremo_final();
        if(!vis[w])
          DFS_visit(w, g, vis, s);
      }
      s.push(h);
    }

    public static Stack<Integer> DFS(Grafo0 g){
    Stack<Integer> s = new Stack<Integer>();
    boolean[] vis = new boolean[g.num_vertices()+1];
    for (int h=1; h<=g.num_vertices(); h++) {
      if (!vis[h])
        DFS_visit(h, g, vis, s);
    }
    return s;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int it = sc.nextInt();
    for (int i = 1; i<=it; i++) {
      int gr=0;
      int nnos = sc.nextInt();
      Grafo0 g = new Grafo0(nnos);
      Grafo0 gt = new Grafo0(nnos);
      int fora=0;
      for (int j=0; j<nnos; j++) {
        int id = sc.nextInt();
        int nrel = sc.nextInt();
        for (int m=0; m<nrel; m++) {
          int rel = sc.nextInt();
          g.insert_new_arc(id, rel);
          gt.insert_new_arc(rel, id);
        }
      }
    Stack<Integer> pilha = DFS(g);
    boolean[] visitado = new boolean[nnos+1];
    while (!pilha.isEmpty()){
      Stack<Integer> st = new Stack<Integer>();
      int no = pilha.pop();
      if(!visitado[no]){
        DFS_visit(no, gt, visitado, st);
      }
      int cont=st.size();
      if(cont>=4)
        gr++;
      else
        fora+=cont;
    }
      System.out.println("Caso #"+i);
      System.out.println(gr + " " +fora);
    }
  }
}