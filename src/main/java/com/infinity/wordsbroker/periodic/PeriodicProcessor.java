package com.infinity.wordsbroker.periodic;

import com.infinity.wordsbroker.SentenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @EnableScheduling @Slf4j public class PeriodicProcessor {

    private List<String> lastSentence = new ArrayList<>();

    private final        SentenceService               sentenceService;
    private final        KafkaTemplate<String, String> kafkaTemplate;
    private static final String                        TOPIC = "words";

    public PeriodicProcessor(SentenceService sentenceService, KafkaTemplate<String, String> kafkaTemplate) {
        this.sentenceService = sentenceService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "0 * * * * *") public void addAndSend() {

        if (lastSentence.size() != sentenceService.getWords().size()) {
            log.info("i send sentence  {} : to {}", TOPIC, sentenceService.getSentence());

            kafkaTemplate.send(TOPIC, sentenceService.getSentence());
        }

    }

    @KafkaListener(topics = "words") public void listen(String message) {
        log.info("Received Messasge: {}", message);

        lastSentence = sentenceService.addWord(message);

        log.info("last size {}  recived {}", lastSentence.size(), sentenceService.getWords().size());

    }

}
