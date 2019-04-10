package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    Main main = Main.getInstance();

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getInventory().getName().equals(main.getUtils().format("&6Island Menu"))) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                player.sendMessage("rawr");
            }

        }
    }

}
