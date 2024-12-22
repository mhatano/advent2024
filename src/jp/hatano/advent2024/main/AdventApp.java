package jp.hatano.advent2024.main;

import java.io.*;

public class AdventApp {
    public static void main(String[] args) {
        AdventMessageProviderFactory factory = new AdventMessageProviderFactory("jp.hatano.advent2024.providers");
        boolean showEnglishName = false;
        BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
        while (true ) {
            int index = 1;
            for ( AdventMessageProvider provider : factory ) {
                AdventApp application = new AdventApp(provider);
                System.out.printf("%d) %s\n",index,(showEnglishName?provider.getEnglishLanguageName():provider.getLanguageName()));
                ++index;
            }
            System.out.println("s) Switch Name to " + (showEnglishName?"Local":"English"));
            System.out.println("q) Quit");
            System.out.print("Input >> ");
            try {
                String input = stdinReader.readLine();
                if ( input.equalsIgnoreCase("s") ) {
                    showEnglishName = !showEnglishName;
                } else if ( input.equalsIgnoreCase("q") ) {
                    break;
                } else {
                    try {
                        int inputNum = Integer.parseInt(input);
                        if ( inputNum < 1 || inputNum > factory.size() ) {
                            System.err.println("Invalid number of language: " + inputNum);
                        } else {
                         (new AdventApp(factory.get(inputNum - 1))).sayHello(System.out);
                        }
                    } catch ( NumberFormatException e ) {
                        System.err.println("Invalid Input: \"" + input + "\"");
                    }
                }
            } catch ( IOException e ) {
                System.err.println("Input error");
            }
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
