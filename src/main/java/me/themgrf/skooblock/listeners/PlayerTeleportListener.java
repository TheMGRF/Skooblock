package me.themgrf.skooblock.listeners;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {

    private final SkooBlock skooBlock = SkooBlock.getInstance();

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player player = e.getPlayer();

        // TODO: Add this in for release
        // if (player.hasPermission("skooblock.border.bypass")) {
        //     return;
        // }
        if (skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> skooBlock.getWorldBorderManager().applyBorder(player), 1);
        }
    }
}
