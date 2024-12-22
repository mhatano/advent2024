package jp.hatano.advent2024.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class AdventAppTest {

    @Test
    void testSayHello() {
        AdventMessageProvider provider = new AdventMessageProvider() {
            @Override
            public String getHello() {
                return "Hello, World!";
            }

            @Override
            public String getLanguageName() {
                return "English";
            }

            @Override
            public String getEnglishLanguageName() {
                return "English";
            }
        };

        AdventApp app = new AdventApp(provider);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);
        app.sayHello(printStream);
        assertEquals("Hello, World!", outContent.toString().trim());
    }
}