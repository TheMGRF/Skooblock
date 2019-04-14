package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.UserIsland;
import games.indigo.skooblock.utils.WorldBorderManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (SkooBlock.playerHasIsland(player)) {
            if (SkooBlock.isPlayerOnIsland(player)) {
                UserIsland userIsland = SkooBlock.getPlayerIsland(player);
                Location centre = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre());

                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        new WorldBorderManager().sendBorder(player, centre.getX(), centre.getZ(), Main.getInstance().getIslandGenerator().getIslandSize() / 2);
                    }
                }, 1);
            }
        }
    }

}
