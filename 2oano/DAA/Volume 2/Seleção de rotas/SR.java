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

class SR{

  public static int analise (Grafo g, int[] rota, int or, int dest, int ngr){
    int prob=0;
    boolean ori = false; boolean des = false;
    for (int i=0; i<(rota.length-2); i+=2) {
      if (rota[i]==or) ori=true;
      if (!des) {
        if(ori){
          if (rota[i+1]<ngr) return -1;
        }
        if (g.find_arc(rota[i], rota[i+2])==null) return -1;
        prob+=g.find_arc(rota[i], rota[i+2]).valor_arco();
      }
      if(ori && rota[i+2]==dest) des = true;
    }
    if (ori && des) return prob;
    return -1;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int ngr = sc.nextInt();
    int or = sc.nextInt();
    int dest = sc.nextInt();
    int nnos = sc.nextInt();
    Grafo g = new Grafo(nnos);
    int nlig = sc.nextInt();
    for (int i = 0; i<nlig; i++) {
      g.insert_new_arc(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }
    int nrt = sc.nextInt();
    int min=nlig+1;
    int cur=0;
    for(int i = 0; i<nrt; i++){
      int n = sc.nextInt();
      n=(n*2)-1;
      int[] rota = new int[n];
      for(int j = 0; j<n; j++){
        rota[j]=sc.nextInt();
      }
      int temp=analise(g, rota, or, dest, ngr);
      if (temp==-1){
        continue;
      }
      if (min>temp) {
        min=temp;
        cur=i+1;
      }
    }
    if (min==nlig+1) {
      System.out.println("Impossivel");
    }
    else
      System.out.println("Reserva na rota "+cur+": "+min);
  }
}
