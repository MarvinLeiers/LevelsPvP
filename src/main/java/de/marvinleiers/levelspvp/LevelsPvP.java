package de.marvinleiers.levelspvp;

import de.marvinleiers.gameapi.GameAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelsPvP extends JavaPlugin
{
    private static GameAPI api;

    @Override
    public void onEnable()
    {
        api = GameAPI.getInstance(this);
    }

    public static LevelsPvP getPlugin()
    {
        return getPlugin(LevelsPvP.class);
    }

    public static GameAPI getApi()
    {
        return api;
    }
}
