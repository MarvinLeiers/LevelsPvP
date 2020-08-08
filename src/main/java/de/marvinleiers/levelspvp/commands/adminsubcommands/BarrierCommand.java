package de.marvinleiers.levelspvp.commands.adminsubcommands;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.commands.Subcommand;
import de.marvinleiers.levelspvp.menus.BarrierSelectMenu;
import de.marvinleiers.levelspvp.utils.Messages;
import de.marvinleiers.levelspvp.utils.SelectionSession;
import de.marvinleiers.menuapi.MenuAPI;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BarrierCommand extends Subcommand
{
    public static HashMap<Player, Integer> selected = new HashMap<>();

    @Override
    public String getName()
    {
        return "barrier";
    }

    @Override
    public String getDescription()
    {
        return "Right-click with your empty hand to set layer barrier.";
    }

    @Override
    public String getSyntax()
    {
        return "/pvpadmin barrier <game>";
    }

    @Override
    public void execute(Player player, String[] args)
    {
        if (selected.containsKey(player))
        {
            selected.remove(player);

            player.sendMessage(Messages.get("left-barrier-mode"));
            return;
        }

        if (args.length != 2)
        {
            player.sendMessage("§cUsage: " + getSyntax());
            return;
        }

        Game game = LevelsPvP.getApi().getGame(args[1]);

        new SelectionSession(player, game);
        new BarrierSelectMenu(MenuAPI.getMenuUserInformation(player)).open();
    }
}
