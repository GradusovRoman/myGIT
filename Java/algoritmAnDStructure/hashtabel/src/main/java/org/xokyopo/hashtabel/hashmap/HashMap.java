package org.xokyopo.hashtabel.hashmap;

import org.xokyopo.hashtabel.hashset.HashSet;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HashMap<Key, Values> implements HashMapInterface<Key, Values>{
    private HashSet<Node<Key, Values>> hashSet;

    public HashMap(int capacity) {
        this.hashSet = new HashSet<>(capacity);
    }

    public HashMap() {
        this.hashSet = new HashSet<>();
    }

    private Node<Key, Values> createNode(Key key, Values values) {
        return new Node<>(key, values);
    }

    private Node<Key, Values> createNode(Key key) {
        return new Node<>(key, null);
    }

    private void requiredNotNullObject(Object object, String text) {
        if (object == null) throw new NullPointerException(text);
    }

    @Override
    public void put(Key key, Values values) {
        this.requiredNotNullObject(key, "Key can't null");
        this.hashSet.put(this.createNode(key, values));
    }

    @Override
    public Values get(Key key) {
        return this.hashSet.get(this.createNode(key)).getValue();
    }

    @Override
    public void del(Key key) {
        this.hashSet.del(this.createNode(key));
    }

    @Override
    public Key[] keys() {
        if (!this.hashSet.isEmpty()) {
            return this.getKeyByNodes(this.getNodeLis());
        } else {
            return null;
        }
    }

    @Override
    public Values[] values() {
        if (!this.hashSet.isEmpty()) {
            return this.getValuesByNodes(this.getNodeLis());
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return this.hashSet.size();
    }

    private Key[] getKeyByNodes(List<Node<Key,Values>> list) {
        Key[] outer = this.createKeyArr(list.size());
        for (int i = 0; i < list.size(); i++) {
            outer[i] = list.get(i).getKey();
        }
        return outer;
    }

    private Values[] getValuesByNodes(List<Node<Key,Values>> list) {
        Values[] outer = this.createValuesArr(list.size());
        for (int i = 0; i < list.size(); i++) {
            outer[i] = list.get(i).getValue();
        }
        return outer;
    }

    @SuppressWarnings("unchecked")
    private Key[] createKeyArr(int length) {
        return (Key[]) new Object[length];
    }

    @SuppressWarnings("unchecked")
    private Values[] createValuesArr(int length) {
        return (Values[]) new Object[length];
    }

    @Override
    public boolean isEmpty() {
        return this.hashSet.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        if (!this.hashSet.isEmpty()) {
            List<Node<Key, Values>> list = this.getNodeLis();
            list.forEach(node -> {
                stringBuilder.append("[").append(node.getKey()).append(",").append(node.getValue()).append("]");
            });
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private List<Node<Key, Values>> getNodeLis() {
        return Arrays.asList(Objects.requireNonNull(this.hashSet.values()));
    }
}
