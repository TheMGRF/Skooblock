package games.indigo.skooblock.listeners;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.events.IslandEnterEvent;
import games.indigo.skooblock.events.IslandLeaveEvent;
import games.indigo.skooblock.island.UserIsland;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getFrom()) != null) {
            UserIsland userIsland = SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getFrom());
            Player player = e.getPlayer();
            if (SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getTo()) != null) {
                if (!SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getTo()).getOwner().equals(userIsland.getOwner())) {
                    Bukkit.getPluginManager().callEvent(new IslandLeaveEvent(player, userIsland));
                }
            } else {
                Bukkit.getPluginManager().callEvent(new IslandLeaveEvent(player, userIsland));
            }
        }

        if (SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getTo()) != null) {
            UserIsland userIsland = SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getTo());
            Player player = e.getPlayer();
            if (SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getFrom()) != null) {
                if (!SkooBlock.getInstance().getIslandManager().getUserIslandAtLocation(e.getFrom()).getOwner().equals(userIsland.getOwner())) {
                    Bukkit.getPluginManager().callEvent(new IslandEnterEvent(player, userIsland));
                }
            } else {
                Bukkit.getPluginManager().callEvent(new IslandEnterEvent(player, userIsland));
            }
        }
    }

}
