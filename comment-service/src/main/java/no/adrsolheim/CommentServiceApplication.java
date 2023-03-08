package no.adrsolheim;

import no.adrsolheim.event.BlogEvent;
import no.adrsolheim.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class CommentServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CommentServiceApplication.class);
    }

    @KafkaListener(topics = "blogNotification")
    public void handleNotification(BlogEvent blogEvent) {
        logger.info("Received notification from blog service: {} {}", blogEvent.getId(), blogEvent.getOperation());
    }
}
