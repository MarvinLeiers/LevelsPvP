package de.marvinleiers.levelspvp.listeners;

import de.marvinleiers.levelspvp.LevelsPvP;
import de.marvinleiers.levelspvp.commands.adminsubcommands.BarrierCommand;
import de.marvinleiers.levelspvp.utils.BlockSelection;
import de.marvinleiers.levelspvp.utils.Messages;
import de.marvinleiers.levelspvp.utils.SelectionSession;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class BarrierSelectListener implements Listener
{
    public static HashMap<Player, Location> selections = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        if (!event.getAction().toString().contains("BLOCK"))
            return;

        if (!BarrierCommand.selected.containsKey(player))
            return;

        event.setCancelled(true);

        Location loc = event.getClickedBlock().getLocation();

        if (!selections.containsKey(player))
        {

            selections.put(player, loc);
            player.sendMessage(Messages.get("saved-loc1"));
            return;
        }

        Location loc1 = selections.get(player);
        Location loc2 = loc;

        selections.remove(player);

        SelectionSession selectionSession = LevelsPvP.getPlugin().selectionSessions.get(player);
        String name = "layer-" + selectionSession.getLayer();

        BlockSelection.save(selectionSession.getGame(), name, loc1, loc2);

        new BukkitRunnable(){
            @Override
            public void run()
            {
                ArrayList<Block> blocks = BlockSelection.getRegion(selectionSession.getGame(), name);

                if (blocks == null || blocks.isEmpty())
                {
                    System.out.println("BLOCKS IS EMPTY!!!");
                    return;
                }

                for (Block block : blocks)
                {
                    block.setType(Material.OBSIDIAN);
                }
            }
        }.runTaskLater(LevelsPvP.getPlugin(), 20);

        BarrierCommand.selected.remove(player);
        player.sendMessage(Messages.get("saved-loc2"));
    }
}
