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

class BacalhausCongelados{
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int nvts= sc.nextInt();
    int narcs= sc.nextInt();
    Grafo g = new Grafo(nvts);
    int par, che, val, cust;
    for (int i=1; i<=narcs; i++) {
      par=sc.nextInt();
      che=sc.nextInt();
      val=sc.nextInt();
      cust=sc.nextInt();
      g.insert_new_arc(par, che, val);
      g.insert_new_arc(che, par, val);
    }
    int perc= sc.nextInt();
    int prev, curr, max, min, t;
    while (perc!=0){
      //System.out.println("perc: " + perc);
      prev=sc.nextInt();
      //System.out.println("P: " + prev);
      curr=sc.nextInt();
      //System.out.println("C: " + curr);
      t=g.find_arc(prev, curr).valor_arco();
      max=min=t;
      for (int i=2; i<perc; i++) {
          prev=curr;
          //System.out.println("P: " + prev);
          curr=sc.nextInt();
          //System.out.println("C: " + curr);
          t=g.find_arc(prev, curr).valor_arco();
          if(t>max) max=t;
          else if (t<min) min=t;
      }
      System.out.println(min + " " + max);
      perc=sc.nextInt();
  }
}
}

/*
6 10
1 4 10 35
3 5 -22 30
1 2 -27 50
5 2 20 51
3 2 3 27
2 4 -1 5
5 6 9 20
1 5 30 20
4 6 25 45
6 3 -15 40
9 5 1 4 2 3 6 4 2 1
5 1 4 2 3 6
2 1 5
5 2 3 6 4 2
7 5 6 4 1 5 2 3
0

*/
