package de.marvinleiers.levelspvp.menus;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.utils.Messages;
import de.marvinleiers.menuapi.Menu;
import de.marvinleiers.menuapi.MenuUserInformation;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SetTeamSelectorZoneMenu extends Menu
{
    private Game game;

    public SetTeamSelectorZoneMenu(Game game, MenuUserInformation menuUserInformation)
    {
        super(menuUserInformation);

        this.game = game;
    }

    @Override
    public String getTitle()
    {
        return "Set team zone";
    }

    @Override
    public int getSlots()
    {
        return 9;
    }

    @Override
    public void setItems()
    {
        ItemStack blue = makeItem(Material.WOOL, "§9§lBlue");
        blue.setDurability((short) 11);

        ItemStack red = makeItem(Material.WOOL, "§c§lRed");
        red.setDurability((short) 14);

        ItemStack yellow = makeItem(Material.WOOL, "§e§lYellow");
        yellow.setDurability((short) 4);

        ItemStack green = makeItem(Material.WOOL, "§2§lGreen");
        green.setDurability((short) 5);

        inventory.addItem(blue);
        inventory.addItem(red);
        inventory.addItem(yellow);
        inventory.addItem(green);
    }

    @Override
    public void handleClickActions(InventoryClickEvent inventoryClickEvent)
    {
        String rawName = inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName();
        String team = ChatColor.stripColor(rawName).toLowerCase();

        LevelsPvP.getApi().gamesConfig.setLocation("games." + game.getName() + ".teams.selector." + team, player.getLocation());

        player.sendMessage(Messages.get("game-set-team-zone").replace("%team%", rawName));
        player.closeInventory();
    }
}
