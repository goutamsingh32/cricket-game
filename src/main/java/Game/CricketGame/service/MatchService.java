package Game.CricketGame.service;

import Game.CricketGame.model.Match;
import Game.CricketGame.model.Scorecard;
import Game.CricketGame.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    ScorecardService scorecardService;

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

    public Match matchCompelete(String matchId){
        Match currentMatch  = matchRepo.findById(matchId).orElseThrow(()-> new RuntimeException("Match not found"));

        //Can set status of match to complete
    }

}
