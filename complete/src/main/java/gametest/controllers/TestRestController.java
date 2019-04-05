package gametest.controllers;

import gametest.models.Test;
import gametest.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gametest.services.GreetingService;

import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    GreetingService greetingService;

    @Autowired
    TestRepo testRepo;

    @RequestMapping("/hello")
    public String home() {
        return greetingService.greet();
    }

    @RequestMapping("/testrepo")
        public String testrepo() {

        Test test = new Test("steve");

        testRepo.save(test);
        Long idOftest = test.getId();

       test = null;
        test = testRepo.findById(new Long(5)).get();

        return "DID THIS WORK::::: + "+ test.toString();
    }

    @RequestMapping("/all")
    public String all(){
        List<Test> all =  testRepo.findAll();
        return all.get(2).toString();
    }

}
