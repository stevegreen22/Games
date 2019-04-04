package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static hello.Application.gamers;

@Controller
public class GamerController {

    @RequestMapping("/{gamer}/games")
    public String userGames(@PathVariable final String gamer,
                            Model model){

        System.out.println("FDLHGFLJDSFLSDGHLJF");

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
