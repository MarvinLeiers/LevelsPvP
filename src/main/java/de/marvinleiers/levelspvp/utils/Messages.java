package de.marvinleiers.levelspvp.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Messages
{
    static FileConfiguration config;
    static Plugin plugin;

    public static String get(String path)
    {
        return path.equalsIgnoreCase("prefix") ? get(path, false) : get(path, true);
    }

    public static String get(String path, boolean prefix)
    {
        return ChatColor.translateAlternateColorCodes('&', prefix ? config.getString("prefix") + " " + config.getString(path) : config.getString(path));
    }

    public static void setUp(Plugin plugin)
    {
        Messages.plugin = plugin;
        Messages.config = plugin.getConfig();

        config.options().copyDefaults(true);

        config.addDefault("prefix", "&b&l[LevelsPvP]&r");
        config.addDefault("entered-barrier-mode", "&aYou are now in barrier mode.");
        config.addDefault("left-barrier-mode", "&cYou left the barrier mode.");
        config.addDefault("saved-loc1", "&aSaved location 1 for barrier.");
        config.addDefault("saved-loc2", "&aBarrier has been saved.");

        config.addDefault("game-entry-point-set", "&aEntry point set for game &2%game%");
        config.addDefault("game-set-team-zone", "&7Set team zone for team %team%");
        config.addDefault("game-joined-team", "&7You joined team %team%");

        config.addDefault("error-no-sign-found", "&c&lFor this operation you have to look at a sign!");

        saveConfig();
    }

    private static void saveConfig()
    {
        plugin.saveConfig();
    }
}
