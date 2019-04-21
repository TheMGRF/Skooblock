package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {

    private Main main = Main.getInstance();

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player player = e.getPlayer();

        // TODO: Add this in for release
        // if (player.hasPermission("skooblock.border.bypass")) {
        //     return;
        // }
        if (main.getIslandManager().playerHasIsland(player)) {
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    main.getWorldBorderManager().applyBorder(player);
                }
            }, 1);
        }
    }
}
