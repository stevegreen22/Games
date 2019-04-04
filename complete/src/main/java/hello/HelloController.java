package hello;

import com.omertron.bgg.BggApi;
import com.omertron.bgg.BggException;
import com.omertron.bgg.model.UserInfo;
import models.Game;
import models.Gamer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static hello.Application.gamers;

@Controller
public class HelloController {


    @RequestMapping("/")
    public String homepage() {
        return "welcome";
    }

    @RequestMapping("/allGames")
    public String homepageds(Model model) {

        List<Game> games= new ArrayList<Game>();
        games.add(new Game("dsfsdf"));

        model.addAttribute("games", games);
        System.out.println("FDLHGFLJDSFLSDGHLJF");
        return "games";
    }
    @RequestMapping("/allCategories")
    public String homepagesd() {
        return "welcome";
    }

    @RequestMapping("/allRegisteredGamers")
    public String index(Model model) {

        //Map<String, Gamer> gamers = new HashMap<>();
        gamers.put("Steve", new Gamer("Steve"));
        gamers.put("Jerkwood", new Gamer("Jerkwood"));
        gamers.put("Mark", new Gamer("Mark"));


//        List<Game> games = new ArrayList<>();
//        try {
//            Scanner scan = new Scanner(new File("C:\\Users\\Steve\\Downloads\\gs-spring-boot-master\\gs-spring-boot-master\\complete\\src\\main\\gameslist"));
//            while (scan.hasNextLine()) {
//                games.add(new Game(scan.nextLine()));
//            }
//            scan.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        GamerRepo pr = new GamerRepo();
//        Gamer steve = pr.getPersonByName(gamers, "Jerkwood");



        //gamers.forEach((k,v) -> gamerRepo.save(v));

        model.addAttribute("gamers", gamers);

        return "gamers";
    }



    @RequestMapping("/test")
    public String main(Model model) {
        Gamer steve = new Gamer("Steve");

        //model.addAttribute("message", "a mesage");

        BggApi bggApi = new BggApi();
        UserInfo user = null;
        try {
           user =  bggApi.getUserInfo("fatcatgaming");
        } catch (BggException e) {
            e.printStackTrace();
        }

        steve.setBggUserinfo(user);

        List<String> tasks = new ArrayList<>();
        for (Game game: steve.getExcited()
             ) {
            tasks.add(game.getName());
        }
        model.addAttribute("tasks", steve.getBggUserinfo());

        return "gamers"; //view
    }


}
