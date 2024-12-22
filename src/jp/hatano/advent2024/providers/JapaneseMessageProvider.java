package jp.hatano.advent2024.providers;

import jp.hatano.advent2024.main.AdventMessageProvider;

public class JapaneseMessageProvider implements AdventMessageProvider {
    @Override
    public String getHello() {
        return "こんにちは、世界";
    }
    @Override
    public String getLanguageName() {
        return "日本語";
    }
    @Override
    public String getEnglishLanguageName() {
        return "Japanese";
    }
}
