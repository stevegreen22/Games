package gametest.repo;

import gametest.models.Gamer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SteveGreen on 06/04/2019.
 */
@Repository
public interface GamerRepo {
    List<Gamer> getAllGamers();

    Gamer getGamerById(Long gamerid);

    //obtain all the games for the gamer from the db and set in their list.
    void populateGamerGames(Gamer gamer);
}
