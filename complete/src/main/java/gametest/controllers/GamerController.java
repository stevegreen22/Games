package gametest.controllers;

import gametest.models.Gamer;
import gametest.repo.GamerRepo;
import gametest.repo.JpaGamerRepo;
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
    private GamerRepo gamerRepo;

    @RequestMapping("/viewGamers")
    public String viewGamers(Model model) {

        List<Gamer> gamers = gamerRepo.getAllGamers();

        model.addAttribute("gamers", gamers);
        return "gamers";
    }

    @RequestMapping("/{gamerid}/games")
    public String userGames(@PathVariable final Long gamerid,
                            Model model){

        Gamer gamer = gamerRepo.getGamerById(gamerid);
        System.out.println("name:" + gamer.getName());

        gamerRepo.populateGamerGames(gamer);

//        List<Gamer> gamerlist =  gamers;
////        Gamer gamerRes = null;
//        for (Gamer gamer: gamers
//             ) {
//            if (gamer.getName().equals(gamer)){
//                gamerRes = gamer;
//            }
//        }

        model.addAttribute("games", gamer.getAllGames());
        model.addAttribute("gamer", gamer);

        return "games";
    }
}
