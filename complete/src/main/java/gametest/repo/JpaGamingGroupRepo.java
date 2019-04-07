package gametest.repo;

import gametest.models.GamingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SteveGreen on 07/04/2019.
 */
@Repository
public interface JpaGamingGroupRepo extends JpaRepository<GamingGroup, Long> {
}
