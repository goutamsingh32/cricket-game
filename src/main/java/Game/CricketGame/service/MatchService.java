package Game.CricketGame.service;

import Game.CricketGame.GlobalConstant;
import Game.CricketGame.model.Match;
import Game.CricketGame.model.PlayerMatchStats;
import Game.CricketGame.model.Scorecard;
import Game.CricketGame.model.TeamMatchStats;
import Game.CricketGame.repository.MatchRepository;
import Game.CricketGame.repository.ScorecardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    ScorecardRepository scorecardRepo;

    @Autowired
    ScorecardService scorecardService;

    @Autowired
    PlayerMatchStatsService playerMatchStatsService;

    /**
     * Creates a new match and links it with an empty scorecard.
     *
     * @param newMatch the match details to be created
     * @return the created Match object with the generated ID and linked scorecard ID
     * @throws RuntimeException if any operation (scorecard creation, match saving, or scorecard update) fails
     */
    @Transactional
    public Match createMatch(Match newMatch) {
        try {
            // create an empty scorecard
            Scorecard scorecard = scorecardService.createNewScoreCard();

            newMatch.setScorecardId(scorecard.getId());
            Match createdMatch = matchRepo.save(newMatch);

            scorecard.setMatchId(createdMatch.getId());
            scorecardService.updateScoreCardWithId(scorecard.getId(), "matchId", createdMatch.getId());
            return createdMatch;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a match: " + e.getMessage());
        }
    }

    /**
     * Update the respective player stats when match completes with given matchId
     * and change the status to complete
     * @param matchId id of match that is completed
     * @return Match
     */
    public Match matchCompelete(String matchId){
        Match currentMatch  = matchRepo.findById(matchId).orElseThrow(()-> new RuntimeException("Match not found"));

        //Can set status of match to complete
        currentMatch.setStatus(GlobalConstant.COMPLETE);
        matchRepo.save(currentMatch);

        Scorecard scard = scorecardRepo.findById(currentMatch.getScorecardId()).orElseThrow(()-> new RuntimeException(("scorecard not found")));

        TeamMatchStats team1 = scard.getTeam1();
        TeamMatchStats team2 = scard.getTeam2();

        List<String> playersOfTeam1 = team1.getPlayers();
        //TODO can use asynchronous way
        playersOfTeam1.forEach(playerid -> {
            playerMatchStatsService.updatePlayerStatsWithPlayerStatsId(playerid);
        });

        List<String> playersOfTeam2 = team2.getPlayers();
        playersOfTeam2.forEach(playerid->{
            playerMatchStatsService.updatePlayerStatsWithPlayerStatsId(playerid);
        });
        return  currentMatch;
    }

}
