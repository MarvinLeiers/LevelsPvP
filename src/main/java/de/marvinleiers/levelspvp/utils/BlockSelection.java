package de.marvinleiers.levelspvp.utils;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.levelspvp.LevelsPvP;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BlockSelection
{
    public static void save(Game game, String name, Location loc1, Location loc2)
    {
        LevelsPvP.getApi().gamesConfig.setLocation("games." + game.getName() + ".barriers." + name + ".loc1", loc1);
        LevelsPvP.getApi().gamesConfig.setLocation("games." + game.getName() + ".barriers." + name + ".loc2", loc2);
    }

    public static ArrayList<Block> getRegion(Game game, String name)
    {
        if (!LevelsPvP.getApi().gamesConfig.isSet("games." + game.getName() + ".barriers." + name + ".loc1") || !LevelsPvP.getApi().gamesConfig.isSet("games." + game.getName() + ".barriers." + name + ".loc2"))
            return null;

        ArrayList<Block> blocks = new ArrayList<>();

        Location loc1 = LevelsPvP.getApi().gamesConfig.getLocation("games." + game.getName() + ".barriers." + name + ".loc1");
        Location loc2 = LevelsPvP.getApi().gamesConfig.getLocation("games." + game.getName() + ".barriers." + name + ".loc2");

        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());

        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        for (int x = minX; x <= maxX; x++)
        {
            for (int y = minY; y <= maxY; y++)
            {
                for (int z = minZ; z <= maxZ; z++)
                {
                    blocks.add(loc1.getWorld().getBlockAt(x, y, z));
                    System.out.println("added " + loc1.getWorld().getBlockAt(x, y, z).getLocation());

                    //TODO: add to reset blocks
                }
            }
        }

        return blocks;
    }
}
