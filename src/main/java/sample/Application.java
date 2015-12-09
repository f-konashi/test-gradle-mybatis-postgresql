package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  // エントリーポイント
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}