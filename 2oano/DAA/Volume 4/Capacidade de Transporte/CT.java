import java.util.*;
import java.lang.*;

class Qnode {
    int vert;
    int vertkey;

    Qnode(int v, int key) {
	vert = v;
	vertkey = key;
    }
}

class Heapmax {
    private static int posinvalida = 0;
    int sizeMax,size;

    Qnode[] a;
    int[] pos_a;

    Heapmax(int vec[], int n) {
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

    int extractMax() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
	size--;
	heapify(1);
	return vertv;
    }

    void increaseKey(int vertv, int newkey) {

	int i = pos_a[vertv];
	a[i].vertkey = newkey;

	while (i > 1 && compare(i, parent(i)) > 0) {
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
	increaseKey(vertv,key);   // aumenta a chave e corrige posicao se necessario
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
	int l, r, largest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	largest = i;
	if (compare(l,largest) > 0)
	    largest = l;
	if (compare(r,largest) > 0)
	    largest = r;

	if (i != largest) {
	    swap(i, largest);
	    heapify(largest);
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

class CT {

  public static int min(int a, int b){
    if (a<b) {
      return a;
    }
    else return b;
  }
/*
  public static int Dijs(Grafo g, int or, int dest){
    int[] dist = new int[g.num_vertices()+1];
    boolean[] vis = new boolean[g.num_vertices()+1];
    for (int i=0;i<dist.length; i++) {
      dist[i]=0;
    }
    int count =0;
    dist[or]=Integer.MAX_VALUE;
    Heapmax q = new Heapmax(dist, g.num_vertices());
    while(!q.isEmpty()){
      int v = q.extractMax();
      //System.out.println(dist[v]);
      for (Arco y : g.adjs_no(v)) {
        count++;
        int w = y.extremo_final();
        if(min(dist[v],y.valor_arco())>dist[w]){
          dist[w]=min(dist[v],y.valor_arco());
        }
        q.increaseKey(w, dist[w]);
      }
    }
    return count;
  }

*/
  public static void main(String[] args) {
    Map <Integer, Integer> m = new HashMap<Integer, Integer>();
    Scanner sc = new Scanner(System.in);
    int nnos = sc.nextInt();
    Grafo g = new Grafo(1000);
    int lmin = sc.nextInt();
    int lmax = sc.nextInt();
    int cmin = sc.nextInt();
    int cmax = sc.nextInt();
    int alt = sc.nextInt();
    int or = sc.nextInt();
    int dest = sc.nextInt();
    int c = 0;
    m.put(or, 1);
    m.put(dest, 2);
    int count=3;
    int p1 = sc.nextInt();
    while(p1!=-1){
      int p2 = sc.nextInt();
      int maxl = sc.nextInt();
      int maxc = sc.nextInt();
      int maxa = sc.nextInt();
      if (maxl>=lmin && maxa>=alt && maxc>=cmin) {
        c++;
        if (!m.containsKey(p1)) {
          m.put(p1, count);
          count++;
        }
        if (!m.containsKey(p2)) {
          m.put(p2, count);
          count++;
        }
        g.insert_new_arc(m.get(p1), m.get(p2), maxl);
        g.insert_new_arc(m.get(p2), m.get(p1), maxl);
      }
      p1=sc.nextInt();
    }
    int qt= sc.nextInt();
    while(qt!=0){
      boolean flag = true;
      int max =Integer.MAX_VALUE;
      int pr = sc.nextInt();
      if (pr!=or) {
        flag=false;
      }
      for (int i=1; i<qt; i++) {
        int seg = sc.nextInt();
        if (g.find_arc(m.get(pr), m.get(seg))==null) {
          flag=false;
        }
        else max=min(max, g.find_arc(m.get(pr), m.get(seg)).valor_arco());
        pr=seg;
      }
      if (pr!=dest) {
        flag=false;
      }
      if (flag) {
        if (max>lmax) System.out.println(lmax);
        else System.out.println(max);
      }
      else System.out.println("Nao");
      qt=sc.nextInt();
    }
  //System.out.println(c);
}
}
