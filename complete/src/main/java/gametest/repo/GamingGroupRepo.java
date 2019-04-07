package gametest.repo;

import gametest.models.Gamer;
import gametest.models.GamingGroup;
import gametest.models.GamingLocation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SteveGreen on 07/04/2019.
 */
@Repository
public interface GamingGroupRepo {

    //debug / test only?
    List<GamingGroup> getAllRegisteredGamingGroups();

    List<GamingGroup> getGamingGroupsForGamer(Gamer gamer);
    GamingGroup getGamingGroupByName(String groupName);
    void addGamerToGamingGroup(Gamer gamer, GamingGroup gamingGroup);
    void removeGamerFromGamingGroup(Gamer gamer, GamingGroup gamingGroup);
    void addLocationToGamingGroup(GamingLocation location, GamingGroup gamingGroup);
    void removeLocationFromGamingGroup(GamingLocation location, GamingGroup gamingGroup);
    List<GamingLocation> getGamingGroupLocations(GamingGroup gamingGroup);
    List<Gamer> getGamersInGamingGroup(GamingGroup gamingGroup);



}
