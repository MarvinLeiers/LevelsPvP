package de.marvinleiers.levelspvp.utils;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.gameapi.game.GamePlayer;
import de.marvinleiers.levelspvp.listeners.TeamSelectMoveEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Teams
{
    private Game game;
    private Location loc;
    private Team team;
    private ArrayList<GamePlayer> members;

    public Teams(Game game, Location selectionLocation, Team team)
    {
        this.game = game;
        this.loc = selectionLocation;
        this.team = team;

        this.members = new ArrayList<>();
    }

    public void join(GamePlayer gp)
    {
        if (gp.getGame() != game)
            return;

        gp.setTeam(getTeam().toString());

        if (!members.contains(gp))
        {
            for (Teams teams : TeamSelectMoveEvent.teamSelections.get(game))
            {
                if (teams.getMembers().contains(gp))
                    teams.getMembers().remove(gp);
            }

            members.add(gp);
            gp.sendMessage(Messages.get("game-joined-team").replace("%team%", getTeam().getName()));
        }
    }

    public ArrayList<GamePlayer> getMembers()
    {
        return members;
    }

    public Game getGame()
    {
        return game;
    }

    public Location getSelectionLocation()
    {
        return loc;
    }

    public Team getTeam()
    {
        return team;
    }

    public enum Team
    {
        BLUE("§9§lBlue"),
        RED("§c§lRed"),
        YELLOW("§e§lYellow"),
        GREEN("§2§lGreen");

        private String name;

        Team(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
