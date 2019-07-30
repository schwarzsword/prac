package schwarzsword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Main {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(new Class[]{Main.class}, args);

    }
}
