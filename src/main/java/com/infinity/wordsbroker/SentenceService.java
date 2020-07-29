package com.infinity.wordsbroker;

import com.infinity.wordsbroker.model.Sentence;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service public class SentenceService {

    private Sentence sentence;

    @PostConstruct public void init() {

        sentence = new Sentence();
    }

    /**
     * add words to sentence
     * @param words
     */
    public void addWord(String words) {
         this.sentence.addAWord(words);
    }


    public String getSentence(){
        return this.sentence.getSentence();
    }
}
