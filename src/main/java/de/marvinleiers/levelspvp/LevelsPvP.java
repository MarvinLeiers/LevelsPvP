package de.marvinleiers.levelspvp;

import de.marvinleiers.gameapi.GameAPI;
import de.marvinleiers.levelspvp.commands.AdminCommand;
import de.marvinleiers.levelspvp.listeners.BarrierSelectListener;
import de.marvinleiers.levelspvp.listeners.GameStartListener;
import de.marvinleiers.levelspvp.listeners.TeamSelectMoveEvent;
import de.marvinleiers.levelspvp.utils.Messages;
import de.marvinleiers.levelspvp.utils.SelectionSession;
import de.marvinleiers.menuapi.MenuAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class LevelsPvP extends JavaPlugin
{
    private static GameAPI api;

    public HashMap<Player, SelectionSession> selectionSessions = new HashMap<>();

    @Override
    public void onEnable()
    {
        api = GameAPI.getInstance(this);

        MenuAPI.setUp(this);
        Messages.setUp(this);

        this.getCommand("pvpadmin").setExecutor(new AdminCommand());

        this.getServer().getPluginManager().registerEvents(new BarrierSelectListener(), this);
        this.getServer().getPluginManager().registerEvents(new TeamSelectMoveEvent(), this);
        this.getServer().getPluginManager().registerEvents(new GameStartListener(), this);
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
