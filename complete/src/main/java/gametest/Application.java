package gametest;

import gametest.models.Gamer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableCaching
//@ComponentScan({"gametest.repo","gametest.repo.TestRepo"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static Map<String, Gamer> gamers = new HashMap<>();

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx ) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }


        };
    }


}
