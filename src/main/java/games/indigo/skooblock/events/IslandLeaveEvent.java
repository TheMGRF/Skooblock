package games.indigo.skooblock.events;

import games.indigo.skooblock.island.UserIsland;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandLeaveEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();

    private Player player;
    private UserIsland userIsland;
    private boolean isCancelled;

    public IslandLeaveEvent(Player player, UserIsland userIsland) {
        this.player = player;
        this.userIsland = userIsland;
    }

    public Player getPlayer() {return player;}

    public UserIsland getUserIsland() { return userIsland; }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public HandlerList getHandlers() { return handlers; }

}
