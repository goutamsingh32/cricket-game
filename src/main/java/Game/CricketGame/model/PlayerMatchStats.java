package Game.CricketGame.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_match_stats")
@Getter
@Setter
public class PlayerMatchStats {

    @Id
    private String id;
    private String playerId; // Reference from players model
    private String playerName;

    // Batting stats
    private int runs;
    private int six;
    private int fours;
    private double strikeRate;
    private int ballFaced;

    // Bowling stats;
    private int wickets;
    private int overs;
    private int ballsBowled;
    private int runsConced;
    private int maiden;
    private double economy;


}
