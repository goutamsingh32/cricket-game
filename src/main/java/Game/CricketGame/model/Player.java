package Game.CricketGame.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "player")
@Getter
@Setter
public class Player {

    @Id
    private String id;

    private String name;
    private String role;
    private String country;

    private Date dob;
    private String battingStyle;
    private String bowlingStyle;

    private String battingStatsId;
    private String bowlingStatsId;

    private List<String> teamIds;
}
