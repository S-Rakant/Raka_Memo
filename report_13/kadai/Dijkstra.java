//課題2: ダイクストラ法による最短経路検索  Dijkstra.java

import java.util.LinkedList;

/**
 * Dijkstra
 */
public class Dijkstra extends Graph {
    final double INFINITY = Double.POSITIVE_INFINITY;
    double[] D;
    LinkedList<Integer> X;
    LinkedList<Integer> V;
    int[] dijkstra_trace;

    public Dijkstra(){
        super();
        D = new double[7];
        X = new LinkedList<>();
        V = new LinkedList<>();
        dijkstra_trace = new int[7];
    }

    public void initialize(){ //配列Dを正の無限大で初期化
        for(int i = 0; i  < D.length; i++){
            D[i] = INFINITY;
        }
    }

    public int[] doDijkstra(int s){
        int[] result = new int[7];
        for(int i = 0; i < result.length; i++){ //resultを-1で初期化する。-1のままであるNodeは、startNodeからはたどり着けない頂点である
            result[i] = -1;
        }

        for(Node n : nodes){ //Vに全頂点を格納 V={0, 1, 2, 3, 4, 5, 6}
            V.add(n.getId());
        }
        for(int i = 0; i < nodes.size(); i++){ //XをVのサイズで全て-1で初期化
            X.add(-1);
        }
        
        initialize();
        D[s] = s;
        result[s] = s;

        // System.out.print("X = "); //Debug用
        // for(int i = 0; i < X.size(); i++){
        //     System.out.print(X.get(i) + ",");
        // }
        // System.out.println();

        // int count = 1; //Debug用
        while(!judgeEqual(X, V)){
            // System.out.println("whileに入りました."); //Debug用
            int u = calcMinD(X); //V-Xの中でD[]が最小である頂点uとする
            X.set(u, u); //Xにuを追加
            // // System.out.print("X = "); //Debug用
            // for(int i = 0; i < X.size(); i++){
            //     System.out.print(X.get(i) + ",");
            // }
            // System.out.println();
            LinkedList<Integer> edgeList= getEdge(nodes.get(u)); //頂点uに接続する各辺(u, v)の頂点vの集合
            for(int i = 0; i < edgeList.size(); i++){
                int v = edgeList.get(i);
                if(D[u] + getWeight(u, v) < D[v]){ //D[v]が更新される
                    D[v] = D[u] + getWeight(u, v);
                    result[v] = u;
                }
                // D[v] = min(D[v], D[u] + getWeight(u, v));
                // System.out.print("result = ");//Debug用
                // for(int j = 0; j < result.length; j++){
                //     System.out.print(result[j] + ",");
                // }
                // System.out.println();
            }
            // if(count == 10) return result; //Debug用
            // count++;
            // System.out.println("----------------------------------------");
        }
        for(int i = 0; i < result.length; i++){
            this.dijkstra_trace[i] = result[i]; //trace[]にresultをコピー
        }
        return result;
    }

    public int calcMinD(LinkedList<Integer> X){ //V-Xの中でD[]が最小である頂点uを返却
        LinkedList<Integer> V_X = new LinkedList<>();

        for(Node n : nodes){ //V_XをVで初期化
            V_X.add(n.getId());
        }

        for(int i = 0; i < X.size(); i++){ //Xに含まれるIDに対応するV_Xの値を-1にする
            for(int j = 0; j < V_X.size(); j++){
                if(X.get(i) == V_X.get(j)){
                    V_X.set(j, -1);
                }
            }
        }

        // System.out.print("V_X = "); //Debug用
        // for(int i = 0; i < V_X.size(); i++){
        //     System.out.print(V_X.get(i) + ",");
        // }
        // System.out.println();

        // System.out.print("D = "); //Debug用
        // for(int i = 0; i < D.length; i++){
        //     System.out.print(D[i] + ",");
        // }
        // System.out.println();

        double min = 0;
        int min_i = 0;
        for(int i = 0; i < V_X.size(); i++){
            if(V_X.get(i) == -1){
                continue;
            }
            else{
                min = D[V_X.get(i)];
                min_i = V_X.get(i);
                break;
            }
        }
        // System.out.println("min = " + min); //Debug用

        
        for(int i = 0; i < V_X.size(); i++){
            if(V_X.get(i) == -1){
                continue;
            }
            else{ //Xに含まれないものから選ぶ
                if(D[i] < min){
                    min = D[i];
                    min_i = i;
                }
            }
        }
        
        // System.out.println("u = " + min_i); //Debug用
        return min_i;
    }

    public boolean judgeEqual(LinkedList<Integer> a, LinkedList<Integer> b){ //LinkedList<Integer>型の中身が一致している場合-->true 一致していない場合-->false
        if(a.size() != b.size()){
            return false;
        }
        else{
            for(int i = 0; i < a.size(); i++){
                if(a.get(i) != b.get(i)){
                    return false;
                }
            }
        }
        // System.out.println("trueに到達"); //Debug用
        return true;
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

    public double getWeight(int u, int v){ //Node番号=uの頂点に隣接する頂点のうち,id=vとなる頂点v-->辺(u, v)の重みを求めるメソッド
        double result = 0;
        LinkedList<Edge> list = nodes.get(u).getList(); //uの頂点に隣接する頂点idの集合
        for(Edge edge : list){
            if(edge.getId() == v){
                result = edge.getWeight();
            }
        }
        return result;
    }

    public double min(double a, double b){
        return a < b ? a : b;
    }

    public void printIntArray(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] == -1){
                System.out.print("x,");
            }
            else{
                System.out.print(arr[i] + ",");
            }
        }
        if(arr[arr.length - 1] == -1){
            System.out.print("x");
        }
        else{
            System.out.print(arr[arr.length - 1]);
        }
        System.out.println();
    }

    public void printShortestPath(int start, int end){
        int arr[] = getShortestPath(start, end);
        System.out.print(end + ": ");
        for(int i = 0; i < arr.length - 1; i++){
            System.out.print(arr[i] + ",");
        }
        System.out.print(arr[arr.length - 1]);
        System.out.println();
    }

    public int[] getShortestPath(int start, int end){ //getBFSTreeで更新されたtrace配列をもとに始点から終点までの最短経路を求める
        LinkedList<Integer> temp = new LinkedList<>();
        temp.add(end);
        int v = end;
        while(v != dijkstra_trace[v]){
            v = dijkstra_trace[v];
            temp.add(v);
        }
        int[] result = new int[temp.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = temp.get(result.length - 1 - i);
        }
        return result;
    }
}