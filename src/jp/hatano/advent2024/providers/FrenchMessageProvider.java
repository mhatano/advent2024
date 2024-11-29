package jp.hatano.advent2024.providers;

import jp.hatano.advent2024.main.AdventMessageProvider;

public class FrenchMessageProvider implements AdventMessageProvider {

    @Override
    public String getHello() {
        return "Bonjour le monde.";
    }
}
