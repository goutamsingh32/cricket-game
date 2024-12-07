package Game.CricketGame.controller;

import Game.CricketGame.ResponseHandling.CustomResponse;
import Game.CricketGame.model.Team;
import Game.CricketGame.service.TeamService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;
//
//    @GetMapping("/")
//    public void redirect(HttpServeletResponse response){
//        response.sendRedirect("/swagger-ui")
//    }

    @GetMapping("/teams")
    public List<Team> getAll(){
        return teamService.getAll();
    }

    @PostMapping("/team/new")
    public ResponseEntity<CustomResponse<Team>> createTeam(@RequestBody  Team team){
        Team createdTeam = teamService.createTeam(team);
        CustomResponse<Team> response = CustomResponse.success(
                "Team created successfully",
                createdTeam,
                HttpStatus.CREATED
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
