package gametest.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //obtain some values using the BGG API and store them.  Odds are these games may be played again.
    //When a game is removed from the list because it's been played, we will add it to 'played' list
    //so this information will still be useful to keep.
    @Transient
    private int minPlayerCount;
    @Transient
    private int maxPlayerCount;
    @Transient
    private int ownerId;

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
