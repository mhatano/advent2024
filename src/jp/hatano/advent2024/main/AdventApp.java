package jp.hatano.advent2024.main;

import java.io.*;

public class AdventApp {
    public static void main(String[] args) {
        AdventMessageProviderFactory factory = new AdventMessageProviderFactory("list.txt");
        AdventApp application = new AdventApp(factory.get(0));
        application.sayHello(System.out);
    }

    private final AdventMessageProvider provider;
    public AdventApp(AdventMessageProvider provider) {
        this.provider = provider;
    }

    public void sayHello(PrintStream out) {
        out.println(provider.getHello());
    }
}
