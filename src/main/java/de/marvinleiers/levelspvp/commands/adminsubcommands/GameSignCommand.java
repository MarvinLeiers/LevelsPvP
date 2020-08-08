package de.marvinleiers.levelspvp.commands.adminsubcommands;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.commands.Subcommand;
import de.marvinleiers.levelspvp.utils.Messages;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.Set;

public class GameSignCommand extends Subcommand
{
    @Override
    public String getName()
    {
        return "sign";
    }

    @Override
    public String getDescription()
    {
        return "Set a join-sign for the game.";
    }

    @Override
    public String getSyntax()
    {
        return "/pvpadmin sign <game>";
    }

    @Override
    public void execute(Player player, String[] args)
    {
        Set<Material> nullMap = null;
        Block block = player.getTargetBlock(nullMap, 50);

        if (args.length != 2)
        {
            player.sendMessage("§cUsage: " + getSyntax());
            return;
        }

        if (!block.getType().toString().contains("SIGN"))
        {
            player.sendMessage(Messages.get("error-no-sign-found"));
            return;
        }

        Sign sign = (Sign) block.getState();
        Game game = LevelsPvP.getPlugin().getApi().getGame(args[1]);
        game.addSign("LevelsPvP", sign.getLocation());
    }
}
