package no.adrsolheim.config;

import org.aspectj.bridge.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class AppConfig {
   @Bean
   public MessageDigest messageDigest() throws NoSuchAlgorithmException {
      return MessageDigest.getInstance("SHA-256");
   }
}
