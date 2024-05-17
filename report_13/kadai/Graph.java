//課題1: Graphクラスの重み付きグラフの拡張 Graph.java

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Edge {
    private int id;
    private double weight;

    Edge(int id, double weight){
        this.id = id;
        this.weight = weight;
    }

    int getId(){
        return id;
    }

    double getWeight(){
        return weight;
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
        if(temp_1.length > 1){ //隣接ノードが存在する場合 この条件分岐がないと、temp_2 = temp_1[1]でtem_1.size = 1の場合にエラー(OutOfIndex)
            String[] temp_2 = temp_1[1].split(",");
            for(int i = 0; i < temp_2.length; i++){
                String[] temp_3 = temp_2[i].split("@");
                list.add(new Edge(Integer.parseInt(temp_3[0]), Double.parseDouble(temp_3[1]))); //あるノードに隣接するノードのidと重みをそれぞれリストに格納
            }
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
                System.out.print(list.get(i).getId() + "@" + list.get(i).getWeight() + ",");
                i++;
            }
            System.out.println(list.get(i).getId() + "@" + list.get(i).getWeight());
        }
        else{
            System.out.println();
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