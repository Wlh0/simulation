package org.cgn.simulation.consumer;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {
    @KafkaListener(topics = "test")
    public void listen(String message) {
        System.out.println(message);
    }
}
