package gametest.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

//Does this need to be an entity?!
@Entity
@Table(name="game_details")
public class GameDetails {

    //will hibernate create in GCP?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //gameid?

    private int bggId;

    private int minPlayer;

    private int maxPlayer;

    @Size(max = 100)
    private String imageLocation; //use for pathing to the bucket?

    @Size(max = 100)
    private String imageName; //may not need both.

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public GameDetails(){}

    public GameDetails(int bggId, int minPlayer, int maxPlayer, String imageLocation, String imageName) {
        this.bggId = bggId;
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.imageLocation = imageLocation;
        this.imageName = imageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBggId() {
        return bggId;
    }

    public void setBggId(int bggId) {
        this.bggId = bggId;
    }

    public int getMinPlayer() {
        return minPlayer;
    }

    public void setMinPlayer(int minPlayer) {
        this.minPlayer = minPlayer;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}
