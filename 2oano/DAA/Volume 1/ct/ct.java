import java.util.Scanner;
class ct{

  public static int scanPath(int path[], int nnos, int cam){
    for(int i=0; i<nnos; i++){
      if(path[i]==cam) return i+1;
    }
    path[nnos]=cam;
    return nnos+1;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner (System.in);
    int [] path = new int [30];
    int cam=sc.nextInt();
    int nnos=0;
    while (cam!=0){
      nnos=scanPath(path, nnos, cam);
      cam=sc.nextInt();
    }
    for (int j=0; j<nnos ;j++ ) {
      System.out.println(path[j]);
    }
  }
}
