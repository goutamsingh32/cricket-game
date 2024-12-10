package Game.CricketGame.controller;

import Game.CricketGame.ResponseHandling.CustomResponse;
import Game.CricketGame.model.Match;
import Game.CricketGame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    /**
     * Create a new match
     * @param match
     * @return
     */
    @PostMapping("/match/new")
    public ResponseEntity<CustomResponse<Match>> createNewGame(@RequestBody Match match) {
        try {
            Match createdMatch = matchService.createMatch(match);
            CustomResponse<Match> response = CustomResponse.success(
                    "Match created successfully",
                    createdMatch,
                    HttpStatus.CREATED
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            //TODO
            throw new RuntimeException("Something went wrong");
        }
    }

    /**
     * Complete match
     * @param matchId id of match that is compeleted
     * @return
     */
    @PostMapping("/match/complete")
    public ResponseEntity<CustomResponse<Match>> matchCompelete(@RequestBody String matchId){
        Match match = matchService.matchCompelete(matchId);
        CustomResponse<Match> response = CustomResponse.success("Stats updated successfully", match, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
