import java.util.*;



class CM {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int l = sc.nextInt();
      int c = sc.nextInt();
      float[][] m = new float[l+1][c+1];
      float f;
      for (int i=1; i<=l; i++) {       //valores m[0][*] e m[*][0] não são utilizados
        for (int j=1; j<=c; j++) {
          String aux = sc.next();
          f = Float.parseFloat(aux);
          m[i][j]=f;
        }
      }
      float[] luc = new float[c+1];
      luc[0]=0;
      int[] cont= new int[c+1];
      for (int i=1; i<=c; i++) luc[i]=m[1][i];
      for (int i=2; i<=l; i++) {
        for (int k=c; k>=1; k--) {
          for (int t=0; t<k; t++) {
            if (m[i][k-t]+luc[t]==luc[k]) cont[k]++;
            if (m[i][k-t]+luc[t]>luc[k]){
               luc[k]=m[i][k-t]+luc[t];
               cont[k]=1;
             }
          }
        }
      }
      float max=0;
      int maxi=0;
      for (int i=0; i<=c; i++) if (luc[i]>max) {
        max=luc[i];
        maxi=i;
      }
      System.out.println("Lucro Maximo = "+max);
      System.out.println("No.Sols = "+cont[maxi]);
    }
}
