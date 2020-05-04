package me.themgrf.skooblock.listeners;

import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!SkooBlock.getInstance().getIslandManager().isPlayerOnHomeIsland(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Text.colour("&4&l(!) &cYou can only break blocks on your own island!"));
        }
    }

}
