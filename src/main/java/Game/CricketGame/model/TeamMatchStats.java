package Game.CricketGame.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "team_match_stats")
@Getter
@Setter
public class TeamMatchStats {

    @Id
    private String id;
    private String teamId; //Reference from team model
    private String teamName; // Reference from team model

    private String scorecardId; // Referenced from scorecard(optional for now)
    private int totalRuns;
    private int totalWickets;
    private double overs;
    private int extras;

    private List<String> players = new ArrayList<>(); // Id's of playerMatchStats
}
