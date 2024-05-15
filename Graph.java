// グラフを表すクラスを作成 Graph.java

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Edge {
    private int id;
    Edge(int id){
        this.id = id;
    }

    int getId(){
        return id;
    }
}

class Node {
    private int id;
    LinkedList<Edge> list;
    private int visit;

    Node(String line){
        list = new LinkedList<>();
        String[] temp_1 = line.split(":");
        id = Integer.parseInt(temp_1[0]); //id
        String[] temp_2 = temp_1[1].split(",");
        for(String s : temp_2){ //あるNodeに隣接するEdgeを生成
            list.add(new Edge(Integer.parseInt(s)));
        }
    }

    LinkedList<Edge> getList(){
        return list;
    } 

    int getId(){
        return id;
    }

    void printEdge(){ //print Edge id
        int i = 0;
        if(list.size() > 0){
            while(i < list.size() - 1){
                System.out.print(list.get(i).getId() + ",");
                i++;
            }
            System.out.println(list.get(i).getId());
        }
    }

    void setVisit(int i){
        visit = i;
    }

    int getVisit(){
        return visit;
    }
}

public class Graph {
    LinkedList<Node> nodes;

    public Graph(){
        nodes = new LinkedList<>();
    }

    public LinkedList<Node> getNodeList(){
        return nodes;
    }

    public void addNode(String str){
        nodes.add(new Node(str));
    }

    public void loadGraph(String file){ //ファイルを読み込み、グラフのNodeを構成
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            try{
                while((line = reader.readLine()) != null){
                    addNode(line);
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    public void printGraph(){
        for(Node n : getNodeList()){
            System.out.print(n.getId() + ":"); //ノードid
            n.printEdge(); //ノードidに隣接するノードのid集合
        }
    }
}