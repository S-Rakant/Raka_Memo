// 課題3: 深さ優先木の作成と最短経路探索 DFSTester.java
/**
 * DFSTester
 */
public class DFSTester {
    public static void main(String[] args) {
        int start = Integer.parseInt(args[1]);
        int end = Integer.parseInt(args[2]);

        DFSTree dfs = new DFSTree();
        dfs.loadGraph(args[0]);

        System.out.println("DFS Tree: root = 0");
        dfs.printIntArray(dfs.getDFSTree(start));
        System.out.println("\nShortest Path " + start + " -> " + end);
        dfs.printShortestPath(dfs.getShortestPath(start, end));
        dfs.printPathLength(dfs.getShortestPath(start, end));
    }
}