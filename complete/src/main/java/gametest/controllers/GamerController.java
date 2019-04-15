package gametest.controllers;

import com.omertron.bgg.BggApi;
import com.omertron.bgg.BggException;
import com.omertron.bgg.model.BoardGameExtended;
import com.omertron.bgg.model.SearchWrapper;
import gametest.gcp_storage.StorageUpload;
import gametest.models.Game;
import gametest.models.GameDetails;
import gametest.models.Gamer;
import gametest.repo.JpaGameDetailsRepo;
import gametest.repo.JpaGameRepo;
import gametest.services.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GamerController {

    @Autowired
    private GamerService gamerService;

    //both used for testing the new game test table.
    @Autowired
    JpaGameDetailsRepo gameDetailsRepo;
    @Autowired
    JpaGameRepo gameRepo;

    @RequestMapping("/viewGamers")
    public String viewGamers(Model model) {

        List<Gamer> gamers = gamerService.getAllGamers();

        //doStuff();

        model.addAttribute("metaTitle", "All Registered Gamers");
        model.addAttribute("gamers", gamers);
        return "gamers";
    }



    //testing
    public void doStuff(){
        Game test = new Game("Great Western Trail_2");
        GameDetails gameDetails = new GameDetails(
                12341, 2, 4,
                "myImageLoc", "myImageName");

        test.setGameDetails(gameDetails);
        gameDetails.setGame(test);

        gameRepo.save(test);



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


        //Quick/ dirty test before work
        BggApi bggApi = new BggApi();

        for (Game game: gamer.getLikes()) {
            try {
                SearchWrapper result = bggApi.searchBoardGame(game.getName(), false, false);
                int bggGameId = result.getItems().get(0).getId();

                List<BoardGameExtended> result2 = bggApi.getBoardGameInfo(bggGameId);
                for (BoardGameExtended bg : result2) {

                    StorageUpload storageUpload = new StorageUpload();
                    storageUpload.doPostToStorage(bg.getImage(),bg.getName());

//                    doPostToStorage(String urlFileName, String saveFileName) {

                    // Save file to temp location
                    // Upload File to GCP
                    // Clean up and remove file from temp location
                    System.out.println(bg.getImage());

                    // use this new file name location to set the details in the object
                    // serve up the file from GCP using the object filename.
                    game.setImageString(bg.getImage());
                }
            } catch (BggException e) {
                e.printStackTrace();
            }
        }

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
