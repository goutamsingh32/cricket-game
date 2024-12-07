package Game.CricketGame;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableSwagger2
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "GameOfCricket",version = "1.0"))
@EnableWebMvc
public class CricketGameApplication {
	public static void main(String[] args) {
		SpringApplication.run(CricketGameApplication.class, args);
	}

}
