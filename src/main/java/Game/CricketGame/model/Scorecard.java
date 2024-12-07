package Game.CricketGame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "scorecard")
@Getter
@Setter
@ToString
public class Scorecard {
    @Id
    private String id;
    private TeamMatchStats team1;
    private TeamMatchStats team2;
    private String matchId;
}
