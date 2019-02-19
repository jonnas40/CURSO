import java.util.LinkedList;
import java.util.Scanner;

class Arco {
    int no_final;
    int valor0;
    int valor1;

    Arco(int fim, int v0, int v1){
	no_final = fim;
	valor0  = v0;
	valor1 = v1;
    }

    int extremo_final() {
	return no_final;
    }

    int valor0_arco() {
	return valor0;
    }

    int valor1_arco() {
	return valor1;
    }

    void novo_valor0(int v) {
	valor0 = v;
    }

    void novo_valor1(int v) {
	valor1 = v;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo2 {
    No verts[];
    int nvs, narcos;

    public Grafo2(int n) {
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

    public void insert_new_arc(int i, int j, int valor0, int valor1){
	verts[i].adjs.addFirst(new Arco(j,valor0,valor1));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}

class Reservas {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int nnos = sc.nextInt();
    int nlig = sc.nextInt();
    Grafo2 g = new Grafo2(nnos);
    for (int i=0; i<nlig; i++) {
      int ori = sc.nextInt();
      int dest = sc.nextInt();
      int lug = sc.nextInt();
      int pre = sc.nextInt();
      g.insert_new_arc(ori, dest, lug, pre);
    }
    int nres = sc.nextInt();
    for (int i=0; i<nres; i++) {
      int res = sc.nextInt();
      int path=sc.nextInt();
      int [] v = new int[path];
      for (int j = 0; j<v.length; j++) {
        v[j]=sc.nextInt();
      }
      corre(g, res, v);
    }
  }

  public static void corre(Grafo2 g, int res, int v[]){
    int custo=0;
    for (int j=0; j<v.length-1; j++) {
      if(g.find_arc(v[j], v[j+1])!=null){
        if(g.find_arc(v[j], v[j+1]).valor0_arco()>=res){
          custo+=(g.find_arc(v[j], v[j+1]).valor1_arco())*res;
        }
        else {
          System.out.println("Sem lugares suficientes em ("+v[j]+","+v[j+1]+")");
          return;
        }
      }
      else{
        System.out.println("("+v[j]+","+v[j+1]+") inexistente");
        return;
      }
    }
    for (int j=0; j<v.length-1; j++) {
      g.find_arc(v[j], v[j+1]).novo_valor0(g.find_arc(v[j], v[j+1]).valor0_arco()-res);
    }
    System.out.println("Total a pagar: "+custo);
  }
}
