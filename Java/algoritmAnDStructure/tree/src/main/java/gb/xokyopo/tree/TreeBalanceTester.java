package gb.xokyopo.tree;

import gb.xokyopo.tree.tree.MyTreeSet;

import java.util.Random;

//TODO исправить класс.

public class TreeBalanceTester {
    private final Random random = new Random();
    private boolean haveResult;
    private Object[] treeArr;
    private int result;

    protected interface NewValue<T> { T get();}

    public boolean run(int roots, int rootsMaxDeep, int startRandomInt, int stopRandomInt) {
        this.haveResult = false;
        System.out.println("Создаю деревья.");
        this.generateTrees(roots, rootsMaxDeep, startRandomInt, stopRandomInt);
        System.out.println("Все деревья созданы.\nПерехожу к расчетам");
        this.result = this.getPercentBalancedTree(this.treeArr);
        this.haveResult = true;
        return true;
    }

    private void generateTrees(int roots, int rootsMaxDeep, int startRandomInt, int stopRandomInt) {
        this.treeArr = new Object[roots];
        for (int i = 0; i < this.treeArr.length; i++) {
            this.treeArr[i] = this.getTree(rootsMaxDeep, ()->this.getRandomInteger(startRandomInt, stopRandomInt));;
        }
    }

    private MyTreeSet<Integer> getTree(int deep, NewValue<Integer> function) {
        MyTreeSet<Integer> treeSet = new MyTreeSet<>();
        this.filingTree(treeSet, deep, function);
        System.out.println(treeSet.toString());
        return treeSet;
    }

    private void filingTree(MyTreeSet<Integer> tree, int deep, NewValue<Integer> function) {
        while (tree.countDeep() < deep) {
            tree.insert(function.get());
        }
    }

    private Integer getRandomInteger(int start, int stop) {
        int amplitude = stop - start;
        return start + this.random.nextInt(amplitude);
    }

    @SuppressWarnings("unchecked")
    private int getPercentBalancedTree(Object[] treeAsObject) {
        int count = 0;
        for (int i = 0; i < treeAsObject.length; i++) {
            MyTreeSet<Integer> treeSet = (MyTreeSet<Integer>) treeAsObject[i];
            if(treeSet.isBalanced()) count++;
        }
        return (treeAsObject.length == 0)? 0 : (100 * count) / treeAsObject.length;
    }

    public int getResult() {
        if (this.haveResult)
            return result;
        else
            throw new TreeBalanceException("execute Run() method before getResult");
    }

    protected class TreeBalanceException extends RuntimeException {
        public TreeBalanceException(String message) {
            super(message);
        }
    }
}
