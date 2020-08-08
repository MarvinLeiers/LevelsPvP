package de.marvinleiers.levelspvp.menus;

import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.commands.adminsubcommands.BarrierCommand;
import de.marvinleiers.levelspvp.utils.Messages;
import de.marvinleiers.menuapi.Menu;
import de.marvinleiers.menuapi.MenuUserInformation;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BarrierSelectMenu extends Menu
{
    public BarrierSelectMenu(MenuUserInformation menuUserInformation)
    {
        super(menuUserInformation);
    }

    @Override
    public String getTitle()
    {
        return "Select level / layer";
    }

    @Override
    public int getSlots()
    {
        return 9;
    }

    @Override
    public void setItems()
    {
        for (int i = 0; i < 5; i++)
        {
            inventory.addItem(makeItem(Material.OBSIDIAN, "§f§lLayer " + (i + 1)));
        }
    }

    @Override
    public void handleClickActions(InventoryClickEvent inventoryClickEvent)
    {
        int layer = 1;

        ItemStack item = inventoryClickEvent.getCurrentItem();

        if (item.getItemMeta().getDisplayName().contains("2"))
            layer = 2;
        else if (item.getItemMeta().getDisplayName().contains("3"))
            layer = 3;
        else if (item.getItemMeta().getDisplayName().contains("4"))
            layer = 4;
        else if (item.getItemMeta().getDisplayName().contains("5"))
            layer = 5;

        BarrierCommand.selected.put(player, layer);
        LevelsPvP.getPlugin().selectionSessions.get(player).setLayer(layer);

        player.sendMessage(Messages.get("entered-barrier-mode"));
        player.closeInventory();
    }
}
