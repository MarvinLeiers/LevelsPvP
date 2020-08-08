package de.marvinleiers.levelspvp.utils;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.levelspvp.LevelsPvP;
import org.bukkit.entity.Player;

public class SelectionSession
{
    private Player player;
    private Game game;
    private int layer;

    public SelectionSession(Player player, Game game)
    {
        this.player = player;
        this.game = game;
        this.layer = layer;

        LevelsPvP.getPlugin().selectionSessions.put(player, this);
    }

    public void setLayer(int layer)
    {
        this.layer = layer;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Game getGame()
    {
        return game;
    }

    public int getLayer()
    {
        return layer;
    }
}
