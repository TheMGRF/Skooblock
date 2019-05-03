package games.indigo.skooblock.listeners;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.events.IslandLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class IslandLeaveListener implements Listener {

    @EventHandler
    public void onIslandEnter(IslandLeaveEvent e) {
        e.getPlayer().sendMessage(SkooBlock.getInstance().getUtils().format("&cNow leaving " + Bukkit.getOfflinePlayer(UUID.fromString(e.getUserIsland().getOwner())).getName() + "'s island!"));
    }
}
