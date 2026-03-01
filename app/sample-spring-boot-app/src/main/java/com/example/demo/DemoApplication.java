package com.example.demo;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// 1. The Database Entity
@Entity
class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;
    public Message() {}
    public Message(String content) { this.content = content; }
}

// 2. The Database Repository
interface MessageRepository extends JpaRepository<Message, Long> {}

// 3. The REST Controller (Handles the API Call and publishes to Kafka)
@RestController
@RequestMapping("/api")
class MessageController {
    private final MessageRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(MessageRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String text) {
        // Save to MySQL
        repository.save(new Message(text));
        
        // Publish to Kafka
        kafkaTemplate.send("demo_topic", text);
        
        return "Message saved to MySQL and published to Kafka: " + text;
    }
}
