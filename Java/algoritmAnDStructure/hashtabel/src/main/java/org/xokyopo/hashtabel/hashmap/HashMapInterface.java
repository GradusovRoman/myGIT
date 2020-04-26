package org.xokyopo.hashtabel.hashmap;

public interface HashMapInterface<Key, Value> {
    void put(Key key, Value value);
    Value get(Key key);
    void del(Key key);
    Key[] keys();
    Value[] values();
    int size();
    boolean isEmpty();
}
