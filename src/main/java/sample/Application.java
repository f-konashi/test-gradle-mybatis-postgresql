package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

@SpringBootApplication
public class Application extends WebMvcAutoConfiguration {
  // エントリーポイント
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}