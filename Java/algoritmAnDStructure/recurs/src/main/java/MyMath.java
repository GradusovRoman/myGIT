
public class MyMath {

    public int powFunction(int a, int n) {
        int result = a;
        for (int i = 0; i < n -1 ; i++) {
            result *=a;
        }
        return result;
    }

    public int powRecurs(int a, int n) {
        if (n == 1) return a;
        else return a * this.powRecurs(a, n -1);
    }
}
