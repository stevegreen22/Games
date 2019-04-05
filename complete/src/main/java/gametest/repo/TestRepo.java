package gametest.repo;

import gametest.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SteveGreen on 05/04/2019.
 */
@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
}
