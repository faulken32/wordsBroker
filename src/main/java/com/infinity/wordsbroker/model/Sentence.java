package com.infinity.wordsbroker.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data public class Sentence {

    List<String> words = new ArrayList<>();

    public List<String> addAWord(String word) {

        this.words.add(word);

        return words;

    }

    public String getSentence() {

        return String.join(" ", words);

    }

}
