package Game.CricketGame.repository;

import Game.CricketGame.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findByNameAndCountry(String name, String country);
}
