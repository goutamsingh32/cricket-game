package Game.CricketGame.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
@Getter
@Setter
public class Team {
    @Id
    private String id;

    private String name;
    private  String logo;

}
