package Game.CricketGame.repository;

import Game.CricketGame.model.BowlingStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BowlingStatsRepository extends MongoRepository<BowlingStats, String> {
}
