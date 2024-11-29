package jp.hatano.advent2024.main;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class AdventApp {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("list.txt"));
            while ( reader.ready() ) {
                String providerName = reader.readLine();
                Class<?> aClass = Class.forName(providerName);
                AdventApp application = new AdventApp((AdventMessageProvider)aClass.getConstructor(new Class[]{}).newInstance(new Object[] {}));
                application.sayHello(System.out);
            }
        } catch ( IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private final AdventMessageProvider provider;
    public AdventApp(AdventMessageProvider provider) {
        this.provider = provider;
    }

    public void sayHello(PrintStream out) {
        out.println(provider.getHello());
    }
}
