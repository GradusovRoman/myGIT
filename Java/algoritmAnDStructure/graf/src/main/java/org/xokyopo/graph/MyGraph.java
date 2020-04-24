package org.xokyopo.graph;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.xokyopo.graph.template.Graph;
import org.xokyopo.graph.template.GraphInterface;
import org.xokyopo.graph.template.GraphNode;

import java.util.*;

public class MyGraph<T> implements GraphInterface<T> {
    private Graph<T> graph;

    public MyGraph() {
        this.graph = new Graph<>();
    }

    private GraphNode<T> getNode(T value) {
        return new GraphNode<>(value);
    }

    @Override
    public void add(T element) {
        this.graph.add(this.getNode(element));
    }

    @Override
    public void addLinkBetween(T left, T right) {
        this.graph.addLinkBetween(this.getNode(left), this.getNode(right));
    }

    @Override
    public void remove(T element) {
        this.graph.remove(this.getNode(element));
    }

    @Override
    public void removeLinkBetween(T left, T right) {
        this.graph.removeLinkBetween(this.getNode(left), this.getNode(right));
    }

    @Override
    public T find(T element) {
        GraphNode<T> node = this.graph.find(this.getNode(element));
        if (node != null) return node.getValue();
        else return null;
    }

    @Override
    public int nodes() {
        return this.graph.nodes();
    }

    @Override
    public int links() {
        return this.graph.links();
    }

    @Override
    public void clear() {
        this.graph.clear();
    }

    public int findClosesPath(T first, T second) {
        if(this.isContain(first) && this.isContain(second)) {
            if (!first.equals(second)) {
                Map<GraphNode<T>, Integer> map = new HashMap<>();
                List<GraphNode<T>> list = new ArrayList<>();
                list.add(this.graph.find(this.getNode(first)));

                int[] deep = {0};
                this.graph.forEachByWight(node->{
                    deep[0] = map.getOrDefault(node, 0);
                    if (node.getValue().equals(second)) {
                        return true;
                    } else {
                        node.getLinks().forEach(link -> {
                            if (!map.containsKey(link)) {
                                map.put(link, deep[0] + 1);
                            }
                        });
                        deep[0] = -1;
                        return false;
                    }
                }, list);

                return deep[0];
            } else return 0;
        } else return -1;
    }

    private class Element<T> {
        private GraphNode<T> node;
        private int deep;

        public Element(GraphNode<T> node, int deep) {
            this.node = node;
            this.deep = deep;
        }

        public GraphNode<T> getNode() {
            return node;
        }

        public void setNode(GraphNode<T> node) {
            this.node = node;
        }

        public int getDeep() {
            return deep;
        }

        public void setDeep(int deep) {
            this.deep = deep;
        }
    }

    public boolean isContain(T value) {
        return this.graph.isContain(this.getNode(value));
    }

    @Override
    public String toString() {
        return this.graph.toString();
    }

}
