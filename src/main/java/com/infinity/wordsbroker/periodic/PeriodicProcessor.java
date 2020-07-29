package com.infinity.wordsbroker.periodic;

import com.infinity.wordsbroker.SentenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @EnableScheduling @Slf4j public class PeriodicProcessor {

    private int lastSendWordsSize = 0;

    private final        SentenceService               sentenceService;
    private final        KafkaTemplate<String, String> kafkaTemplate;
    private static final String                        TOPIC = "sentence";

    public PeriodicProcessor(SentenceService sentenceService, KafkaTemplate<String, String> kafkaTemplate) {
        this.sentenceService = sentenceService;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * add word to sentence and send to clients
     */
    @Scheduled(cron = "0 * * ? * *") public void addAndSend() {

            if (!sentenceService.getSentence().isEmpty()) {
                log.info("i send sentence  {} : to {}", TOPIC, sentenceService.getSentence());
                kafkaTemplate.send(TOPIC, sentenceService.getSentence());
            }


    }

    /**
     * listen to client and process sentence
     * @param message String
     */
    @KafkaListener(topics = "words") public void listen(String message) {

        log.info("Received Messasge: {}", message);
        sentenceService.addWord(message);

    }

}
