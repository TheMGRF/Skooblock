package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.Utils;
import games.indigo.skooblock.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    Main main = Main.getInstance();
    Utils utils = main.getUtils();

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getInventory().getName().equals(utils.format("&6&lIsland Menu"))) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                player.sendMessage("rawr");
            }

        } else if (e.getInventory().getName().equals(utils.format("&a&lIsland Creator"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    for (Island island : main.getIslandTypes()) {
                        if (utils.format(island.getFriendlyName()).equals(item.getItemMeta().getDisplayName())) {
                            // TODO: IslandType.valueOf(island.getIdentifier().toUpperCase()).getValue()
                            main.getIslandGenerator().generateNewIsland(player, island.getIslandType().getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

}
