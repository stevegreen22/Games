package gametest.services;

import gametest.enums.ACTIVE;
import gametest.models.Game;
import gametest.models.Gamer;
import gametest.models.GamingGroup;
import gametest.models.GamingLocation;
import gametest.repo.GamingGroupRepo;
import gametest.repo.JpaGamingGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by SteveGreen on 07/04/2019.
 */
@Service
public class GamingGroupService implements GamingGroupRepo {

    @Autowired
    JpaGamingGroupRepo jpaGamingGroupRepo;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<GamingGroup> getAllRegisteredGamingGroups() {
        //use the repo to get all of the groups.
        return jpaGamingGroupRepo.findAll();
    }

    @Override
    public List<GamingGroup> getGamingGroupsForGamer(Gamer gamer) {
        return null;
    }

    @Override
    public GamingGroup getGamingGroupByName(String groupName) {
        return null;
    }

    @Override
    public void addGamerToGamingGroup(Gamer gamer, GamingGroup gamingGroup) {

    }

    @Override
    public void removeGamerFromGamingGroup(Gamer gamer, GamingGroup gamingGroup) {

    }

    @Override
    public void addLocationToGamingGroup(GamingLocation location, GamingGroup gamingGroup) {

    }

    @Override
    public void removeLocationFromGamingGroup(GamingLocation location, GamingGroup gamingGroup) {

    }

    @Override
    public List<GamingLocation> getGamingGroupLocations(GamingGroup gamingGroup) {
        return null;
    }

    @Override
    public List<Gamer> getGamersInGamingGroup(GamingGroup gamingGroup) {

        //todo: add exception handling
        //use the gaming repo to run native sql to pull out the deatils.
        System.out.println("Getting members of gaming group: " +gamingGroup.getName() + " id: " + gamingGroup.getId());

        Query q = entityManager.createNativeQuery(
                "select " +
                        "gamer.id, gamer.name, gamer.active " +
                        "from gaming_group_members " +
                        "inner join gamer on gamer.id = gaming_group_members.gamerid " +
                        "inner join gaming_group on gaming_group.id = gaming_group_members.groupid " +
                        "where gaming_group.id =:gamingGroupId", Gamer.class)
                .setParameter("gamingGroupId", gamingGroup.getId());

        System.out.println(q.getResultList());

        List<Gamer> gamersInGroup = q.getResultList();
        entityManager.close();
        System.out.println("List of gamers in the group: " + gamersInGroup.toString());

        for (Gamer g : gamersInGroup) {
            System.out.println("Gamer "+ g.getName());
        }

        return gamersInGroup;
    }



}
