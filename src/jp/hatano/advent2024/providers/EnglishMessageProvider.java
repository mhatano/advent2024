package jp.hatano.advent2024.providers;

import jp.hatano.advent2024.main.AdventMessageProvider;

public class EnglishMessageProvider implements AdventMessageProvider {
    @Override
    public String getHello() {
        return "Hello, World!";
    }
}
