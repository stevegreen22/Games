package gametest.models;

import com.omertron.bgg.model.UserInfo;
import gametest.enums.ACTIVE;
import gametest.enums.CATEGORY;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Table(name="gamer")
public class Gamer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public ACTIVE getState() {
        return state;
    }

    public void setState(ACTIVE state) {
        this.state = state;
    }

    //todo: add Active State in
    @Column(name = "active")
    private ACTIVE state; //ACTIVE.Active; //defaulted

    @Transient private List<Game>allGames = new ArrayList<>();
    @Transient private List<Game>excited = new ArrayList<>();
    @Transient private List<Game>wants = new ArrayList<>();
    @Transient private List<Game>likes = new ArrayList<>();
    @Transient private List<Game>lolnope = new ArrayList<>();
    @Transient private UserInfo bggUserinfo = null;

    public Map<Game, CATEGORY> getAllGamesWithCategories() {
        return allGamesWithCategories;
    }

    public void setAllGamesWithCategories(Map<Game, CATEGORY> allGamesWithCategories) {
        this.allGamesWithCategories = allGamesWithCategories;
    }

    @Transient private Map<Game, CATEGORY> allGamesWithCategories = new HashMap<>();

    public Gamer(String name){

        this.name = name;
        this.state = ACTIVE.Active;
    }


    public Long getId() { return id; }

    public List<Game> getAllGames() { return allGames; }
    public void setAllGames(List<Game>games){
        allGames = games;
    }

    public UserInfo getBggUserinfo() {
        return bggUserinfo;
    }

    public void setBggUserinfo(UserInfo bggUserinfo) {
        this.bggUserinfo = bggUserinfo;
    }

    public List<Game> getExcited() {
        return excited;
    }

    public void setExcited(List<Game> excited) {
        this.excited = excited;
    }

    public List<Game> getWants() {
        return wants;
    }

    public void setWants(List<Game> wants) {
        this.wants = wants;
    }

    public List<Game> getLikes() {
        return likes;
    }

    public void setLikes(List<Game> likes) {
        this.likes = likes;
    }

    public List<Game> getLolnope() {
        return lolnope;
    }

    public void setLolnope(List<Game> lolnope) {
        this.lolnope = lolnope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //Deprecated, no longer in use unless a list is required via a file.
    @Transient private final String prefix_win = "C:\\Users\\Steve\\Downloads\\gs-spring-boot-master\\gs-spring-boot-master\\complete\\src\\main\\";
    @Transient private final String prefix_mac = "/Users/SteveGreen/Downloads/gameapp/Games/complete/src/main/resources/tempgamelists/";
    private void CreateExcited(String s) {
        UpdateList(s, excited);
    }
    private void CreateWants(String s) {
        UpdateList(s, wants);
    }
    private void CreateLikes(String s) {
        UpdateList(s, likes);
    }
    private void CreateFuckNo(String s) {
        UpdateList(s, lolnope);
    }

    public void CreateGamingLists() {
        CreateExcited(prefix_mac + this.name+"_excited");
        CreateWants(prefix_mac + this.name+"_wants");
        CreateLikes(prefix_mac + this.name+"_likes");
        CreateFuckNo(prefix_mac + this.name+"_fuckno");
    }

    private void UpdateList(String filename, List<Game>list){
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()){
                list.add(new Game(scan.nextLine()));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
