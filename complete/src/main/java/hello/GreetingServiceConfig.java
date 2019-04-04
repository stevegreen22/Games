package hello;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GreetingServiceConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "language.name", havingValue = "english", matchIfMissing = true)
    public GreetingService engGreetingService() {
        return new EngGreetingService();
    }

    @Bean
    @ConditionalOnProperty(name = "language.name", havingValue = "french")
    public GreetingService freGreetingService() {
        return new FreGreetingService();
    }
}
