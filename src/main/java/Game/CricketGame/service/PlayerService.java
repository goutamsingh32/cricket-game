package Game.CricketGame.service;

import Game.CricketGame.exceptionHanding.BadRequestException;
import Game.CricketGame.exceptionHanding.ConflictException;
import Game.CricketGame.model.Player;
import Game.CricketGame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepo;


    /**
     * Create a new player
     * <p>
     * This method accept a {@link Player} in request body and creates a
     * new player entity in database. It validates required field like role, name , country
     * and ensure no duplicate player exist
     *
     * @param player the player object containing details like role, name, dob, country
     * @return a {@link ResponseEntity} contains the created player object and HTTP status code
     * - 201 (created) if player created successfully
     * - 400 (BAD request) if required parameters are missing
     * - 409 (Conflict) if player already exist
     */
    public Player createPlayer(Player player) {
        if (player.getName() == null || player.getName().isEmpty() ||
                player.getCountry() == null || player.getCountry().isEmpty() ||
                player.getRole() == null || player.getRole().isEmpty()
        ) {
            throw new BadRequestException("Parameters missing: name, role, country are required");
        }
        Player existingPlayer = playerRepo.findByNameAndCountry(player.getName(), player.getCountry());
        if (existingPlayer != null) {
            throw new ConflictException("Player already present");
        }
        return playerRepo.save(player);
    }
}

