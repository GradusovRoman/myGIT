package xokyopo.stackqueuedeque;

import xokyopo.stackqueuedeque.myrealization.Stack;

import java.util.Arrays;
import java.util.Scanner;

public class ParenthesisChecker {
    private char[][] valuePairs = {
            {'(', '{', '(', '"'},
            {')', '}', ')', '"'}
    };

    public void start() {
        this.printWelcomeMessage();
        boolean repeat = true;
        while (repeat) {
            this.printResultMessage(
                    this.checking(
                            this.getInputText()
                    )
            );

            System.out.println("");
            repeat = this.askingAboutRepeat();
            System.out.println("");
        }
    }

    private String getInputText() {
        System.out.print("Введите выражение которое вы хотите проверить:");
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
        System.out.println("\tПривет!!!\nЭта программа проверяет коректность использования парных знаков\n" +
                "таких как" + Arrays.toString(this.valuePairs[0]) + " и " + Arrays.toString(this.valuePairs[1]));
    }

    private void printResultMessage(boolean validExpress) {
        System.out.println("введеное вами выражение: " +((validExpress)? "верно" : "неверно"));
    }


    private boolean checking(String text) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            if (this.isPairCharacter(text.charAt(i))) {
                if (stack.isEmpty()) {
                    stack.put(text.charAt(i));
                } else {
                    char stackChar = stack.get();
                    if (!isPair(stackChar, text.charAt(i))) {
                        stack.put(stackChar);
                        stack.put(text.charAt(i));
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isPair (Character stackChar, Character textChar) {
        int firstPairIndex = this.getOpenPairIndex(stackChar);
        if (firstPairIndex >= 0) return textChar.equals(this.valuePairs[1][firstPairIndex]);
        return false;
    }

    private int getOpenPairIndex(Character character) {
        for (int i = 0; i < this.valuePairs[0].length ; i++) {
            if (character.equals(this.valuePairs[0][i])) return i;
        }
        return -1;
    }


    public boolean isPairCharacter(Character character) {
        boolean result = false;
        for (int row = 0; row < this.valuePairs.length; row++) {
            for (int i = 0; i < this.valuePairs[1].length; i++) {
                if (character.equals(this.valuePairs[row][i])) result = true;
            }
        }
        return result;
    }
}
