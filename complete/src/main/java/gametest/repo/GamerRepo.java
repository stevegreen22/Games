package gametest.repo;

import gametest.models.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface GamerRepo extends JpaRepository<Gamer, Long> {
}
