package org.xokyopo.graph.template;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.*;

public class Graph<T> implements GraphInterface<GraphNode<T>> {
    private final List<GraphNode<T>> elements;
    private int nodes;
    private int links;

    public Graph() {
        this.elements = new ArrayList<>();
    }

    private GraphNode<T> addElement(GraphNode<T> first) {
        this.elements.add(first);
        this.nodes++;
        return first;
    }

    private void deleteElement(GraphNode<T> element) {
        this.elements.remove(element);
        this.nodes--;
    }

    private GraphNode<T> findGraph(GraphNode<T> element) {
        int index = this.elements.indexOf(element);
        if (index <0) return null;
        else return this.elements.get(index);
    }

    private GraphNode<T> getOrDefault(GraphNode<T> element) {
        GraphNode<T> resultElement = this.findGraph(element);
        if (resultElement == null) {
            resultElement = this.addElement(element);
        }
        return resultElement;
    }

   private void addLink(GraphNode<T> first, GraphNode<T> second) {
        if (!first.equals(second)) {
            GraphNode<T> firstNode = this.getOrDefault(first);
            GraphNode<T> secondNode = this.getOrDefault(second);
            firstNode.addLink(secondNode);
            secondNode.addLink(firstNode);
            this.links++;
        }
    }

    private void removeLink(GraphNode<T> first, GraphNode<T> second) {
        if (this.isContain(first) && this.isContain(second)) {
            GraphNode<T> firstNode = this.find(first);
            GraphNode<T> secondNode = this.find(second);
            if (firstNode.getLinks().contains(secondNode)) this.links--;
            firstNode.removeLink(secondNode);
            secondNode.removeLink(firstNode);
        }
    }

    private void removeElement(GraphNode<T> element) {
        if (this.elements.contains(element)) {
            this.removeLinksTo(this.find(element));
            this.deleteElement(element);
        }
    }

    private void removeLinksTo(GraphNode<T> element) {
        element.getLinks().forEach(node->{
            node.removeLink(element);
            this.links--;
        });
    }

    private void removeAll() {
        this.links = 0;
        this.nodes = 0;
        this.elements.clear();
    }

    @Override
    public void add(GraphNode<T> element) {
        this.addElement(element);
    }

    @Override
    public void addLinkBetween(GraphNode<T> left, GraphNode<T> right) {
        this.addLink(left, right);
    }

    @Override
    public void remove(GraphNode<T> element) {
        this.removeElement(element);
    }

    @Override
    public void removeLinkBetween(GraphNode<T> left, GraphNode<T> right) {
        this.removeLink(left, right);
    }

    @Override
    public GraphNode<T> find(GraphNode<T> element) {
        return this.findGraph(element);
    }

    @Override
    public int nodes() {
        return this.nodes;
    }

    @Override
    public int links() {
        return this.links;
    }

    @Override
    public void clear() {
        this.removeAll();
    }

    public boolean isContain(GraphNode<T> element) {
        return this.elements.contains(element);
    }

    private String graphToString() {
        StringBuilder resultSB = new StringBuilder();
        Set<GraphNode<T>> set = new HashSet<>();
        resultSB.append("{");
        this.forEachByDeep(node->{
            if (!set.contains(node)) {
                set.add(node);
                if (node.getLinks().size() > 0 ) {
                    node.getLinks().forEach(link -> {
                        if (!set.contains(link)) {
                            resultSB.append("[").append(node).append("-").append(link).append("]");
                        }
                    });
                } else {
                    resultSB.append("[").append(node).append("]");
                }
            }
        }, this.elements);
        resultSB.append("}");
        return resultSB.toString();
    }

    @Override
    public String toString() {
        return this.graphToString();
    }

    public void forEachByDeep(Action<GraphNode<T>> action, List<GraphNode<T>> startNodeList) {
        Stack<GraphNode<T>> stack = new Stack<>();
        Set<GraphNode<T>> visited = new HashSet<>();
        stack.addAll(startNodeList);
        GraphNode<T> current;

        while (!stack.isEmpty()) {
            current = stack.pop();
            visited.add(current);
            action.accept(current);
            current.getLinks().forEach(link->{
                if (!visited.contains(link)) {
                    stack.add(link);
                }
            });
        }
    }

    public void forEachByWight(ReturnIf<GraphNode<T>> returnIf, List<GraphNode<T>> startNodeList) {
        LinkedList<GraphNode<T>> queue = new LinkedList<>(startNodeList);
        Set<GraphNode<T>> visited = new HashSet<>();
        GraphNode<T> current;

        while (!queue.isEmpty()) {
            current = queue.pollFirst(); //
            visited.add(current);
            if (returnIf.check(current)) return;
            current.getLinks().forEach(link->{
                if (!visited.contains(link)) {
                    queue.addLast(link); //
                }
            });
        }
    }
}
