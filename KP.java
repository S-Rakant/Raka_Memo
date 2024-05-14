// KP.java
/**
 * KP
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class KP {
    public static void main(String[] args) {
        int num = 0, capacity = 0, answer = 0;
        int[] weight, value;

        BufferedReader file = null;

        // System.out.println("KP問題を解くファイル名を入力してください.");
        Scanner stdIn = new Scanner(System.in);
        String filename = args[0];

        try{
            file = new BufferedReader(new FileReader(filename));
        }catch(FileNotFoundException e){
            System.out.println(e);
        }

        try{
            String line = file.readLine();
            String[] temp = line.split(" ");
            num = Integer.parseInt(temp[0]); //品物の個数
            capacity = Integer.parseInt(temp[1]); //合計重量
    
            // System.out.println("num = " + num);
            // System.out.println("capacity = " + capacity);
    
            weight = new int[num];
            value = new int[num];
    
            int i = 0;
            // file.nextLine(); //1行目を飛ばす
            while((line = file.readLine()) != null){
                temp = line.split(" ");
                weight[i] = Integer.parseInt(temp[0]); //重さをweight配列に格納
                value[i] = Integer.parseInt(temp[1]); //価値をvalue配列に格納
                i++;
            }

            Solver s = new Solver(num, weight, value, capacity);
            answer = s.solve();
            System.out.println(answer);
        }catch(IOException e){
        }
        // System.out.println(Arrays.toString(weight));
        // System.out.println(Arrays.toString(value));


        stdIn.close();
    }
}
