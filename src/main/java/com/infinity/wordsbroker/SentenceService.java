package com.infinity.wordsbroker;

import com.infinity.wordsbroker.model.Sentence;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service public class SentenceService {

    private Sentence sentence;

    @PostConstruct public void init() {

        sentence = new Sentence();
    }

    public List<String> addWord(String words) {
        return this.sentence.addAWord(words);
    }

    public List<String> getWords(){
        return this.sentence.getWords();
    }

    public String getSentence(){
        return this.sentence.getSentence();
    }
}
