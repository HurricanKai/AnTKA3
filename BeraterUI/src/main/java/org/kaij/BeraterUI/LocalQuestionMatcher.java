package org.kaij.BeraterUI;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LocalQuestionMatcher implements IQuestionMatcher {

    private HashMap<String, String> synonyms;
    private HashMap<String, String> matchedWords;

    public LocalQuestionMatcher() {
        var gson = new Gson();
        matchedWords = gson.fromJson(new JsonReader(new InputStreamReader(getClass().getResourceAsStream("MatchedWords.json"))), HashMap.class);
        synonyms = gson.fromJson(new JsonReader(new InputStreamReader(getClass().getResourceAsStream("Synonyms.json"))), HashMap.class);
    }

    @Override
    public String match(String question) {
        var strWords = question.split("[ .]"); // split on ' ' or '.'

        for (var word : strWords) {
            if (word == null || word.trim().isEmpty())
                continue;

            while (synonyms.containsKey(word)) {
                word = synonyms.get(word);
            }

            if (matchedWords.containsKey(word))
                return matchedWords.get(word);
        }

        return null;
    }
}
