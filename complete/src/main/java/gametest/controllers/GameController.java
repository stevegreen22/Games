package gametest.controllers;

import gametest.models.Game;
import gametest.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GameController {

    @Autowired private GameService gameService;


    // Return a list of all games in the library, this will take on the role
    // of displaying all of the games that have been added to the 'game' or
    // 'library' db.
    // if no games in library (won't happen unless first instance, search from bgg.
    @RequestMapping("/gamesLibrary")
    public String viewGameLibrary(Model model) {

        //obtian all games from the table, method doesn't exist.
        List<Game> gameLibrary = gameService.getAllGamesByGamerId(null);

        model.addAttribute("metaTitle", "Games Library");
        model.addAttribute("games", gameLibrary);
        return "gamesLibrary";
    }


    // Adding a new game to the library, this will come about when a user
    // searches for a game using BGG API.  Games will have been displayed
    // ID etc of game will be passed to here for addition to DB
    @RequestMapping("/gamesLibrary/addGame")
    public String addGameToLibrary(Model model) {

        //obtain all games from the table, method doesn't exist.
        List<Game> gameLibrary = gameService.getAllGamesByGamerId(null);

        model.addAttribute("metaTitle", "Games Library");
        model.addAttribute("games", gameLibrary);
        return "gamesLibrary";
    }

}
