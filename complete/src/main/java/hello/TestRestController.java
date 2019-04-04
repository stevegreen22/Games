package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @Autowired
    GreetingService greetingService;

    @RequestMapping("/hello")
    public String home() {
        return greetingService.greet();
    }
}
