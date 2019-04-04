package hello;

import org.springframework.stereotype.Service;

@Service
public class EngGreetingService implements GreetingService {

    @Override
    public String greet() {
        return "weassuop";
    }
}
