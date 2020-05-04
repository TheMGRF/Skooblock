package me.themgrf.skooblock.listeners;

import me.themgrf.skooblock.events.IslandLeaveEvent;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class IslandLeaveListener implements Listener {

    @EventHandler
    public void onIslandEnter(IslandLeaveEvent e) {
        e.getPlayer().sendMessage(Text.colour("&cNow leaving " + Bukkit.getOfflinePlayer(UUID.fromString(e.getUserIsland().getOwner())).getName() + "'s island!"));
    }
}
