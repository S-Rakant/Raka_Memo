// 課題2: 幅優先木の作成と最短経路探索 BFSTester.java
/**
 * BFSTester
 */
public class BFSTester {
    public static void main(String[] args) {
        // int start = Integer.parseInt(args[1]);
        // int end = Integer.parseInt(args[2]);
        int start = 0;
        int end = 200;

        BFSTree bfs = new BFSTree();
        bfs.loadGraph(args[0]);
        
        System.out.println("BFS Tree: root = 0");
        bfs.printIntArray(bfs.getBFSTree(start));
        System.out.println("Shortest Path " + start + " -> " + end);
        bfs.printShortestPath(bfs.getShortestPath(start, end));
    }
}