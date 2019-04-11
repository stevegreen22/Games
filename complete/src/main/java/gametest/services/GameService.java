package gametest.services;

import com.omertron.bgg.BggApi;
import gametest.enums.CATEGORY;
import gametest.models.Game;
import gametest.models.Gamer;
import gametest.repo.GameRepo;
import gametest.repo.JpaGameRepo;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        System.out.println("List of games: " + q.getResultList().toString());

        for (Game g : games) {

            System.out.println("Game "
                    + g.getName()
                    + " "
                    + g.toString());
        }

        return games;
    }


    // obtain the list of a gamers games but with the category also.
    @Override
    public Map<Game, CATEGORY> getAllGamesWithCategoriesByGamerId(Gamer gamer) {

        System.out.println("Get all games(CATEGORY AS WELL) by gamer id: " +
                ""+ gamer.getName() + " with an id of: " + gamer.getId());


        StatelessSession session =
                ((Session) entityManager
                    .getDelegate())
                    .getSessionFactory()
                    .openStatelessSession();


        //Todo: remove string ,format
        Iterator q = session.createNativeQuery(String.format(
                "SELECT " +
                        "game.id, game.name as gamename, gamer_game_category.categoryid, category.name as catname " +
                        "FROM gamer_game_category " +
                        "INNER JOIN gamer " +
                        "ON gamer.id = gamer_game_category.gamerid " +
                        "INNER JOIN game " +
                        "ON game.id = gamer_game_category.gameid " +
                        "INNER JOIN category " +
                        "ON categoryid = category.id " +
                        "WHERE " +
                        "gamer.id = ? " +
                        "order by " +
                        "category.value desc, game.name asc", gamer.getId()))
                .setParameter(1, gamer.getId())
//                .setLong(0, gamer.getId())
                .list()
                .iterator();

        Map<Game, CATEGORY> results = new HashMap<>();

        //Todo: consider the transformation object to make this a little tidier and futureproof
        while (q.hasNext()) {
            Object[] tupleData = (Object[]) q.next();
            Game game = new Game( (String)tupleData[1]);
            CATEGORY gameCat = CATEGORY.valueOf( (String)tupleData[3] );
            results.put(game, gameCat);
            System.out.println("Game with category: " + game.getName() + " : " + gameCat.name());
        }

        session.close();
        entityManager.close();

        return results;

    }


}
