package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.island.UserIsland;
import games.indigo.skooblock.utils.WorldBorderManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private Main main = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (main.getIslandManager().playerHasIsland(player)) {
            if (main.getIslandManager().isPlayerOnHomeIsland(player)) {
                UserIsland userIsland = main.getIslandManager().getPlayerIsland(player);
                Location centre = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre());

                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        main.getWorldBorderManager().applyBorder(player);
                    }
                }, 1);

                Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
                    @Override
                    public void run() {
                        String name  = Bukkit.getOfflinePlayer(UUID.fromString(userIsland.getOwner())).getName();
                        int level = 0;
                        String action = "&b&lIsland: &e" + name + " &8| &b&lLevel: &e" + level;
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(main.getUtils().format(action)));
                    }
                }, 0, 1);
            }
        }
    }

}
