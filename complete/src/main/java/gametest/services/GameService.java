package gametest.services;

import com.omertron.bgg.BggApi;
import gametest.models.Game;
import gametest.models.Gamer;
import gametest.repo.GameRepo;
import gametest.repo.JpaGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by SteveGreen on 06/04/2019.
 */
@Service
public class GameService implements GameRepo {

    @Autowired
    private JpaGameRepo jpaGameRepo;
    private BggApi bggApi;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getMinPlayerCount(String name) {
        return 0;
    }

    @Override
    public int getMaxPlayerCount(String name) {
        return 0;
    }

    @Override
    public List<Game> getAllGamesByGamerId(Gamer gamer) {

        System.out.println("Get all games by gamer id: "+ gamer.getName() + " with an id of: " + gamer.getId());

        Query q = entityManager.createNativeQuery(
                "SELECT " +
                    "game.name, game.id " +
                    "FROM gamer_game_category " +
                    "INNER JOIN gamer " +
                    "ON gamer.id = gamer_game_category.gamerid " +
                    "INNER JOIN game " +
                    "ON game.id = gamer_game_category.gameid " +
                    "WHERE " +
                    "gamer.id =:gamerid " +
                    "order by " +
                    "game.name asc", Game.class)
                    .setParameter("gamerid", gamer.getId());


        List<Game> games = q.getResultList();
        entityManager.close();
        System.out.println("List of gamer?" + q.getResultList().toString());

        for (Game g : games) {

            System.out.println("Game "
                    + g.getName()
                    + " "
                    + g.toString());
        }

        return games;
    }


}
