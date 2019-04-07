package gametest.repo;

import gametest.enums.CATEGORY;
import gametest.models.Game;
import gametest.models.Gamer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by SteveGreen on 06/04/2019.
 */
@Repository
public interface GameRepo {

    int getMinPlayerCount(String name);
    int getMaxPlayerCount(String name);

    List<Game> getAllGamesByGamerId(Gamer gamer);
    Map<Game, CATEGORY> getAllGamesWithCategoriesByGamerId(Gamer gamer);
}
