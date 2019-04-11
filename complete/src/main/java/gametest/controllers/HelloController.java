package gametest.controllers;

import com.omertron.bgg.BggApi;
import com.omertron.bgg.BggException;
import com.omertron.bgg.model.UserInfo;
import gametest.models.Game;
import gametest.models.Gamer;
import gametest.repo.JpaGamerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    JpaGamerRepo jpaGamerRepo;

    @RequestMapping("/")
    public String homepage() {
        return "welcome";
    }

    @RequestMapping("/templateTest")
    public String testBootStrapTemplate(){
        return "home"; }

    @RequestMapping("/fragmentTest")
    public String testFragments(Model model){
        model.addAttribute("metaTitle", "MYSUPERPAGETITLE");
        return "fragmentTemplate"; }

    @RequestMapping("/allGames")
    public String homepageds(Model model) {

        List<Game> games= new ArrayList<Game>();
        games.add(new Game("dsfsdf"));

        model.addAttribute("games", games);
        return "games";
    }

    @RequestMapping("/allCategories")
    public String homepagesd() {
        return "welcome";
    }


}
