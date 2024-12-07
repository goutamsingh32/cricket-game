package Game.CricketGame.controller;

import Game.CricketGame.ResponseHandling.CustomResponse;
import Game.CricketGame.model.Scorecard;
import Game.CricketGame.service.ScorecardService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScorecardController {

    @Autowired
    ScorecardService scorecardService;

    /**
     *  Used to add a team in existing scorecard
     * @param requestBody Contains {scorecard, team1, team2} as payload
     * @return
     */
    @PostMapping("/scorecard/team/add")
    public ResponseEntity<CustomResponse<Scorecard>> addTeamInScorecard(@RequestBody JsonNode requestBody){
        String scorecardId = requestBody.get("scorecard").asText();
        String team1 = requestBody.get("team1").asText();
        String team2 = requestBody.get("team2").asText();
        Scorecard updatedScorecard = scorecardService.addTeamInScoreCard(scorecardId, team1, team2);
        return ResponseEntity.ok(CustomResponse.success("Teams added successfully",updatedScorecard,HttpStatus.OK));
    }

}
