package de.marvinleiers.levelspvp.listeners;

import de.marvinleiers.gameapi.GameAPI;
import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.gameapi.game.GamePlayer;
import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.utils.Messages;
import de.marvinleiers.levelspvp.utils.Teams;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeamSelectMoveEvent implements Listener
{
    public static HashMap<Game, ArrayList<Teams>> teamSelections = new HashMap<>();

    public TeamSelectMoveEvent()
    {
        for (Game game : LevelsPvP.getApi().games)
        {
            ArrayList<Teams> teams = new ArrayList<>();

            for (Map.Entry<String, Object> entry : LevelsPvP.getApi().gamesConfig.getConfig().getConfigurationSection("games." + game.getName() + ".teams.selector").getValues(false).entrySet())
            {
                Location location = LevelsPvP.getApi().gamesConfig.getLocation("games." + game.getName() + ".teams.selector." + entry.getKey());
                teams.add(new Teams(game, location, Teams.Team.valueOf(entry.getKey().toUpperCase())));

                System.out.println("added team " + entry.getKey() + " for game " + game.getName());
            }

            teamSelections.put(game, teams);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();

        if (!GameAPI.inGame(player))
            return;

        GamePlayer gp = LevelsPvP.getApi().gameplayers.get(player);
        Game game = gp.getGame();

        if (!game.inLobby())
            return;

        if (!teamSelections.containsKey(game))
            return;

        for (Teams teams : teamSelections.get(game))
        {
            if (teams.getSelectionLocation().distance(event.getTo()) <= 2)
            {
                if (gp.getTeam() == null || !gp.getTeam().equalsIgnoreCase(teams.getTeam().toString()))
                {
                    teams.join(gp);
                }

                return;
            }
        }
    }
}
