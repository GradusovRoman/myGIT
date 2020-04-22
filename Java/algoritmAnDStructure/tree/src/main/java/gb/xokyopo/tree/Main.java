package gb.xokyopo.tree;


public class Main {


    public static void main(String[] args) {
        int roots = 20;
        int rootsMaxDeep = 6;
        int startRandomInt = -100;
        int stopRandomInt = 100;

        TreeBalanceTester treeBalanceTester = new TreeBalanceTester();
        treeBalanceTester.run(roots, rootsMaxDeep, startRandomInt, stopRandomInt);
        System.out.println("сбалансированных деревьев: " + treeBalanceTester.getResult() + "%");

    }
}
