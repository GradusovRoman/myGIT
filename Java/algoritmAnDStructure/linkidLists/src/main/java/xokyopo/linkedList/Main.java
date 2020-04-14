package xokyopo.linkedList;


import xokyopo.linkedList.lists.ListIterator;
import xokyopo.linkedList.lists.MyLinkedList;
import xokyopo.linkedList.queue.Queue;
import xokyopo.linkedList.queue.Stack;

public class Main {
    public static void main(String[] args) {
        int numberOfElements = 10;
        Main main = new Main();

        main.testingStack(numberOfElements);
        main.testingQueue(numberOfElements);
        main.iterator(numberOfElements);
    }

    public void testingStack(int numberOfElement) {
        Stack<String> stringStack = new Stack<>();

        System.out.print("Stack\n in = \t");
        for (int i = 0; i < numberOfElement; i++) {
            stringStack.put("" + i);
            System.out.print("" + i);
        }
        System.out.print("\n out = \t");
        for (int i = 0; !stringStack.isEmpty(); i++) {
            System.out.print(stringStack.get());
        }
        System.out.println("\n");
    }

    public void testingQueue(int numberOfElement) {
        Queue<String> stringQueue = new Queue<>();

        System.out.print("Queue\n in = \t");
        for (int i = 0; i < numberOfElement; i++) {
            stringQueue.put("" + i);
            System.out.print("" + i);
        }
        System.out.print("\n out = \t");
        for (int i = 0; !stringQueue.isEmpty(); i++) {
            System.out.print(stringQueue.get());
        }
        System.out.println("\n");
    }

    public void iterator(int numberOfElement) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        System.out.print("Iterator\nin = \t");
        for (int i = 0; i < numberOfElement; i++) {
            myLinkedList.insert("" + i);
        }
        myLinkedList.forEach(System.out::print);
        ListIterator<String> listIterator = myLinkedList.getIterator();
        System.out.println("");

        System.out.println("delete 5 ");
        this.deleteElementInIterator(listIterator, "5");

        System.out.println("delete 0 ");
        this.deleteElementInIterator(listIterator, "0");

        System.out.println("delete 9 ");
        this.deleteElementInIterator(listIterator, "9");

        System.out.print("out = \t");
        myLinkedList.forEach(System.out::print);
        System.out.println("\n");


        System.out.println("insert < и > around 4");
        this.insertAroundElementInIterator(listIterator, "4","<", ">");

        System.out.println("insert  \" и > around 1");
        this.insertAroundElementInIterator(listIterator, "1","\"", ">");
//
        System.out.println("insert  < и \" around 8");
        this.insertAroundElementInIterator(listIterator, "8","<", "\"");

        System.out.print("out = \t");
        myLinkedList.forEach(System.out::print);
        System.out.println("");
    }

    public <T> void deleteElementInIterator(ListIterator<T> listIterator, T element) {
        listIterator.reset();
        while (!listIterator.atEnd()) {
            if (listIterator.getCurrent().equals(element)) listIterator.deleteCurrent();
            else listIterator.nextLink();
        }
    }

    public <T> void insertAroundElementInIterator(ListIterator<T> listIterator, T element, T left, T right) {
        listIterator.reset();
        while (!listIterator.atEnd()) {
            if (listIterator.getCurrent().equals(element)) {
                listIterator.insertBefore(left);
                listIterator.insertAfter(right);
            }
            listIterator.nextLink();
        }
    }
}
