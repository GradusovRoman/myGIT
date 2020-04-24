package org.xokyopo.graph.template;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    private T value;
    private List<GraphNode<T>> links;

    public GraphNode(T value) {
        this.value = value;
        this.links = new ArrayList<>();
    }

    public List<GraphNode<T>> getLinks() {
        return links;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object element) {
        if (element instanceof GraphNode) {
            return this.value.equals(((GraphNode<T>)element).getValue());
        } else {
            return false;
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void addLink(GraphNode<T> element) {
        if(!this.links.contains(element)) {
            this.links.add(element);
        }
    }

    public void removeLink(GraphNode<T> element) {
        this.links.remove(element);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}

