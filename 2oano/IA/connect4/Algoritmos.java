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
        if(b.magicheck()!=0 || depth==0){
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
        if(b.magicheck()!=0 || depth==0){
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
        if(b.magicheck()!=0 || depth==0){
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
        if(b.magicheck()!=0 || depth==0){
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
        Node<Board> node;
        double UCB = Double.MIN_VALUE;
        Node<Board> chosen = new Node<>(b);
        
        for (int i = 0; i < depth; i++) {

            Set <Node<Board>> visitados = new HashSet <Node<Board>>();

            node = root;
            visitados.add(node);
            
            while(!node.isLeaf()){
                for (Node<Board> test : node.getChildren()) {
                    if(UCB < UCB1(test.getData().getT(), test.getData().getN(), i)){
                        chosen = test;
                        UCB = UCB1(test.getData().getT(), test.getData().getN(), i);
                    }
                }
                UCB = Double.MIN_VALUE;
                node = chosen;
                visitados.add(node);
            }
            UCB = Double.MIN_VALUE;

            node.addChildren(node.getData().sonsM());

            for (Node<Board> test : node.getChildren()) {
                if(UCB < UCB1(test.getData().getT(), test.getData().getN(), i)){
                    chosen = test;
                    UCB = UCB1(test.getData().getT(), test.getData().getN(), i);
                }
            }
            UCB = Double.MIN_VALUE;

            int v = rollout(chosen.getData());
            
            Board cho = chosen.getData();
            cho.setT(v);
            cho.setN();
            chosen.setData(cho);

            for (Node<Board> pai : visitados) {
                cho = pai.getData();
                cho.setT(v);
                cho.setN();
                pai.setData(cho);
            }
            
        }

        int esc=0;
        Node<Board> ac = new Node<>(b);
        for (Node<Board> ans : root.getChildren()) {
            if( ans.getData().getT() > esc){
                esc = ans.getData().getT();
                ac = ans;
            }
        }
        return ac.getData().lastPlayX;
    }


    private static int rollout(Board b){
        while (b.magicheck()==0){
            int esc = (int)(Math.round(Math.random() * 6))/1;
            if (b.actions().contains(esc)) {
                b = b.play(esc);
            }
        }
        if (b.magicheck()==1) return 1;
        else return 0;
    }

    private static double UCB1(int v, int n, int j){
        if(n==0) return Integer.MAX_VALUE;
        return (double)(v/n) + 7 * (Math.sqrt(Math.log(j)/n));
    }


    private static int max(int a, int b){
        return (a>b) ? a : b;
    }

    public static int min(int a, int b){
        return (a>b) ? b : a;
    }
}

