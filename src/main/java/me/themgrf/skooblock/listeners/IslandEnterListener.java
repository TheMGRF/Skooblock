package me.themgrf.skooblock.listeners;

import me.themgrf.skooblock.events.IslandEnterEvent;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class IslandEnterListener implements Listener {

    @EventHandler
    public void onIslandEnter(IslandEnterEvent e) {
        e.getPlayer().sendMessage(Text.colour("&aNow entering " + Bukkit.getOfflinePlayer(UUID.fromString(e.getUserIsland().getOwner())).getName() + "'s island!"));
    }

}
