import java.util.*;

class Algoritmos {

    public static int minimax(Board b){
        int depth = 0;
        Board v = maxValue(b, depth+1);
        System.out.println(v.score);
        return v.lastPlayX;
    }

    private static Board maxValue(Board b, int depth){
      Board ac = new Board();
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin() || depth==4){
            return b;
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=minValue(son, depth+1).score;
            if (v<m){
                v = m;
                Board.copyBoard(son,ac);
            }
            //System.out.println(v);
        }
        return ac;
    }


    private static Board minValue(Board b, int depth){
      Board ac = new Board();
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin() || depth==4){
            return b;
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=maxValue(son, depth+1).score;
            if (v>m){
                v = m;
                Board.copyBoard(son,ac);
            }
        }
        return ac;
    }

/*
    public static int alfabeta(Board b){
        int depth = 0;
        int v = maxValueAB(b, Integer.MAX_VALUE, Integer.MIN_VALUE, depth);
        return ac;
    }


    private static int maxValueAB(Board b, int alfa, int beta, int depth){
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin() || depth==2){
            return b.score();
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=minValueAB(son, alfa, beta, depth+1);
            if (v<m){
                v = m;
                ac = son.lastPlayX;
            }
            if (v <= alfa)
                return v;
            beta = min(beta, v);
        }
        return v;
    }


    private static int minValueAB(Board b, int alfa, int beta, int depth){
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin() || depth==2){
            return b.score();
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=maxValueAB(son, alfa, beta, depth+1);
            if (v>m){
                v = m;
                ac = son.lastPlayX;
            }
            if (v >= beta)
                return v;
            alfa = max(alfa, v);
        }
        return v;
    }*/


    private static int max(int a, int b){
        return (a>b) ? a : b;
    }

    public static int min(int a, int b){
        return (a>b) ? b : a;
    }
}
