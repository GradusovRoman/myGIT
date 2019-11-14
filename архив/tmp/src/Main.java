import game.Game;

public class Main {
    public static void main(String[] args) {
        //Game gameInstance = new Game();
        //gameInstance.initGame();

        //StringBuffer a = new StringBuffer()
        printArr();
    }

    public static void printArr(){
        for (int i = 0; i < 100; i++){
            System.out.println(i);
            return ;
        }
    }

    public int[] insertion_binary(int[] data){
        for (int i = 1 ; i < data.length ; i++){
            int key = data[i];
            int lo = 0;
            int hi = i - 1;

            while(lo < hi){
                int mid = lo + (hi - lo);
                if (key < data[mid]){
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            for (int j = i ; j != lo + 1 ; j--){
                data[j] = data[j - 1];
            }
            data[lo] = key;
        }
        return data;
    }

}

