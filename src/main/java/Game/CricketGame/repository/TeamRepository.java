package Game.CricketGame.repository;

import Game.CricketGame.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
    Team findByName(String name);
}
