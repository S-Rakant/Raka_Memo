// DFS木に関するクラス DFSTree.java

import java.util.LinkedList;

/**
 * DFSTree
 */

 class Edge_for_BFS { //辺を2つの頂点として管理するクラス
    int from;
    int to;
    Edge_for_BFS(int from, int to){
        this.from = from;
        this.to = to;
    }

    int getFrom(){
        return from;
    }

    int getTo(){
        return to;
    }

    void setFrom(int from){
        this.from = from;
    }

    void setTo(int to){
        this.to = to;
    }
 }
public class DFSTree extends BFSTree {
    LinkedList<Edge_for_BFS> stack; //2つの頂点の組をスタックで管理する
    int[] dfs_trace = new int[400];

    public DFSTree(){
        super();
        stack = new LinkedList<>(); //スタックSの生成
    }

    public int[] getDFSTree(int root){ //スタックSを生成してから実行するメソッド
        int[] result = new int[400];
        result[root] = root;
        initialize(); //全ての頂点に未訪問の印をつける
        setVisited(root); //始点sに訪問印をつける
        LinkedList<Integer> adjOfStart = getEdge(nodes.get(root)); //始点sに隣接する頂点の集合
        for(int i = adjOfStart.size() - 1; i >= 0; i--){
            stack.offerFirst(new Edge_for_BFS(root, adjOfStart.get(i))); //各辺(s, v)をスタックSに入れる
        }
        while(stack.peek() != null){
            Edge_for_BFS temp = stack.removeFirst(); //Sから辺(x, y)を取り出す
            if(!checkVisited(nodes.get(temp.getTo()))){ //yが未訪問の場合
                setVisited(temp.getTo()); //yを既訪問にする
                result[temp.getTo()] = temp.getFrom(); //resultを更新
                LinkedList<Integer> adjOfY = getEdge(nodes.get(temp.getTo())); //yに隣接する頂点の集合
                for(int i = adjOfY.size() - 1; i >= 0; i--){
                    stack.offerFirst(new Edge_for_BFS(temp.getTo(), adjOfY.get(i))); //各辺(s, v)をスタックSに入れる
                }
            }
        }
        for(int i = 0; i < result.length; i++){
            dfs_trace[i] = result[i];
        }
        return result;
    }

    @Override
    public int[] getShortestPath(int start, int end){ //getDFSTreeで更新されたtrace配列をもとに始点から終点までの最短経路を求める
        LinkedList<Integer> temp = new LinkedList<>();
        temp.add(end);
        int v = end;
        while(v != dfs_trace[v]){
            v = dfs_trace[v];
            temp.add(v);
        }
        int[] result = new int[temp.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = temp.get(result.length - 1 - i);
        }
        return result;
    }

    public void printPathLength(int[] arr){
        System.out.println("Path Length = " + (arr.length - 1));
    }
}