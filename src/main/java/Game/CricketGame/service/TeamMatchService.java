package Game.CricketGame.service;

import Game.CricketGame.exceptionHanding.ConflictException;
import Game.CricketGame.model.Player;
import Game.CricketGame.model.PlayerMatchStats;
import Game.CricketGame.model.TeamMatchStats;
import Game.CricketGame.repository.PlayerRepository;
import Game.CricketGame.repository.TeamMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamMatchService {

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    TeamMatchRepository teamMatchRepository;

    @Autowired
    PlayerMatchStatsService playerMatchStatsService;

    /**
     * Create a new team_match_stats, for that use any teamId
     * @param teamId Team id
     * @return
     */
    public TeamMatchStats createNewMatchStats(String teamId, String scorecardId){
        TeamMatchStats teamMatchStats = new TeamMatchStats();
        teamMatchStats.setTeamId(teamId);
        teamMatchStats.setScorecardId(scorecardId);
        teamMatchRepository.save(teamMatchStats);
        return teamMatchStats;
    }

    public TeamMatchStats addNewPlayerInTeam(String playerId, String teamMatchId){
        Player player = playerRepo.findById(playerId).orElseThrow(()-> new RuntimeException("Player not found with Id: " +  playerId));
        TeamMatchStats teamMatchStats  = teamMatchRepository.findById(teamMatchId).orElseThrow(()-> new RuntimeException("Match stats not found with Id: " + teamMatchId));

        PlayerMatchStats playerMatchStats = playerMatchStatsService.createNewPlayerMatchStats(player.getId(), player.getName());

        List<String> players = teamMatchStats.getPlayers();

        if(!players.contains(playerMatchStats.getPlayerId())){
            players.add(playerMatchStats.getPlayerId());
            teamMatchStats.setPlayers(players);
            return teamMatchRepository.save(teamMatchStats);
        } else{
            throw new ConflictException("Player already present");
        }
    }
}
