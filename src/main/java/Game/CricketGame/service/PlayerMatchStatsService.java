package Game.CricketGame.service;

import Game.CricketGame.model.BattingStats;
import Game.CricketGame.model.BowlingStats;
import Game.CricketGame.model.Player;
import Game.CricketGame.model.PlayerMatchStats;
import Game.CricketGame.repository.BattingStatsRepository;
import Game.CricketGame.repository.BowlingStatsRepository;
import Game.CricketGame.repository.PlayerMatchStatsRepository;
import Game.CricketGame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchStatsService {

    @Autowired
    PlayerMatchStatsRepository playerMatchStatsRepo;

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    BattingStatsRepository battingRepo;

    @Autowired
    BowlingStatsRepository bowlingRepo;

    PlayerMatchStats createNewPlayerMatchStats(String playerId, String playerName) {
        PlayerMatchStats playerMatchStats = new PlayerMatchStats();
        playerMatchStats.setPlayerId(playerId);
        playerMatchStats.setPlayerName(playerName);
        playerMatchStatsRepo.save(playerMatchStats);
        return playerMatchStats;
    }

    /**
     * Updates both batting and bowling statistics for a player using their match statistics ID.
     *
     * <p>This method performs the following steps:
     * <ol>
     *   <li>Fetches the player match statistics using the provided ID.</li>
     *   <li>Finds the corresponding player based on the player ID from the match stats.</li>
     *   <li>Updates the player's batting and bowling statistics based on their match performance.</li>
     * </ol>
     *
     * @param playerMatchStatsId the ID of the player's match statistics
     * @throws RuntimeException if the player match statistics or the player is not found
     */
    public void updatePlayerStatsWithPlayerStatsId(String playerMatchStatsId) {
        PlayerMatchStats stats = playerMatchStatsRepo.findById(playerMatchStatsId).orElseThrow(() -> new RuntimeException("Invalid player match stats id" + playerMatchStatsId));

        Player player = playerRepo.findById(stats.getPlayerId()).orElseThrow(() -> new RuntimeException("player not found with player match stats id"));
        updateBattingStats(player, stats);
        updateBowlingStats(player, stats);
        return;
    }

    /**
     * Update the batting stats of a player after match
     *
     * <p>
     *     This method retrieves the player's bowling stats, updates the fields based on
     *     the performance in the current match (provided in {@link PlayerMatchStats}, and save the changes/
     * </p>
     * <p>
     *     Key updates include:
     *     - Increment the number of innings played
     *     - Incrementing the total runs, 4s hit, sixes, tons, or fifty
     *     - Update strike rate and average
     * </p>
     *
     * @param player  the player whose batting stats are being updated
     * @param playerMatchStats the performance statistics of the player in the current match
     * @throws RuntimeException if the player's bowling statistics are not found in the repository
     */
    public void updateBattingStats(Player player, PlayerMatchStats playerMatchStats) {
        // Fetch batting stats or throw exception if not found
        BattingStats stats = battingRepo.findById(player.getBattingStatsId()).orElseThrow(() -> new RuntimeException(("Unable to find batting stats")));

        // Update the stats

        // Update aggregate stats
        stats.setRuns(stats.getRuns() + playerMatchStats.getRuns());
        stats.setFour(stats.getFour() + playerMatchStats.getFours());
        stats.setSix(stats.getSix() + playerMatchStats.getSix());
        stats.setBowlsFaced(stats.getBowlsFaced() + playerMatchStats.getBallFaced());

        stats.setStrikeRate(stats.getBowlsFaced() > 0
                ? (double) (stats.getRuns() * 100) / stats.getBowlsFaced()
                : 0.0);
        stats.setInnings(stats.getInnings() + 1);
        stats.setAverage(stats.getInnings() > 0
                ? (double) stats.getRuns() / stats.getInnings()
                : 0.0);
        if (playerMatchStats.getRuns() >= 100) {
            stats.setTon(stats.getTon() + 1);
        } else if (playerMatchStats.getRuns() >= 50) {
            stats.setFifty(stats.getFifty() + 1);
        }
        battingRepo.save(stats);
        return;
    }

    /**
     * Updates the bowling statistics of a player after a match.
     *
     * <p>This method retrieves the player's bowling stats, updates the fields based on
     * the performance in the current match (provided in {@link PlayerMatchStats}), and saves the changes.
     *
     * <p>Key updates include:
     * - Incrementing the total wickets taken.
     * - Adding to the balls bowled and runs conceded.
     * - Recalculating derived metrics like economy rate.
     * - Incrementing the five-wicket haul count if applicable.
     *
     * @param player           the player whose bowling stats are being updated
     * @param playerMatchStats the performance statistics of the player in the current match
     * @throws RuntimeException if the player's bowling statistics are not found in the repository
     */
    public void updateBowlingStats(Player player, PlayerMatchStats playerMatchStats) {
        BowlingStats bowlingStats = bowlingRepo.findById(player.getBowlingStatsId()).orElseThrow(()-> new RuntimeException("Bowling stats not found"));

        bowlingStats.setInnings(bowlingStats.getInnings());
        bowlingStats.setWickets(bowlingStats.getWickets() + playerMatchStats.getWickets());
        if(playerMatchStats.getWickets() >= 5){
            bowlingStats.setWickets(bowlingStats.getWickets() + 1);
        }
        bowlingStats.setBallsBowled(bowlingStats.getBallsBowled() + playerMatchStats.getBallsBowled());
        bowlingStats.setRunsConced(bowlingStats.getRunsConced() + playerMatchStats.getRunsConced());

        if(playerMatchStats.getBallsBowled() > 0){
            bowlingStats.setEconomy((double) 6 * bowlingStats.getRunsConced() / bowlingStats.getRunsConced());
        }
        bowlingRepo.save(bowlingStats);
        return;
    }
}
