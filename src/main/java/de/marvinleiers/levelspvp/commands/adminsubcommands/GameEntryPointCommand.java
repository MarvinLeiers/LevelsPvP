package de.marvinleiers.levelspvp.commands.adminsubcommands;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.commands.Subcommand;
import de.marvinleiers.levelspvp.utils.Messages;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class GameEntryPointCommand extends Subcommand
{
    @Override
    public String getName()
    {
        return "lobby";
    }

    @Override
    public String getDescription()
    {
        return "Set the lobby for the game.";
    }

    @Override
    public String getSyntax()
    {
        return "/pvpadmin lobby <game>";
    }

    @Override
    public void execute(Player player, String[] args)
    {
        if (args.length != 2)
        {
            player.sendMessage("§cUsage: " + getSyntax());
            return;
        }

        Game game = LevelsPvP.getApi().getGame(args[1]);
        game.setEntryPoint(player.getLocation());

        player.sendMessage(Messages.get("game-entry-point-set").replace("%game%", game.getName()));
    }
}
