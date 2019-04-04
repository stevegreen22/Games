package interfaces;

import models.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IGamer  extends JpaRepository<Gamer, Long> {

    Gamer getGamerByName(Map<String, Gamer> gamers, String name);
}
