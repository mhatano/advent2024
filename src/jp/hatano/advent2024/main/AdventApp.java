package jp.hatano.advent2024.main;

import java.io.PrintStream;
import jp.hatano.advent2024.providers.JapaneseMessageProvider;
import jp.hatano.advent2024.providers.EnglishMessageProvider;

public class AdventApp {
    public static void main(String[] args) {
        AdventApp application = new AdventApp(new EnglishMessageProvider());
        application.sayHello(System.out);
        application = new AdventApp(new JapaneseMessageProvider());
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
