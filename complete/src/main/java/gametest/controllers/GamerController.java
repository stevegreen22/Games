package gametest.controllers;

import gametest.models.Gamer;
import gametest.repo.GamerRepo;
import gametest.services.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GamerController {

    @Autowired
    private GamerService gamerService;

    @RequestMapping("/viewGamers")
    public String viewGamers(Model model) {

        List<Gamer> gamers = gamerService.getAllGamers();

        model.addAttribute("metaTitle", "All Registered Gamers");
        model.addAttribute("gamers", gamers);
        return "gamers";
    }

    //test - this is for spring security if/when implemented.
    @RequestMapping(value="gamer/showGames", method = RequestMethod.POST)
    public String userGamesTest(@RequestParam final Long id, Model model){

        Gamer gamer = gamerService.getGamerById(id);
        System.out.println("name:" + gamer.getName());

        gamerService.getAllGamerGamesWithCategories(gamer);


        model.addAttribute("metaTitle", gamer.getName() +"'s Games");
        model.addAttribute("gcm", gamer.getAllGamesWithCategories()); //can remove.
        model.addAttribute("games", gamer.getAllGames());
        model.addAttribute("gamer", gamer);

        return "games";
    }


    @RequestMapping("/{gamerid}/games")
    public String userGames(@PathVariable final Long gamerid, Model model){

        Gamer gamer = gamerService.getGamerById(gamerid);
        System.out.println("name:" + gamer.getName());

        gamerService.getAllGamerGamesWithCategories(gamer);


        //todo:  getAlGames is empty...
        System.out.println("ALL SIZE"+gamer.getAllGames().size());

        model.addAttribute("metaTitle", gamer.getName() +"'s Games");
        model.addAttribute("gcm", gamer.getAllGamesWithCategories()); //can remove.
        model.addAttribute("gamer", gamer);

        return "games";
    }

    //todo: don't think this is the best approach.
//    <li><a th:href="@{/gamer/{gamerid}/games/excited (gamerid=${gamer.getId()}) }">Excited</a></li>
//    <li><a th:href="@{/{gamerid}/games/wants (gamerid=${gamer.getId()}) }">Wants</a></li>
//    <li><a th:href="@{/{gamerid}/games/likes (gamerid=${gamer.getId()}) }">Likes</a></li>
//    <li><a th:href="@{/{gamerid}/games/lolnope (gamerid=${gamer.getId()}) }">LolNope</a></li>


    @RequestMapping("/gamer/{gamerid}/games/excited")
    public String getExcited(@PathVariable final Long gamerid, Model model){

        Gamer gamer = gamerService.getGamerById(gamerid);
        gamerService.getAllGamerGamesWithCategories(gamer); //not yet implemented

        model.addAttribute("metaTitle", gamer.getName() +"'s Games");
        model.addAttribute("games", gamer.getExcited());
        model.addAttribute("gamer", gamer);
        return "games";
    }


    @RequestMapping("/gamer/games/excited")
    public String userGamesExcited(@ModelAttribute final Gamer gamer, Model model){

        System.out.println("ALL SIZE with model attr"+gamer.getAllGames().size());

        System.out.println(gamer.getName() + gamer.getId());

        model.addAttribute("metaTitle", gamer.getName() +"'s Games");
        model.addAttribute("games", gamer.getExcited());
        model.addAttribute("gamer", gamer);

        return "games";
    }


}
