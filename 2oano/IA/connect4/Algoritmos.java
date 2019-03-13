import java.util.*;

class Algoritmos {

    public static int minimax(Board b){
        int v = maxValue(b);
        return ac;
    }

    private static int maxValue(Board b){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin())
            return b.score();
        sons=sons(b);
        for (Board son : sons) {
            v = max(v, minValue(son));
        }
        return v;
    }


    private static int maxValue(Board b){
        Set<Board> sons = new HashSet<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin())
            return b.score();
        sons=sons(b);
        for (Board son : sons) {
            v = min(v, maxValue(son));
        }
        return v;
    }


    private static int max(int a, int b){
        return (a>b) ? a : b;
    }

    public int min(int a, int b){
        return (a>b) ? b : a;
    }
}