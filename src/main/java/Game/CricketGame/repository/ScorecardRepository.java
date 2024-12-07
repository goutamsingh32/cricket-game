package Game.CricketGame.repository;

import Game.CricketGame.model.Scorecard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScorecardRepository extends MongoRepository<Scorecard, String> {
}
