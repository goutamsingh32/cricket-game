package Game.CricketGame.repository;

import Game.CricketGame.model.PlayerMatchStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerMatchStatsRepository extends MongoRepository<PlayerMatchStats, String> {
}
