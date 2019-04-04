package hello;

import org.springframework.stereotype.Service;

@Service
public class FreGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Bonjour Monde!";
    }
}
