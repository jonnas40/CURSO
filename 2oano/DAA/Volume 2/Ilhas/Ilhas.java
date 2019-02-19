import java.util.LinkedList;
import java.util.Scanner;


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
    int gr;
    LinkedList<Arco> adjs;

    No(int i) {
      int gr=i;
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
	    verts[i] = new No(i);
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

class Ilhas {
  static int [] pai;
  static void set_dad(boolean[] pais, int max){
    for (int i=1; i<pais.length; i++) {
      if(pais[i]) pai[i]=max;
    }
  }

  public static void BFS_visit(Grafo0 g, int s, boolean[] vis){
    int v, w=g.num_vertices();
    boolean[] pais= new boolean[vis.length];
    LinkedList<Integer> l = new LinkedList<Integer>();
    int max=s;
    l.addLast(s);
    vis[s]=true; pais[s]=true;
    do{
      v = l.pop();
      for (Arco adjs:g.adjs_no(v)) {
        w=adjs.extremo_final();
        if (!vis[w]) {
          l.addLast(w);
          vis[w]=true;
          pais[w]=true;
          if(max<w) max = w;
        }
      }
    } while (l.size()!=0);
    set_dad(pais, max);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int nnos = sc.nextInt();
    int nlig = sc.nextInt();
    Grafo0 g = new Grafo0(nnos);
    pai=new int[nnos+1];
    boolean [] vis = new boolean[nnos+1];
    for (int i=0; i<nlig; i++) {
      int no2 = sc.nextInt();
      int no1 = sc.nextInt();
      g.insert_new_arc(no2, no1);
      g.insert_new_arc(no1, no2);
    }
    for (int i=1; i<=nnos; i++) {
      if(!vis[i]) BFS_visit(g, i, vis);
    }
    int nil = sc.nextInt();
    for (int i = 0; i<nil; i++) {
      int p = sc.nextInt();
      System.out.println("No "+ p+": "+pai[p]);
    }
  }
}



/*

12 9
4 3
3 5
5 2
7 1
9 11
8 1
7 8
5 4
2 10
14 1 5 10 9 4 1 8 9 6 12 2 3 7 3

*/
