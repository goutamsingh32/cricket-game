package Game.CricketGame.controller;

import Game.CricketGame.ResponseHandling.CustomResponse;
import Game.CricketGame.exceptionHanding.BadRequestException;
import Game.CricketGame.exceptionHanding.ConflictException;
import Game.CricketGame.model.Player;
import Game.CricketGame.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;



    /**
     * Create a new player in DB
     * @param player The player Details
     * @return ResponseEntity containing created player and success status
     * @throws BadRequestException if required fields are missing
     * @throws ConflictException if the player already present
     */
//    @Operation(summary = "Create a new player", description = "Adds a new player to the system.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Player created successfully",
//                    content = @Content(schema = @Schema(implementation = Player.class))),
//            @ApiResponse(responseCode = "400", description = "Bad Request - Missing required fields"),
//            @ApiResponse(responseCode = "409", description = "Conflict - Player already exists")
//    })
    @PostMapping("/player/new")
    public ResponseEntity<CustomResponse<Player>> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.createPlayer(player);

        CustomResponse<Player> response = CustomResponse.success(
                "Player created successfully",
                createdPlayer,
                HttpStatus.CREATED
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
