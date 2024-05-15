// BFS木に関するクラス BFSTree.java

import java.util.*;

/**
 * BFSTree
 */
public class BFSTree extends Graph{
    Queue<Integer> queue; //BFS用のキュー
    int[] bfs_trace = new int[400];

    public BFSTree(){
        super();
        queue = new LinkedList<Integer>(); 
    }

    public int[] getBFSTree(int root){
        int[] result = new int[400];
        result[root] = root;
        initialize(); //全頂点を未訪問に初期化
        setVisited(root); //始点sに訪問印をつける
        queue.offer(root); //Queueに始点sを追加
        while(queue.peek() != null){ //Queueが空になるまで繰り返す
            int u = queue.poll(); //Queueから頂点uを取り出す
            LinkedList<Integer> adj = getEdge(nodes.get(u)); //adj = uに隣接する頂点リスト
            while(adj.size() > 0){
                int v = adj.removeFirst(); 
                if(!checkVisited(nodes.get(v))){ //頂点vが未訪問の場合
                    result[v] = u; //result[v]に親の頂点を格納
                    setVisited(v); //vを既訪問にする
                    queue.offer(v); //vをQueueに追加する
                }
            }
        }
        for(int i = 0; i < result.length; i++){
            bfs_trace[i] = result[i];
        }
        return result;
    }

    public void initialize(){ //全てのNodeに未訪問の印を付与 未訪問-1 訪問積み1
        int i = 0;
        while(i < nodes.size()){
            nodes.get(i).setVisit(-1);
            i++;
        }
    }

    public void setVisited(int i){ //指定したNodeに訪問の印をつける
        nodes.get(i).setVisit(1);
    }

    public LinkedList<Integer> getEdge(Node node){ //あるNodeに隣接するNodeのIdをint型の双方向リストとして取得
        LinkedList<Integer> edgeList = new LinkedList<>();
        LinkedList<Edge> edge = node.getList();
        int i = 0;
        while(i < edge.size()){
            edgeList.addLast(edge.get(i).getId());
            i++;
        }
        return edgeList;
    }

    public boolean checkVisited(Node node){ //訪問済み-->true 未訪問-->false
        if(node.getVisit() == 1){
            return true;
        }
        else return false;
    }

    public void printIntArray(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            System.out.print(arr[i] + ",");
        }
        System.out.print(arr[arr.length - 1]);
        System.out.println();
    }

    public void printShortestPath(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            System.out.print(arr[i] + "->");
        }
        System.out.print(arr[arr.length - 1]);
        System.out.println();
    }

    public int[] getShortestPath(int start, int end){ //getBFSTreeで更新されたtrace配列をもとに始点から終点までの最短経路を求める
        LinkedList<Integer> temp = new LinkedList<>();
        temp.add(end);
        int v = end;
        while(v != bfs_trace[v]){
            v = bfs_trace[v];
            temp.add(v);
        }
        int[] result = new int[temp.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = temp.get(result.length - 1 - i);
        }
        return result;
    }
}