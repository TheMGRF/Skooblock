package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.SkooBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!SkooBlock.isPlayerOnIsland(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Main.getInstance().getUtils().format("&4&l(!) &cYou can only place blocks on your own island!"));
        }
    }

}
