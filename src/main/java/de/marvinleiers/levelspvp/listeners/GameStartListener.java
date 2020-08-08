package de.marvinleiers.levelspvp.listeners;

import de.marvinleiers.gameapi.events.GameStartEvent;
import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.gameapi.game.GamePlayer;
import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.utils.Teams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStartListener implements Listener
{
    @EventHandler
    public void onGameStart(GameStartEvent event)
    {
        Game game = event.getGame();

        for (Player player : game.players)
        {
            GamePlayer gp = LevelsPvP.getApi().gameplayers.get(player);

            if (gp.getTeam() == null)
            {
                for (Teams teams : TeamSelectMoveEvent.teamSelections.get(game))
                {
                    if (teams.getMembers().size() < 4)
                    {
                        teams.join(gp);
                    }
                }
            }
        }

        for (Teams teams : TeamSelectMoveEvent.teamSelections.get(game))
        {
            System.out.println("path " + "games." + game.getName() + ".teams." + teams.getTeam().toString() + ".spawn");
            for (GamePlayer gp : teams.getMembers())
                gp.getPlayer().teleport(LevelsPvP.getApi().gamesConfig.getLocation("games." + game.getName() + ".teams." + teams.getTeam().toString().toLowerCase() + ".spawn"));
        }
    }
}
