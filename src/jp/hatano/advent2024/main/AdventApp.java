package jp.hatano.advent2024.main;

import java.io.PrintStream;

public class AdventApp {
    public static void main(String[] args) {
        AdventApp application = new AdventApp();
        application.sayHello(System.out);
    }

    public void sayHello(PrintStream out) {
        out.println("Hello, World!");
    }
}
