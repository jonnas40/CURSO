import java.util.*;

class Arco {
    int no_final;
    int valor;

    Arco(int fim, int v){
	no_final = fim;
	valor = v;
    }

    int extremo_final() {
	return no_final;
    }

    int valor_arco() {
	return valor;
    }

    void novo_valor(int v) {
	valor = v;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo {
    No verts[];
    int nvs, narcos;

    public Grafo(int n) {
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

    public void insert_new_arc(int i, int j, int valor_ij){
	verts[i].adjs.addFirst(new Arco(j,valor_ij));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}


class TL {

  public static void BFS (Grafo g, int or, int dest){
    int w, v;
    Queue<Integer> q = new LinkedList<Integer>();
    boolean[] visitados = new boolean[g.num_vertices()+1];
    int[] level = new int[g.num_vertices()+1];
    q.add(or);
    level[or]=0;
    visitados[or]=true;
    do {
      w=q.remove();
      for (Arco y : g.adjs_no(w)) {
        v=y.extremo_final();
        if (!visitados[v]) {
          level[v]=level[w]+1;
          visitados[v]=true;
          //System.out.println(level[v]);
          if (v==dest) {
            System.out.println("Sim "+ level[v]);
            return;
          }
          q.add(v);
        }
      }
    } while (!q.isEmpty());
    System.out.println("Nao");
    return;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int tmin = sc.nextInt();
    int tmax = sc.nextInt();
    int or = sc.nextInt();
    int dest = sc.nextInt();
    int nnos = sc.nextInt();
    int nlig = sc.nextInt();
    Grafo g = new Grafo(nnos);
    for (int i=0; i<nlig; i++) {
      int p1 = sc.nextInt();
      int p2 = sc.nextInt();
      int temp = sc.nextInt();
      int cus = sc.nextInt();
      if (temp>=tmin && temp<=tmax) {
        //System.out.println(p1);
        //System.out.println(p2);
        g.insert_new_arc(p1, p2, cus);
        g.insert_new_arc(p2, p1, cus);
      }
    }
    BFS(g, or, dest);
  }
}
