package gametest.services;

import gametest.models.Game;
import gametest.models.Gamer;
import gametest.repo.GameRepo;
import gametest.repo.GamerRepo;
import gametest.repo.JpaGameRepo;
import gametest.repo.JpaGamerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
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

    @Override
    public List<Gamer> getAllGamers() {
        return jpaGamerRepo.findAll();
    }

    @Override
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
    public void populateGamerGames(Gamer gamer) {

        System.out.println("Populated all gamer games for :" + gamer.getName());
        gamer.setAllGames(gameRepo.getAllGamesByGamerId(gamer));


    }


    private void getAllGamersEntityManagerTest(Gamer gamer) {

        Query q = entityManager.createNativeQuery("SELECT a.name, a.id FROM Gamer a", Gamer.class);
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
