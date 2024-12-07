package Game.CricketGame.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "batting_stats")
@Getter
@Setter
public class BattingStats {
    private String player_id;
    private int runs;
    private int four;
    private int six;
    private int ton;
    private int fifty;
    private double average;
}
