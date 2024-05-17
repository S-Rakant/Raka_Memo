//課題1: Graphクラスの重み付きグラフへの拡張 GraphTester.java
/**
 * GraphTester
 */
public class GraphTester {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.loadGraph(args[0]);
        graph.printGraph();
    }
}
