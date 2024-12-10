package Game.CricketGame.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bowling_stats")
@Getter
@Setter
public class BowlingStats {
    private String player_id;
    private int innings;
    private int wickets;
    private int ballsBowled;
    private int runsConced;
    private double economy;
    private int five_wicket_hall;

}
