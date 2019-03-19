import java.util.*;

class Algoritmos {


    public static int minimax(Board b, int depth){
        Board v = new Board();
        v = maxValue(b, depth-1);
        return v.lastPlayX;
    }

    private static Board maxValue(Board b, int depth){
        Board ac = new Board();
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin() || depth==0){
            return b;
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=minValue(son, depth-1).score;
            if (v<m){
                v = m;
                Board.copyBoard(son,ac);
            }
        }
        return ac;
    }


    private static Board minValue(Board b, int depth){
        Board ac = new Board();
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin() || depth==0){
            return b;
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=maxValue(son, depth-1).score;
            if (v>m){
                v = m;
                Board.copyBoard(son,ac);
            }
        }
        return ac;
    }


    public static int alfabeta(Board b, int depth){
        Board v = new Board();
        v = maxValueAB(b, Integer.MIN_VALUE, Integer.MAX_VALUE, depth-1);
        return v.lastPlayX;
    }


    private static Board maxValueAB(Board b, int alfa, int beta, int depth){
        Board ac = new Board();
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MIN_VALUE;
        if(b.checkWin() || depth==0){
            return b;
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=minValueAB(son, alfa, beta, depth-1).score;
            if (v<m){
                v = m;
                Board.copyBoard(son,ac);
            }
            if (v >= beta)
                return ac;
            alfa = max(alfa, v);
        }
        return ac;
    }


    private static Board minValueAB(Board b, int alfa, int beta, int depth){
        Board ac = new Board();
        LinkedList<Board> filhos = new LinkedList<Board>();
        int v = Integer.MAX_VALUE;
        if(b.checkWin() || depth==0){
            return b;
        }
        filhos=Board.sons(b);
        for (Board son : filhos) {
            int m=maxValueAB(son, alfa, beta, depth-1).score;
            if (v>m){
                v = m;
                Board.copyBoard(son,ac);
            }
            if (v <= alfa)
                return ac;
            beta = min(beta, v);
        }
        return ac;
    }


    public static Board MCTS(Board b, int depth){
        LinkedList<Board> filhos = new LinkedList<Board>();
        Board ac = new Board();
        filhos=Board.sons(b);
        
        if (test.magicheck()!=0) {
            if (n == 0) {
                v += rollout(test);
            }
        }
    }


    private static int simulate(Board b){
        Board test = new Board();
        Board.copyBoard(b,test);
        while (test.magicheck()==0){
            int esc = (int)(Math.round(Math.random() * 7))/1;
            Board a = test.play(esc);
            if (!Board.compareBoard(a, test)) {
                test = test.play(esc);
            }
        }
        if (test.magicheck()==1) return 1;
        else return 0;
    }

    private static float UCB1(int v, int n, int j){
        return (float)(v/n) + 7 * (Math.sqrt(Math.log(n)/j));
    }


    private static int max(int a, int b){
        return (a>b) ? a : b;
    }

    public static int min(int a, int b){
        return (a>b) ? b : a;
    }
}
