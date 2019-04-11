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
        model.addAttribute("games", gamer.getAllGames());

        return "games";
    }


    @RequestMapping("/gamer/{gamerid}/games/excited")
    public String getExcited(@PathVariable final Long gamerid, Model model){

        addGamerListAttributes(model, gamerid, "EXC");
        return "games";
    }

    @RequestMapping("/gamer/{gamerid}/games/wants")
    public String getWants(@PathVariable final Long gamerid, Model model){

        addGamerListAttributes(model, gamerid, "WAN");
        return "games";
    }

    @RequestMapping("/gamer/{gamerid}/games/likes")
    public String getLikes(@PathVariable final Long gamerid, Model model){

        addGamerListAttributes(model, gamerid, "LIK");
        return "games";
    }

    @RequestMapping("/gamer/{gamerid}/games/lolnope")
    public String getLolnope(@PathVariable final Long gamerid, Model model){

        addGamerListAttributes(model, gamerid, "LOL");
        return "games";
    }



    private Gamer getGamerForModel(Long gamerId) {
        Gamer gamer = gamerService.getGamerById(gamerId);
        gamerService.getAllGamerGamesWithCategories(gamer);
        return gamer;
    }

    private void addGamerListAttributes(Model model, Long gamerId, String type){
        Gamer gamer = getGamerForModel(gamerId);

        switch (type) {
            case "ALL" :
                model.addAttribute("games", gamer.getAllGames());
                break;
            case "EXC" :
                model.addAttribute("games", gamer.getExcited());
                break;
            case "WAN" :
                model.addAttribute("games", gamer.getWants());
                break;
            case "LIK" :
                model.addAttribute("games", gamer.getLikes());
                break;
            case "LOL" :
                model.addAttribute("games", gamer.getLolnope());
                break;
            default :
                model.addAttribute("games", gamer.getAllGames());
                break;
        }

        model.addAttribute("metaTitle", gamer.getName() +"'s Games");
        model.addAttribute("gamer", gamer);
    }


    private void addAttributes(Model model, Long gamerId){
        Gamer gamer = getGamerForModel(gamerId);

        model.addAttribute("metaTitle", gamer.getName() +"'s Games");
        model.addAttribute("gamer", gamer);

    }


}
