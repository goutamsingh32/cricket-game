package Game.CricketGame.service;

import Game.CricketGame.exceptionHanding.ConflictException;
import Game.CricketGame.model.Player;
import Game.CricketGame.model.PlayerMatchStats;
import Game.CricketGame.model.Team;
import Game.CricketGame.model.TeamMatchStats;
import Game.CricketGame.repository.PlayerRepository;
import Game.CricketGame.repository.TeamMatchRepository;
import Game.CricketGame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamMatchService {

    // Services
    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerMatchStatsService playerMatchStatsService;

    @Autowired
    PlayerRepository playerRepo;

    // Repository
    @Autowired
    TeamRepository teamRepo;

    @Autowired
    TeamMatchRepository teamMatchRepository;


    /**
     * Create a new team_match_stats, for that use any teamId
     * @param teamId Team id
     * @return
     */
    public TeamMatchStats createNewMatchStats(String teamId, String scorecardId){
        Team team = teamRepo.findById(teamId).orElseThrow(()-> new RuntimeException("Invalid teamId - team not found" + teamId));

        TeamMatchStats teamMatchStats = new TeamMatchStats();
        teamMatchStats.setTeamId(teamId);
        teamMatchStats.setTeamName(team.getName());
        teamMatchStats.setScorecardId(scorecardId);
        teamMatchRepository.save(teamMatchStats);
        return teamMatchStats;
    }

    public TeamMatchStats addNewPlayerInTeam(String playerId, String teamMatchId){
        Player player = playerRepo.findById(playerId).orElseThrow(()-> new RuntimeException("Player not found with Id: " +  playerId));
        TeamMatchStats teamMatchStats  = teamMatchRepository.findById(teamMatchId).orElseThrow(()-> new RuntimeException("Match stats not found with Id: " + teamMatchId));

        PlayerMatchStats playerMatchStats = playerMatchStatsService.createNewPlayerMatchStats(player.getId(), player.getName());
        List<String> playersStats = teamMatchStats.getPlayers(); // List of players match stats
        playersStats.add(playerMatchStats.getId());
        teamMatchStats.setPlayers(playersStats);
        playerService.playerPlaysWithNewTeam(teamMatchStats.getTeamId(), playerId); // TODO this code should not block player addition
        return teamMatchRepository.save(teamMatchStats);

        // TODO check duplicate
//        if(!playersStats.contains(playerMatchStats.getPlayerId())){
//            playersStats.add(playerMatchStats.getPlayerId());
//            teamMatchStats.setPlayers(playersStats);
//            return teamMatchRepository.save(teamMatchStats);
//        } else{
//            throw new ConflictException("Player already present");
//        }
    }

    /**
     * @deprecated
     * Can implement same flow if we have only teamMatchId
     * But for now scorecard contains whole TeamMatchStats object instead of Id, so will use that
     */
//    public Void updatePlayerStatsWithTeamMatchId(String teamMatchId){
//        TeamMatchStats teamMatchStats = teamMatchRepository.findById(teamMatchId).orElseThrow(()-> new RuntimeException("team match stats not found"));
//
//        List<String> playerStatsId  = teamMatchStats.getPlayers();
//
//
//    }



}
