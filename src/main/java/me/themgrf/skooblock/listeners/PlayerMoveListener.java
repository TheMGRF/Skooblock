package me.themgrf.skooblock.listeners;

import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.events.IslandEnterEvent;
import me.themgrf.skooblock.events.IslandLeaveEvent;
import me.themgrf.skooblock.island.UserIsland;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final SkooBlock skooBlock = SkooBlock.getInstance();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (skooBlock.getIslandManager().getUserIslandAtLocation(e.getFrom()) != null) {
            UserIsland userIsland = skooBlock.getIslandManager().getUserIslandAtLocation(e.getFrom());
            Player player = e.getPlayer();
            if (skooBlock.getIslandManager().getUserIslandAtLocation(e.getTo()) != null) {
                if (!skooBlock.getIslandManager().getUserIslandAtLocation(e.getTo()).getOwner().equals(userIsland.getOwner())) {
                    Bukkit.getPluginManager().callEvent(new IslandLeaveEvent(player, userIsland));
                }
            } else {
                Bukkit.getPluginManager().callEvent(new IslandLeaveEvent(player, userIsland));
            }
        }

        if (skooBlock.getIslandManager().getUserIslandAtLocation(e.getTo()) != null) {
            UserIsland userIsland = skooBlock.getIslandManager().getUserIslandAtLocation(e.getTo());
            Player player = e.getPlayer();
            if (skooBlock.getIslandManager().getUserIslandAtLocation(e.getFrom()) != null) {
                if (!skooBlock.getIslandManager().getUserIslandAtLocation(e.getFrom()).getOwner().equals(userIsland.getOwner())) {
                    Bukkit.getPluginManager().callEvent(new IslandEnterEvent(player, userIsland));
                }
            } else {
                Bukkit.getPluginManager().callEvent(new IslandEnterEvent(player, userIsland));
            }
        }
    }

}
