package xokyopo.stackqueuedeque;

import xokyopo.stackqueuedeque.myrealization.Deque;
import xokyopo.stackqueuedeque.myrealization.PriorityQueue;
import xokyopo.stackqueuedeque.myrealization.Queue;
import xokyopo.stackqueuedeque.myrealization.Stack;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int numberOfElements = 22;
        Main main = new Main();

        System.out.println("Stack");
        main.stack(numberOfElements);

        System.out.println("\nQueue");
        main.queue(numberOfElements);

        System.out.println("\nDeque");
        main.deque(numberOfElements);

        System.out.println("\nPriorityQueue");
        main.priority(numberOfElements);
        System.out.println("");

        Reverser reverser = new Reverser();
        reverser.start();

        ParenthesisChecker parenthesisChecker = new ParenthesisChecker();
        parenthesisChecker.start();
    }

    //4 ошибки
    public void stack(int numberOfElements) {
        Stack<Integer> integerStack = new Stack<Integer>();

        for (int count = 0; count < numberOfElements; count ++) {
            integerStack.put(count);
            System.out.print(count + "\t");
        }

        System.out.println("");

        for (int count = 0; count < numberOfElements; count ++) {
            System.out.print(integerStack.get() + "\t");
        }
    }

    //4 ошибки
    public void queue(int numberOfElements) {
        Queue<Integer> integerQueue = new Queue<Integer>();

        for (int count = 0; count < numberOfElements; count ++) {
            integerQueue.put(count);
            System.out.print(count + "\t");
        }

        System.out.println("");

        for (int count = 0; count < numberOfElements; count ++) {
            System.out.print(integerQueue.get() + "\t");
        }
    }

    //1 ошибки
    public void deque(int numberOfElements) {
        Deque<Integer> integerDeque = new Deque<Integer>();

        System.out.println("insert Firsrt");
        for (int count = 0; count < numberOfElements; count ++) {
            integerDeque.putFirst(count);
            System.out.print(count + "\t");
        }

        System.out.println("\nread First");
        for (int count = 0; count < numberOfElements; count ++) {
            System.out.print(integerDeque.getFirst() + "\t");
        }

        System.out.println("\ninsert Last");
        for (int count = 0; count < numberOfElements; count ++) {
            integerDeque.putLast(count);
            System.out.print(count + "\t");
        }

        System.out.println("\nread last");
        for (int count = 0; count < numberOfElements; count ++) {
            System.out.print(integerDeque.getLast() + "\t");
        }
    }

    //2 ошибки, но какие....
    public void priority(int numberOfElements) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        Random random = new Random();

        for (int count = 0; count < numberOfElements; count ++) {
            int randomNumber = random.nextInt(numberOfElements);
            priorityQueue.put(randomNumber);
            System.out.print(randomNumber + "\t");
        }

        System.out.println("");

        for (int count = 0; count < numberOfElements; count ++) {
            System.out.print(priorityQueue.get() + "\t");
        }
    }
}
