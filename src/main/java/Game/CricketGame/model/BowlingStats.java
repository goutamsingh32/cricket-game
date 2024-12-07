package Game.CricketGame.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bowling_stats")
@Getter
@Setter
public class BowlingStats {
    private String player_id;
    private int matches;
    private int wickets;
    private double economy;
    private int five_wicket_hall;

}
