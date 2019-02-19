class Tabuleiro{
  public static int[] newBoard(int size){
    return int[] board = new int[size];
  }
  public static int findSpace(int[] tab, int side){
    for (int i=1; i<=side*side; i++){
      if(tab[i]==0){
        return i;
      }
    }
    return 0;
  }
  public static int[] Right(int[] tab, int side){
    int space = findSpace(tab, side);
    if((space % side) != 0){
      tab[space] = tab[space + 1];
      tab[space + 1] = 0;
      return tab;
    }
    else{
      //nao faz nada
    }
  }
  public static int[] Left(int[] tab, int side) {
    int space = findSpace(tab, side);
    if((space % side) != 1){
      tab[space] = tab[space - 1];
      tab[space - 1] = 0;
      return tab;
    }
    else{
      //nao faz nada
    }
  }
  public static int[] Up(int[] tab, int side) {
    int space = findSpace(tab, side);
    if(space - side > 0){
      tab[space] = tab[space - side];
      tab[space - side] = 0;
      return tab;
    }
    else{
      //nao faz nada
    }
  }
  public static int[] Down(int[] tab, int side) {
    int space = findSpace(tab, side);
    if(space + side <= side*side){
      tab[space] = tab[space - side];
      tab[space -side] = 0;
      return tab;
    }
    else{
      //nao faz nada
    }
  }
}
