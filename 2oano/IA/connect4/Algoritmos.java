import java.util.*;

class Algoritmos {

    public static int minimax(Board b){
        int ac=0;
        int depth = 0;
        int v = maxValue(b, ac, depth);
        return ac;
    }

    private static int maxValue(Board b, int ac, int depth){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MIN_VALUE;

        if(b.checkWin() || depth==5)
            return b.score();
        sons=b.sons();
        for (Board son : sons) {
            int m=minValue(son, ac, depth+1);
            if (v<m){
                v = m;
                ac = son.lastPlayY;
            }
        }
        return v;
    }


    private static int minValue(Board b, int ac, int depth){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin() || depth==5)
            return b.score();
        sons=b.sons();
        for (Board son : sons) {
            int m=minValue(son, ac, depth+1);
            if (v>m){
                v = m;
                ac = son.lastPlayY;
            }
        }
        return v;
    }


    public static int alfabeta(Board b){
        int ac=0;
        int depth = 0;
        int v = maxValue(b, ac, Integer.MAX_VALUE, Integer.MIN_VALUE, depth);
        return ac;
    }


    private static int maxValue(Board b, int ac, int alfa, int beta, int depth){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin() || depth==5)
            return b.score();
        sons=b.sons();
        for (Board son : sons) {
            int m=minValue(son, ac, alfa, beta, depth+1);
            if (v<m){
                v = m;
                ac = son.lastPlayY;
            }
            if (v <= alfa)
                return v;
            beta = min(beta, v);
        }
        return v;
    }


    private static int minValue(Board b, int ac, int alfa, int beta, int depth){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin() || depth==5)
            return b.score();
        sons=b.sons();
        for (Board son : sons) {
            int m=maxValue(son, ac, alfa, beta, depth+1);
            if (v>m){
                v = m;
                ac = son.lastPlayY;
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
