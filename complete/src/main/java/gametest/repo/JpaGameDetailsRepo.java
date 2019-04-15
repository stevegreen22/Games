package gametest.repo;

import gametest.models.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SteveGreen on 06/04/2019.
 */
@Repository
public interface JpaGameDetailsRepo extends JpaRepository<GameDetails, Long> {

}
