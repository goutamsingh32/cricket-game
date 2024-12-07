package Game.CricketGame.repository;

import Game.CricketGame.model.BattingStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattingStatsRepository extends MongoRepository<BattingStats, String> {
}
