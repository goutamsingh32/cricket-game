package Game.CricketGame.service;

import Game.CricketGame.model.Scorecard;
import Game.CricketGame.model.TeamMatchStats;
import Game.CricketGame.repository.ScorecardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ScorecardService {

    @Autowired
    ScorecardRepository scoreRepo;

    @Autowired
    TeamMatchService teamMatchService;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * For now it creates an empty scorecard
     * @return
     */
    public Scorecard createNewScoreCard(){
        Scorecard scorecard = new Scorecard();
        return scoreRepo.save(scorecard);
    }

    /**
     * Update a specific filed in a scorecard document
     *
     * @param scorecardId The ID of scorecard to update
     * @param fieldName The filed to be updated
     * @param filedValue The new value for the field
     * @return
     */
    public void updateScoreCardWithId(String scorecardId, String fieldName, Object filedValue){
        Query query = new Query(Criteria.where("_id").is(scorecardId));

        Update update = new Update().set(fieldName, filedValue);

        mongoTemplate.updateFirst(query, update, Scorecard.class);
    }

    public Scorecard addTeamInScoreCard(String scorecardId, String teamId1, String teamId2){
        TeamMatchStats team1 =  teamMatchService.createNewMatchStats(teamId1, scorecardId);
        TeamMatchStats team2 =  teamMatchService.createNewMatchStats(teamId2, scorecardId);

        Scorecard scard = scoreRepo.findById(scorecardId).orElseThrow(() -> new RuntimeException("Scorecard not found with ID: " + scorecardId));
        scard.setTeam1(team1);
        scard.setTeam2(team2);
        return  scard;
    }

}
