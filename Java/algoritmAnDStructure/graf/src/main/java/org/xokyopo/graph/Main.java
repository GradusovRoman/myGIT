package org.xokyopo.graph;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int maxElements = 15;
        final int maxElementValue = 10;
        Main main = new Main();

        main.graph(maxElements, maxElementValue);
    }

    public void graph(int maxElements, int maxElementValue) {
        System.out.println("Домашнее задание по графам");

        MyGraph<Integer> graph = new MyGraph<>();
        Random random = new Random();

        for (int i = 0; i < maxElements; i++) {
            graph.addLinkBetween(random.nextInt(maxElementValue), random.nextInt(maxElementValue));
        }


        System.out.println(String.format("Граф \n%s \nГраф содержин %s элементов и %s связей", graph, graph.nodes(), graph.links()));
        int deleting = random.nextInt(maxElementValue);
        System.out.println("\nУдаляем вершину со значением: " + deleting);
        graph.remove(deleting);
        System.out.println(String.format("Граф \n%s \nГраф содержин %s элементов и %s связей", graph, graph.nodes(), graph.links()));

        int A = random.nextInt(maxElementValue);
        int B = random.nextInt(maxElementValue);
        int lengthBetweenBAndA = graph.findClosesPath(A, B);

        System.out.println("\nОпределяем кратчайшее растояние между двумя значениями");
        if (lengthBetweenBAndA > 0) {
            System.out.println(String.format("Кратчайшее растояние между A = %s и B = %s равно = %s", A, B, lengthBetweenBAndA));
        } else if (lengthBetweenBAndA == 0) {
            System.out.println(String.format("А = %s B = %s \t A == B", A, B));
        } else {
            System.out.println(String.format("A = %s %s", A, graph.isContain(A) ? "содержится в графе" : " не содержится в графе"));
            System.out.println(String.format("B = %s %s", B, graph.isContain(B) ? "содержится в графе" : " не содержится в графе"));
        }
    }
}
