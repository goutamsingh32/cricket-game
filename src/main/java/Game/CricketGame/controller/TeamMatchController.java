package Game.CricketGame.controller;

import Game.CricketGame.ResponseHandling.CustomResponse;
import Game.CricketGame.model.TeamMatchStats;
import Game.CricketGame.service.TeamMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
public class TeamMatchController {

    @Autowired
    TeamMatchService teamMatchService;

    @PostMapping("/match/team/player/add")
    ResponseEntity<CustomResponse<?>> addPlayer(@RequestBody JsonNode requestBody){
        String playerId = requestBody.get("playerId").asText();
        String teamMatchId = requestBody.get("teamMatchId").asText();
        TeamMatchStats teamMatchStats = teamMatchService.addNewPlayerInTeam(playerId, teamMatchId);
        CustomResponse<?> response = CustomResponse.success(
                "Player added to" + teamMatchStats.getTeamName(),
                teamMatchStats,
                HttpStatus.CREATED
        );
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
