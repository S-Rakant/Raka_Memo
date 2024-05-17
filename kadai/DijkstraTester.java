//課題2: ダイクストラ法による最短経路探索 DijkstraTester.java
/**
 * DijkstraTester
 */
public class DijkstraTester {
    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();

        int start = Integer.parseInt(args[1]);
        int end = Integer.parseInt(args[2]);
        dijkstra.loadGraph(args[0]);
        dijkstra.doDijkstra(start);
        dijkstra.printShortestPath(start, end);
    }
}