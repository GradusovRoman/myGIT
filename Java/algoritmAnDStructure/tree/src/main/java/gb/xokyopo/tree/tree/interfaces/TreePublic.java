package gb.xokyopo.tree.tree.interfaces;

public interface TreePublic<Key, Value> {
    int size();
    void insert(Key key, Value object);
    Value delete(Key key);
    Value find(Key key);
    boolean isContain(Key key);
    int countDeep();
}
