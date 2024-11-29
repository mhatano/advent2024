package jp.hatano.advent2024.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

public class AdventApp {
    private static final String[][] listProviders = {
        { "Japanese", "jp.hatano.advent2024.providers.JapaneseMessageProvider" },
        { "English",  "jp.hatano.advent2024.providers.EnglishMessageProvider" },
    };
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for ( int i = 0 ; i < listProviders.length ; i++ ) {
                System.out.printf("%d: %s\n",i,listProviders[i][0]);
            }
            System.out.print("Your choice: ");
            String input = reader.readLine();
            int inputNumber = Integer.parseInt(input);
            if ( inputNumber >= 0 && inputNumber < listProviders.length ) {
                Class<?> aClass = Class.forName(listProviders[inputNumber][1]);
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
