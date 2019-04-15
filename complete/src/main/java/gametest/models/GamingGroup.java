package gametest.models;

import gametest.enums.ACTIVE;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SteveGreen on 07/04/2019.
 */
@Entity
//@Builder(toBuilder = true)
//@AllArgsConstructor(access = AccessLevel.PACKAGE)
//@NoArgsConstructor(access = AccessLevel.PACKAGE)
//@Setter(value = AccessLevel.PACKAGE)
//@Getter
@Table(name="gaming_group")
public class GamingGroup {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Transient private List<Gamer> allGroupMembers = new ArrayList<>();
    @Transient private List<Gamer> activeGroupMembers = new ArrayList<>();
    @Transient private List<Gamer> inactiveGroupMembers = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Gamer> getActiveGroupMembers() {
        return activeGroupMembers;
    }

    public void setActiveGroupMembers(List<Gamer> activeGroupMembers) {
        this.activeGroupMembers = activeGroupMembers;
    }

    public List<Gamer> getInactiveGroupMembers() {
        return inactiveGroupMembers;
    }

    public void setInactiveGroupMembers(List<Gamer> inactiveGroupMembers) {
        this.inactiveGroupMembers = inactiveGroupMembers;
    }

    public Long getId() {

        return this.id;
    }

    public List<Gamer> getAllGroupMembers() {
        return allGroupMembers;
    }

    public void setAllGroupMembers(List<Gamer> allGroupMembers) {
        this.allGroupMembers = allGroupMembers;
    }

    public Map<GamingLocation, ACTIVE> getGamingLocations() {
        return gamingLocations;
    }

    public void setGamingLocations(Map<GamingLocation, ACTIVE> gamingLocations) {
        this.gamingLocations = gamingLocations;
    }

    public List<GamingSession> getUpcomingSessions() {
        return upcomingSessions;
    }

    public void setUpcomingSessions(List<GamingSession> upcomingSessions) {
        this.upcomingSessions = upcomingSessions;
    }

    public List<GamingSession> getPlayedSessions() {
        return playedSessions;
    }

    public void setPlayedSessions(List<GamingSession> playedSessions) {
        this.playedSessions = playedSessions;
    }

    public String getName() {
        return this.name;
    }

    //list of gamers in the group
    // each gamer has their own list of games
    //private List<Gamer> allGroupMembers = new ArrayList<>();
    @Transient
    private Map<GamingLocation, ACTIVE>gamingLocations = new HashMap<>();

    //maybe? Could do swanky diary interaction and sns etc...?
    @Transient
    private List<GamingSession> upcomingSessions = new ArrayList<>();
    @Transient
    private List<GamingSession> playedSessions = new ArrayList<>();


    public GamingGroup(String name) {
        this.name = name;
    }

    public void addLocation(GamingLocation location, ACTIVE activeState) {
        this.gamingLocations.put(location, activeState);
    }

    public void addMember(Gamer gamer) {
        this.allGroupMembers.add(gamer);
    }

    //will want to mark gamer as inactive or somethign to that effect.
    public void makeChangeMemberActiveState(Gamer gamer) {
        //find in map, edit value

    }

    public void updateGroupMembersActivity() {

        for (Gamer gamer : allGroupMembers) {
            if (gamer.getState()==ACTIVE.Active) {
                activeGroupMembers.add(gamer);
            } else {
                inactiveGroupMembers.add(gamer);
            }
        }
    }


}
