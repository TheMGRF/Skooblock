package games.indigo.skooblock.listeners;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.events.IslandEnterEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class IslandEnterListener implements Listener {

    @EventHandler
    public void onIslandEnter(IslandEnterEvent e) {
        e.getPlayer().sendMessage(SkooBlock.getInstance().getUtils().format("&aNow entering " + Bukkit.getOfflinePlayer(UUID.fromString(e.getUserIsland().getOwner())).getName() + "'s island!"));
    }

}
