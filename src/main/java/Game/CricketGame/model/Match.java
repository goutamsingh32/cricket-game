package Game.CricketGame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "match")
@Getter
@Setter
@ToString
public class Match {

//    private String team_id1;
//    private String team_id2;
    @Id
    private String id;

    private String venue;
    private List<String> umpires;
    private String toss;
    private String host;
    private String scorecardId;
}
