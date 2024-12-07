package Game.CricketGame.service;

import Game.CricketGame.exceptionHanding.BadRequestException;
import Game.CricketGame.exceptionHanding.ConflictException;
import Game.CricketGame.model.Match;
import Game.CricketGame.model.Team;
import Game.CricketGame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchService mc;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    /**
     * Create a new Team
     *
     * This method excepts {@link Team} as payload which includes team name and logo,
     * It validates team name and also ensure that no duplicate entry created
     * @param team Team object containing team name and logo
     * @return object of new created team
     */
    public Team createTeam(Team team) {
        if (team.getName() == null || team.getName().trim().isEmpty()) {
            throw new BadRequestException("Team name missing");
        }
        Team existingTeam = teamRepository.findByName(team.getName());
        if (existingTeam != null) {
            throw new ConflictException("Team already present");
        }
        return teamRepository.save(team);
    }
}
