package gb.xokyopo.tree.tree;

import gb.xokyopo.tree.tree.interfaces.TreePublic;
import gb.xokyopo.tree.tree.templates.AbstractNodeTree;
import gb.xokyopo.tree.tree.templates.Node;

public class MyTreeMap<Key extends Comparable<Key>, Value> extends AbstractNodeTree<Key, Value> implements TreePublic<Key, Value> {
    private StringBuffer treeString;

    public MyTreeMap() {
        this.treeString = new StringBuffer();
    }

    protected Node<Key, Value> getFreeNode(Key key, Value value) {
        return new Node<>(key, value);
    }

    @Override
    public int size() {
        return this.getSize();
    }

    @Override
    public void insert(Key key, Value value) {
        this.addNode(this.getFreeNode(key, value));
    }

    @Override
    public Value delete(Key key) {
        this.requiredNotEmptyTree();

        if (this.isContain(key)) {
            return this.deleteNode(this.getFreeNode(key, null)).getObject();
        }
        return null;
    }

    @Override
    public Value find(Key key) {
        return null;
    }

    public boolean isContain(Key key) {
        return this.isContain(this.getFreeNode(key, null));
    }

    @Override
    public int countDeep() {
        return this.getDeeps();
    }
}
