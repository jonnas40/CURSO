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


    public static int MCTS(Board b, int depth){
        Node<Board> root = new Node<>(b);
        Node<Board> pai = root;
        double UCB = Double.MIN_VALUE;
        Node<Board> chosen = new Node<>(b);
        Node<Board> chosenSon = new Node<>(b);
        Node<Board> dummy = new Node<>(b);
        Board dum = new Board();
        Board cho = new Board();
        Board choso = new Board();
        root.addChildren(Board.sonsM(b));
        for (int i = 0; i < depth; i++) {
            for (Node<Board> test : pai.getChildren()) {
                if(UCB < UCB1(test.getData().getT(), test.getData().getN(), i)){
                    chosen = test;
                    UCB = UCB1(test.getData().getT(), test.getData().getN(), i);
                }
            }
            UCB = Double.MIN_VALUE;
            //System.out.println(UCB1(chosen.getData().getT(), chosen.getData().getN(), i));
            //Board.printBoard(chosen.getData());
            if (chosen.getData().getN() == 0 && chosen.getChildren()!=null) {
                int v = rollout(chosen.getData());
                cho = chosen.getData();
                cho.setT(v);
                cho.setN();
                chosen.setData(cho);
                dummy =chosen.getParent();
                dum = dummy.getData();
                dum.setT(v);
                dum.setN();
                dummy.setData(dum);
                while (dummy.getParent()!=null) {
                    dummy =dummy.getParent();
                    dum = dummy.getData();
                    dum.setT(v);
                    dum.setN();
                    dummy.setData(dum);
                }
            }
            else {
                if (chosen.getChildren()!=null){
                    chosen.addChildren(Board.sonsM(chosen.getData()));
                }
                for (Node<Board> test : chosen.getChildren()) {
                    if(UCB < UCB1(test.getData().getT(), test.getData().getN(), i)){
                        chosenSon = test;
                        UCB = UCB1(test.getData().getT(), test.getData().getN(), i);
                    }
                }
                int v = rollout(chosenSon.getData());
                choso = chosenSon.getData();
                choso.setT(v);
                choso.setN();
                chosenSon.setData(choso);
                dummy=chosenSon.getParent();
                dum = dummy.getData();
                dum.setT(v);
                dum.setN();
                dummy.setData(dum);
                while (dummy.getParent()!=null) {
                    dummy =dummy.getParent();
                    dum = dummy.getData();
                    dum.setT(v);
                    dum.setN();
                    dummy.setData(dum);
                } 
            }
        }
        int esc=0;
        Node<Board> ac = new Node<>(b);
        for (Node<Board> ans : root.getChildren()) {
            if( (ans.getData().getT()/ans.getData().getN()) > esc){
                esc = (ans.getData().getT()/ans.getData().getN());
                ac = ans;
            }
        }
        return ac.getData().lastPlayX;
    }


    private static int rollout(Board b){
        Board test = new Board();
        Board.copyBoard(b,test);
        while (test.magicheck()==0){
            int esc = (int)(Math.round(Math.random() * 6))/1;
            //System.out.println(esc);
            Board a = test.play(esc);
            if (!Board.compareBoard(a, test)) {
                test = test.play(esc);
            }
        }
        if (test.magicheck()==1) return 1;
        else return 0;
    }

    private static double UCB1(int v, int n, int j){
        if(n==0) return Integer.MAX_VALUE;
        return (double)(v/n) + 7 * (Math.sqrt(Math.log(n)/j));
    }


    private static int max(int a, int b){
        return (a>b) ? a : b;
    }

    public static int min(int a, int b){
        return (a>b) ? b : a;
    }
}

