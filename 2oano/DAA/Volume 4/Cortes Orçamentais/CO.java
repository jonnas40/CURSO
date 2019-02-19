import java.lang.*;
import java.util.*;

class Qnode {
    int vert;
    int vertkey;

    Qnode(int v, int key) {
	vert = v;
	vertkey = key;
    }
}

class Heapmin {
    private static int posinvalida = 0;
    int sizeMax,size;

    Qnode[] a;
    int[] pos_a;

    Heapmin(int vec[], int n) {
	a = new Qnode[n + 1];
	pos_a = new int[n + 1];
	sizeMax = n;
	size = n;
	for (int i = 1; i <= n; i++) {
	    a[i] = new Qnode(i,vec[i]);
	    pos_a[i] = i;
	}

	for (int i = n/2; i >= 1; i--)
	    heapify(i);
    }

    boolean isEmpty() {
	if (size == 0) return true;
	return false;
    }

    int extractMin() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
	size--;
	heapify(1);
	return vertv;
    }

    void decreaseKey(int vertv, int newkey) {

	int i = pos_a[vertv];
	a[i].vertkey = newkey;

	while (i > 1 && compare(i, parent(i)) < 0) {
	    swap(i, parent(i));
	    i = parent(i);
	}
    }


    void insert(int vertv, int key)
    {
	if (sizeMax == size)
	    new Error("Heap is full\n");

	size++;
	a[size].vert = vertv;
	pos_a[vertv] = size;   // supondo 1 <= vertv <= n
	decreaseKey(vertv,key);   // diminui a chave e corrige posicao se necessario
    }

    void write_heap(){
	System.out.printf("Max size: %d\n",sizeMax);
	System.out.printf("Current size: %d\n",size);
	System.out.printf("(Vert,Key)\n---------\n");
	for(int i=1; i <= size; i++)
	    System.out.printf("(%d,%d)\n",a[i].vert,a[i].vertkey);

	System.out.printf("-------\n(Vert,PosVert)\n---------\n");

	for(int i=1; i <= sizeMax; i++)
	    if (pos_valida(pos_a[i]))
		System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }

    private int parent(int i){
	return i/2;
    }
    private int left(int i){
	return 2*i;
    }
    private int right(int i){
	return 2*i+1;
    }

    private int compare(int i, int j) {
	if (a[i].vertkey < a[j].vertkey)
	    return -1;
	if (a[i].vertkey == a[j].vertkey)
	    return 0;
	return 1;
    }


    private void heapify(int i) {
	int l, r, smallest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	smallest = i;
	if (compare(l,smallest) < 0)
	    smallest = l;
	if (compare(r,smallest) < 0)
	    smallest = r;

	if (i != smallest) {
	    swap(i, smallest);
	    heapify(smallest);
	}

    }

    private void swap(int i, int j) {
	Qnode aux;
	pos_a[a[i].vert] = j;
	pos_a[a[j].vert] = i;
	aux = a[i];
	a[i] = a[j];
	a[j] = aux;
    }

    private boolean pos_valida(int i) {
	return (i >= 1 && i <= size);
    }
}

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


class CO {

  public static boolean BFS (Grafo g, int or, int dest){
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
            //System.out.println("Sim "+ level[v]);
            return true;
          }
          q.add(v);
        }
      }
    } while (!q.isEmpty());
    //System.out.println("Nao");
    return false;
  }

  public static void DIJS (Grafo g, int or, int dest, int lim){
    int[] total = new int[g.num_vertices()+1];
    for (int i=0; i<total.length; i++) total[i]=10001;
    total[or]=0;
    Heapmin q = new Heapmin(total, g.num_vertices());
    while (!q.isEmpty()) {
      int v = q.extractMin();
      for (Arco y : g.adjs_no(v)) {
        int w = y.extremo_final();
        if (total[w]>total[v]+ y.valor_arco()) {
          total[w]= total[v]+ y.valor_arco();
          q.decreaseKey(w,total[w]);
        }
      }
    }
    if (total[dest]>lim) System.out.println("Nao");
    else System.out.println("Sim");
  }

  public static void main(String[] args) {
    //Map <Integer, Integer> m = new HashMap<Integer, Integer>();
    Scanner sc = new Scanner(System.in);
    int tmin = sc.nextInt();
    int tmax = sc.nextInt();
    int or = sc.nextInt();
    int dest = sc.nextInt();
    /*m.put(or, 1);
    m.put(dest, 2);
    int count=3;*/
    int nnos = sc.nextInt();
    int nlig = sc.nextInt();
    Grafo g = new Grafo(nnos);
    for (int i=0; i<nlig; i++) {
      int p1 = sc.nextInt();
      int p2 = sc.nextInt();
      int temp = sc.nextInt();
      int cus = sc.nextInt();
      if (temp>=tmin && temp<=tmax) {
        /*m.put(p1, count);
        count++;
        m.put(p2, count);
        count++;*/
        //System.out.println(p1);
        //System.out.println(p2);
        g.insert_new_arc(p1, p2, cus);
        g.insert_new_arc(p2, p1, cus);
      }
    }
    int ncas = sc.nextInt();
    for (int i=0; i<ncas; i++) {
      int lim = sc.nextInt();
      if(BFS(g, or, dest)){
        DIJS(g, or, dest, lim);
      }
      else System.out.println("Nao");
    }
  }
}
