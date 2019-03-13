import java.util.*;

class Algoritmos {

    public static int minimax(Board b){
        int ac;
        int v = maxValue(b, ac);
        return ac;
    }

    private static int maxValue(Board b, int ac){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin())
            return b.score();
        sons=sons(b);
        for (Board son : sons) {
            int m=minValue(son, ac);
            if (v<m){
                v = m;
                ac = son.lastPlayY;
            }
        }
        return v;
    }


    private static int maxValue(Board b, int ac){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin())
            return b.score();
        sons=sons(b);
        for (Board son : sons) {
            int m=minValue(son, ac);
            if (v>m){
                v = m;
                ac = son.lastPlayY;
            }
        }
        return v;
    }


    public static int alfabeta(Board b){
        int ac;
        int v = maxValue(b, ac, Integer.MAX_VALUE, Integer.MIN_VALUE);
        return ac;
    }


    private static int maxValue(Board b, int ac, int alfa, int beta){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin())
            return b.score();
        sons=sons(b);
        for (Board son : sons) {
            int m=minValue(son, ac, alfa, beta);
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


    private static int maxValue(Board b, int ac, int alfa, int beta){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin())
            return b.score();
        sons=sons(b);
        for (Board son : sons) {
            int m=minValue(son, ac, alfa, beta);
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