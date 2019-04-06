package gametest.controllers;

import gametest.models.Gamer;
import gametest.repo.GamerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static gametest.Application.gamers;

@Controller
public class GamerController {

    @Autowired
    GamerRepo gamerRepo;

    @RequestMapping("/viewGamers")
    public String viewGamers(Model model) {

        List<Gamer> gamers = gamerRepo.findAll();

        //populate the gamers games etc etc?  or do that in the 'viewlist'?


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
//        GamerRepo pr = new GamerRepo();
//        Gamer steve = pr.getPersonByName(gamers, "Jerkwood");

        //gamers.forEach((k,v) -> gamerRepo.save(v));

        model.addAttribute("gamers", gamers);
        return "gamers";
    }

    @RequestMapping("/{gamerid}/games")
    public String userGames(@PathVariable final Long gamerid,
                            Model model){



        Gamer gamer = gamerRepo.findById(gamerid).get();
        System.out.println("name:" + gamer.getName());

//        List<Gamer> gamerlist =  gamers;
////        Gamer gamerRes = null;
//        for (Gamer gamer: gamers
//             ) {
//            if (gamer.getName().equals(gamer)){
//                gamerRes = gamer;
//            }
//        }

        model.addAttribute("games", gamers.get(gamer).getExcited());
        model.addAttribute("gamer", gamers.get(gamer));

        return "games";
    }
}
