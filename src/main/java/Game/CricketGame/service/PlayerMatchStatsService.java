package Game.CricketGame.service;

import Game.CricketGame.model.PlayerMatchStats;
import Game.CricketGame.repository.PlayerMatchStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchStatsService {

    @Autowired
    PlayerMatchStatsRepository playerMatchStatsRepo;

    PlayerMatchStats createNewPlayerMatchStats(String playerId, String playerName){
        PlayerMatchStats playerMatchStats = new PlayerMatchStats();
        playerMatchStats.setPlayerId(playerId);
        playerMatchStats.setPlayerName(playerName);
        playerMatchStatsRepo.save(playerMatchStats);
        return  playerMatchStats;
    }
}
