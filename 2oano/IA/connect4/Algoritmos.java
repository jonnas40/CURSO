import java.util.*;

class Algoritmos {

    private static int ac;

    public static int minimax(Board b){
        int depth = 0;
        int v = maxValue(b, depth);
        return ac;
    }

    private static int maxValue(Board b, int depth){
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin() || depth==2){
            return b.score();
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=minValue(son, depth+1);
            if (v<m){
                v = m;
                ac = son.lastPlayX;
            }
            //System.out.println(v);
        }
        return v;
    }


    private static int minValue(Board b, int depth){
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin() || depth==2){
            return b.score();
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=minValue(son, depth+1);
            if (v>m){
                v = m;
                ac = son.lastPlayX;
            }
        }
        return v;
    }


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
    }


    private static int max(int a, int b){
        return (a>b) ? a : b;
    }

    public static int min(int a, int b){
        return (a>b) ? b : a;
    }
}
