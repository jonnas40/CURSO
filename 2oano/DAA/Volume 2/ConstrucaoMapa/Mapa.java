import java.util.LinkedList;
import java.util.Scanner;

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

class Mapa {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int nos = sc.nextInt();
    int traj = sc.nextInt();
    Grafo g = new Grafo(nos);
    for (int i=0; i<traj; i++) {
      int nnos = sc.nextInt();
      int prev, curr;
      prev = sc.nextInt();
      for (int j=1; j<nnos; j++) {
        curr = sc.nextInt();
        g.insert_new_arc(prev, curr, 0);
        prev = curr;
      }
    }
    for (int i=1; i<=nos; i++) {
      int cont = 0;
      for (int j = 1; j<=nos; j++) {
        if (g.find_arc(i, j)!=null) {
          cont++;
        }
      }
      System.out.println(cont);
    }
  }
}

/*
6 3
4 1 2 5 4
9 2 3 2 1 5 6 3 5 2
5 3 1 6 5 2

*/
