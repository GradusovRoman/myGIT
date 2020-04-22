package gb.xokyopo.tree.tree.templates;

import gb.xokyopo.tree.tree.exceprions.TreeCheckedException;
import gb.xokyopo.tree.tree.exceprions.TreeUncheckedException;

public abstract class AbstractNodeTree <Key extends Comparable<Key>, Values> {
    private Node<Key, Values> root;
    private int size;

    protected boolean isEmpty() {
        return size <= 0;
    }

    protected int getSize() {
        return size;
    }

    protected void requiredNotEmptyTree() throws TreeUncheckedException {
        if (this.isEmpty()) throw new TreeUncheckedException("Tree can't be Empty");
    }

    protected void requiredNotNullObject(Object object) {
        if (object == null) throw new TreeUncheckedException("Object can't be Null");
    }

    protected boolean isContain(Node<Key, Values> node) {
        return this.isContain(node, this.root);
    }

    private boolean isContain(Node<Key, Values> node, Node<Key, Values> startNode) {
        if (this.isEmpty()) return false;

        try {
            Node<Key, Values> parentNode = this.getParentNode(node, startNode);
            return (parentNode.compareTo(node) > 0) ? parentNode.getBelow() != null : parentNode.getAbove() != null;
        } catch (TreeCheckedException e) {
            return startNode.compareTo(node) == 0;
        }
    }

    protected void addNode(Node<Key, Values> node) {
        if (this.root == null) {
            this.root = node;
        } else if (this.root.compareTo(node) == 0) {
            this.root.setObject(node.getObject());
            this.size--;
        } else {
            try {
                this.addNode(node, this.root);
            } catch (TreeCheckedException e) {
                e.printStackTrace();
            }
        }
        this.size++;
    }

    private void addNode(Node<Key, Values> node, Node<Key, Values> startNode) throws TreeCheckedException {
        Node<Key, Values> parentNode = this.getParentNode(node, startNode);

        if (this.isContain(node, parentNode)) {
            if (parentNode.compareTo(node) > 0) parentNode.getBelow().setObject(node.getObject());
            else parentNode.getAbove().setObject(node.getObject());
        } else {
            if (parentNode.compareTo(node) > 0) parentNode.setBelow(node);
            else parentNode.setAbove(node);
        }
    }

    //return null if startNode == null || startNode.compareTo(node) == 0;
    private Node<Key, Values> getParentNode(Node<Key, Values> node, Node<Key, Values> startNode) throws TreeCheckedException{
        if ((startNode == null ? 0: startNode.compareTo(node)) == 0) throw new TreeCheckedException("startNode incorrect");

        Node<Key, Values> currentNode = startNode;
        Node<Key, Values> parentNode = null;
        //currentNode == null or currentNode.compareTo(node) == 0 stop wile{}
        while ((currentNode != null ? currentNode.compareTo(node) : 0) != 0) {
            parentNode = currentNode;
            if (currentNode.compareTo(node) > 0) {
                currentNode = currentNode.getBelow();
            } else {
                currentNode = currentNode.getAbove();
            }
        }
        return parentNode;
    }

    private Node<Key, Values> deleteNode(Node<Key, Values> node, Node<Key, Values> startNode) throws TreeCheckedException{
        Node<Key, Values> parentNode = this.getParentNode(node, startNode);
        Node<Key, Values> resultNode;

        if (parentNode.compareTo(node) > 0) {
            resultNode = parentNode.getBelow();
            parentNode.setBelow(null);
        } else {
            resultNode = parentNode.getAbove();
            parentNode.setAbove(null);
        }

        if (resultNode.getAbove() != null) this.addNode(resultNode.getAbove(), parentNode);
        if (resultNode.getBelow() != null) this.addNode(resultNode.getBelow(), parentNode);

        return resultNode;
    }

    //TODO оптимизация
    protected Node<Key, Values> deleteNode(Node<Key, Values> node) {
        this.requiredNotEmptyTree();
        Node<Key, Values> result = null;

        if (this.isContain(node)) {
            if (this.root.compareTo(node) == 0) {
                result = this.root;
                this.root = null; //TODO тут root перестал существовать но почему он в дереве.
                if (result.getAbove() != null) this.addNode(result.getAbove());
                if (result.getBelow() != null) this.addNode(result.getBelow());
            } else {
                try {
                    result = this.deleteNode(node, this.root);
                } catch (TreeCheckedException e) {
                    e.printStackTrace();
                }
            }
            this.size--;
            return result;
        } else {
            throw new TreeUncheckedException("Node not found");
        }
    }

    //TODO оптимизация
    protected Node<Key, Values> findNode(Node<Key, Values> node) {
        this.requiredNotEmptyTree();
        this.requiredNotNullObject(node);

        if (this.isContain(node, this.root)) {
            Node<Key, Values> result = null;

            if (this.root.compareTo(node) == 0) {
                result = this.root;
            } else {
                try {
                    Node<Key, Values> parentNode = this.getParentNode(node, this.root);
                    result = (parentNode.compareTo(node) > 0) ? parentNode.getBelow() : parentNode.getAbove();
                } catch (TreeCheckedException e) {
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            throw new TreeUncheckedException("Node not found");
        }
    }

    protected int getDeeps() {
        return (this.root == null) ? 0 : this.root.getDeep();
    }

    public boolean isBalanced() {
        this.requiredNotEmptyTree();
        return this.isBalancedTree(this.root);
    }

    private boolean isBalancedTree(Node<Key, Values> node) {
        this.requiredNotNullObject(node);

        if (this.isBalancedNode(node)) {
            boolean above = (node.getAbove() == null) || this.isBalancedTree(node.getAbove());
            boolean below = (node.getBelow() == null) || this.isBalancedTree(node.getBelow());
            return above && below;
        } else {
            return false;
        }
    }

    private boolean isBalancedNode(Node<Key, Values> node) {
        if (node.getDeep() == 1) {
            return true;
        } else {
            int below = (node.getBelow() != null) ? node.getBelow().getDeep() : 0;
            int above = (node.getAbove() != null) ? node.getAbove().getDeep() : 0;
            int diff = above - below;
            return diff >= -1 && diff <= 1;
        }
    }

    public void clear() {
        this.size = 0;
        this.root = null;
    }

    public String toString() {
        if (this.root != null) {
            StringBuffer treeString = new StringBuffer();
            treeString.append("{");
            this.createToString(this.root, treeString);
            treeString.append("}");
            return treeString.toString();
        } else {
            return "{null}";
        }
    }

    private void createToString(Node<Key, Values> node, StringBuffer treeString) {
        if(node.getBelow() != null) this.createToString(node.getBelow(), treeString);
        treeString.append(node.toString());
        if (node.getAbove() != null) this.createToString(node.getAbove(), treeString);
    }
}
