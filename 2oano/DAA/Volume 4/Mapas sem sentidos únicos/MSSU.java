import java.util.*;
import java.lang.*;

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

class MSSU{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int nt = sc.nextInt();
    int nnos = sc.nextInt();
    Grafo0 g = new Grafo0(nnos);
    int[] ct = new int[nt+1];
    for (int i=1; i<=nt; i++) {
      int nlig = sc.nextInt();
      int p1 = sc.nextInt();
      for (int j=1; j<nlig; j++) {
        ct[i]+=sc.nextInt();
        int p2 = sc.nextInt();
        if (g.find_arc(p1, p2)==null) {
          g.insert_new_arc(p1, p2);
          g.insert_new_arc(p2, p1);
        }
        p1=p2;
      }
    }
    for (int i=1; i<=nt; i++) System.out.println("Trajeto "+i+": "+ct[i]);
    for (int i=1; i<=nnos; i++) {
      int count=0;
      for (Arco y : g.adjs_no(i)) {
        count++;
      }
      System.out.println("No "+i+": "+count);
    }
  }
}
