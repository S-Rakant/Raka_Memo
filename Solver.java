// Solver.java
/**
 * Solver
 */
public class Solver {
    int num, capacity, answer;
    int[] weight, value;

    public Solver(int num, int[] weight, int[] value, int capacity){
        this.num = num;
        this.weight = weight;
        this.value = value;
        this.capacity = capacity;
        this.answer = 0;
    }

    public int solve(){
        return knapsack();
    }

    public int knapsack(){
        int[][] kpMap = new int[num + 1][capacity + 1]; //2次元配列の大きさは揃えた方が分かりやすい。(平方行列)
        for(int i = 0; i <= num; i++){
            for(int w = 1; w <= capacity; w++){
                if(i == 0){ //i=0の場合は何も入れれない
                    kpMap[i][w] = 0;
                }
                else{
                    if(weight[i - 1] > w){ //i番目の荷物単独でも容量を超える場合
                        kpMap[i][w] = kpMap[i - 1][w];
                    }
                    else{ //i番目の荷物を使わないときと使うときで価値が大きいほう
                        kpMap[i][w] = max(kpMap[i - 1][w], kpMap[i - 1][w - weight[i - 1]] + value[i - 1]);
                    }
                }
            }
        }
        return kpMap[num][capacity];
    }

    public int max(int a, int b){
        return a > b ? a : b;
    }
}