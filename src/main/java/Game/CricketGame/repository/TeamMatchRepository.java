package Game.CricketGame.repository;

import Game.CricketGame.model.TeamMatchStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamMatchRepository extends MongoRepository<TeamMatchStats, String> {
}
