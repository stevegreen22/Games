package gametest.repo;

import gametest.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SteveGreen on 06/04/2019.
 */
@Repository
public interface JpaGameRepo extends JpaRepository<Game, Long> {

}
