package gametest.services;

import gametest.enums.CATEGORY;
import gametest.models.Game;
import gametest.models.Gamer;
import gametest.repo.GameRepo;
import gametest.repo.GamerRepo;
import gametest.repo.JpaGamerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Created by SteveGreen on 06/04/2019.
 */
@Service
public class GamerService implements GamerRepo {

    @Autowired
    private JpaGamerRepo jpaGamerRepo;

    @Autowired
    private GameRepo gameRepo;

    @PersistenceContext
    private EntityManager entityManager;


    // Return all gamers in the DB
    @Override
    public List<Gamer> getAllGamers() {
        return jpaGamerRepo.findAll();
    }

    // Return all gamers that are within a gaming group

    // Return all gamers within a gaming group that are signed up to a gaming session.

    @Override
    @Cacheable("gamers")
    public Gamer getGamerById(Long gamerid) {

        Optional<Gamer> gamer = jpaGamerRepo.findById(gamerid);
        if(gamer.isPresent()) {
            return gamer.get();
        } else {
            //throw an exception / display message etc.
            return null;
        }
    }

    @Override
    public void getAllGamerGames(Gamer gamer) {

        System.out.println("Populated all gamer games for :" + gamer.getName());
        gamer.setAllGames(gameRepo.getAllGamesByGamerId(gamer));
    }

    @Override
    public void getAllGamerGamesWithCategories(Gamer gamer) {
        System.out.println("Retrieving games with categories as a map<Game, CAT>");
        gamer.setAllGamesWithCategories(gameRepo.getAllGamesWithCategoriesByGamerId(gamer));
        setGamersLists(gamer);

    }

    //private?  at some point I will want this to be handled differently but for now...
    private void setGamersLists(Gamer gamer){
        //iterate through the allgameswithcatlist
        //if cat = n, add to list of type n.
        Map<Game, CATEGORY> in = gamer.getAllGamesWithCategories();

        //rough
        List<Game> ex = new ArrayList<>() ;
        List<Game> wa = new ArrayList<>() ;
        List<Game> li = new ArrayList<>() ;
        List<Game> lo = new ArrayList<>() ;
        List<Game> al = new ArrayList<>() ;

        in.forEach((k,v) -> {
            switch (v) {
                case Excited:
                    ex.add(k);  //todo: add a gamer.addgame(listtoaddto)
                    al.add(k);
                    break;
                case Want:
                    wa.add(k);
                    al.add(k);
                    break;
                case Like:
                    li.add(k);
                    al.add(k);
                    break;
                case Lolnope:
                    lo.add(k);
                    al.add(k);
                    break;
            }
        });

        gamer.setExcited(ex);
        gamer.setWants(wa);
        gamer.setLikes(li);
        gamer.setLolnope(lo);
        gamer.setAllGames(al);

//        List<Game> combinedList = Stream.of(ex, wa, li, lo)
//                .flatMap(x -> x.stream())
//                .collect(Collectors.toList());
//
//        gamer.setAllGames(combinedList);


    }

    private void getAllGamersEntityManagerTest(Gamer gamer) {

        Query q = entityManager.createNativeQuery("SELECT a.name, a.id FROM gamer a", Gamer.class);
        List<Gamer> gamers = q.getResultList();

        System.out.println("lList of gamers?" + q.getResultList().toString());

        for (Gamer g : gamers) {
            System.out.println("Gamer "
                    + g.getName()
                    + " "
                    + g.toString());
        }
    }
}
