package xokyopo.stackqueuedeque;

import xokyopo.stackqueuedeque.myrealization.Stack;

import java.util.Scanner;

public class Reverser {

    public void start() {
        this.printWelcomeMessage();
        boolean repeat = true;
        while (repeat) {
            this.printStack(
                    this.createStack(
                            this.getInputText()
                    )
            );
            System.out.println("");
            repeat = this.askingAboutRepeat();
            System.out.println("");
        }
    }

    private String getInputText() {
        System.out.print("Введите текст, который хотите отразить:");
        return this.readInput();
    }

    private boolean askingAboutRepeat() {
        System.out.print("если хотите проболжить введите Да:");
        return this.readInput().contains("Да");
    }

    private String readInput() {
        return new Scanner(System.in).nextLine();
    }

    private void printWelcomeMessage() {
        System.out.println("\tПривет!!!\nЭта программа переворачиват вводимый вами текст.");
    }

    private Stack<Character> createStack(String text) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            stack.put(text.charAt(i));
        }
        return stack;
    }

    private void printStack(Stack<Character> stack) {
        System.out.print("зеркально:\t");
        for (int i = 0; !stack.isEmpty(); i++) {
            System.out.print(stack.get());
        }
        System.out.println("");
    }

}
