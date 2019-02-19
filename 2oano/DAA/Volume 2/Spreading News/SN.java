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


class SN {

/*  public static int dboom(int w, Grafo0 g){
    int x;
    for (Arco y : g.adjs_no(w)) {
        x=y.extremo_final();
        if(!visitados[x]){

        }
  }*/

  public static void bfs(Grafo0 g, int s){
    boolean[] visitados = new boolean[g.num_vertices()+1];
    Queue<Integer> fila = new LinkedList<Integer>();
    int[] dist = new int[g.num_vertices()+1];
    int[] dtotal = new int[g.num_vertices()+1];
    visitados[s]=true;
    fila.add(s);
    dist[s]=0;
    int w, x, count=0, max=0;
    do {
      w=fila.remove();//System.out.println(w);
      /*if(g.adjs_no(w).size()>max) {
        max=g.adjs_no(w).size();
        count=dist[w];
      }*/
      for (Arco y : g.adjs_no(w)) {
        x=y.extremo_final();
        if(!visitados[x]){
          fila.add(x);
          dtotal[dist[w]]++;
          dist[x]=dist[w]+1;
          visitados[x]=true;
        }
      }
    } while (!fila.isEmpty());
    for(int i=0; i<dist.length; i++){
      if(max<dtotal[dist[i]]) {
        max = dtotal[dist[i]];
        count = dist[i];
      }
    }
    if(max==0 && count == 0) System.out.println(0);
    else System.out.println(max + " " + (count+1));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int e = sc.nextInt();
    Grafo0 g = new Grafo0(e);
    for (int i = 1; i<=e; i++) {
      int f = sc.nextInt();
      for (int j=0; j<f; j++) {
        g.insert_new_arc(i, sc.nextInt()+1);
      }
    }
    int test = sc.nextInt();
    for (int i=0; i<test; i++) {
      int src = sc.nextInt()+1;
      bfs(g, src);
    }
  }
}




 /*
6
2 1 2
2 3 4
3 0 4 5
1 4
0
2 0 2
3
0
4
5

 */
