package gb.xokyopo.tree.tree;

public class MyTreeSet<Value extends Comparable<Value>> {
    private MyTreeMap<Value, Value> myTreeMap;

    public MyTreeSet() {
        this.myTreeMap = new MyTreeMap<>();
    }

    public void insert(Value value) {
        this.myTreeMap.insert(value, value);
    }

    public Value delete(Value value) {
        return this.myTreeMap.delete(value);
    }

    public Value find(Value value) {
        return this.myTreeMap.find(value);
    }

    public boolean isContain(Value value) {
        return this.myTreeMap.isContain(value);
    }

    public int size() {
        return this.myTreeMap.size();
    }

    public void clear() {
        this.myTreeMap.clear();
    }

    @Override
    public String toString() {
        return this.myTreeMap.toString();
    }

    public boolean isBalanced() {
        return this.myTreeMap.isBalanced();
    }

    public int countDeep() {
        return this.myTreeMap.countDeep();
    }
}

