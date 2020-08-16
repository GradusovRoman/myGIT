package org.xokyopo.eight_queens;

import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String p = "(?=\\s)";
        String test = " a!g s d      a ";
        System.out.println("[" + test + "]");
        System.out.println("[" + Arrays.stream(test.split(p)).reduce((s, s2) -> s + s2).get()+ "]");

        Arrays.stream(test.split(p)).forEach(s -> System.out.print("{" + s + "}"));
        Objects.requireNonNull();
    }
}
