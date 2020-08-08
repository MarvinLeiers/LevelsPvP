package de.marvinleiers.levelspvp.commands.adminsubcommands;

import de.marvinleiers.gameapi.GameAPI;
import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.commands.Subcommand;
import de.marvinleiers.levelspvp.menus.SetTeamSelectorZoneMenu;
import de.marvinleiers.menuapi.MenuAPI;
import org.bukkit.entity.Player;

public class SetTeamSelectorCommand extends Subcommand
{
    @Override
    public String getName()
    {
        return "team";
    }

    @Override
    public String getDescription()
    {
        return "Set the selection zone for a team.";
    }

    @Override
    public String getSyntax()
    {
        return "/pvpadmin team <game>";
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

        new SetTeamSelectorZoneMenu(game, MenuAPI.getMenuUserInformation(player)).open();
    }
}
