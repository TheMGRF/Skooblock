package me.themgrf.skooblock.listeners;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.UserIsland;
import me.themgrf.skooblock.utils.Text;
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

    private final Main main = Main.getInstance();
    private final SkooBlock skooBlock = SkooBlock.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
            if (skooBlock.getIslandManager().isPlayerOnHomeIsland(player)) {
                UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
                Location centre = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre());

                Bukkit.getScheduler().runTaskLater(main, () -> skooBlock.getWorldBorderManager().applyBorder(player), 1);

                Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
                    String name  = Bukkit.getOfflinePlayer(UUID.fromString(userIsland.getOwner())).getName();
                    int level = 0;
                    String action = "&b&lIsland: &e" + name + " &8| &b&lLevel: &e" + level;
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Text.colour(action)));
                }, 0, 1);
            }
        }
    }

}
