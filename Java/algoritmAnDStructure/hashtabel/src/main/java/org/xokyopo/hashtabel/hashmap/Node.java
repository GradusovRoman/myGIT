package org.xokyopo.hashtabel.hashmap;

import java.security.Key;

public class Node<Key, Value> {
    private Key key;
    private Value value;

    public Node(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Node() {
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }
}
