package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.UserIsland;
import games.indigo.skooblock.utils.Utils;
import games.indigo.skooblock.island.Island;
import net.darkscorner.darkscooldown.Cooldown;
import org.bukkit.Bukkit;
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
                            if (main.getIslandGenerator().getIslandsBeingCreated() <= 5) {
                                if (SkooBlock.isIslandResetting(player)) {
                                    player.teleport(SkooBlock.getSpawnLocation());
                                    SkooBlock.resetIsland(player);
                                    player.sendMessage(utils.format("&6&l(!) &eResetting island..."));
                                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                        @Override
                                        public void run() {
                                            UserIsland userIsland = SkooBlock.getPlayerIsland(player);
                                            player.teleport(utils.getLocationAsBukkitLocation(userIsland.getCentre()));

                                            main.getIslandGenerator().generateNewIsland(player, island.getIslandType().getValue());
                                            new Cooldown(player, "islandGen", 120);

                                            player.sendMessage(utils.format("&2&l(!) &aIsland reset!"));
                                            player.removeMetadata("resettingIsland", Main.getInstance());
                                        }
                                    }, 20 * 6);
                                    break;
                                } else {
                                    main.getIslandGenerator().generateNewIsland(player, island.getIslandType().getValue());
                                    new Cooldown(player, "islandGen", 120);
                                    break;
                                }
                            } else {
                                player.closeInventory();
                                player.sendMessage(main.getUtils().format("&4&l(!) Sorry, too many islands are being created at the moment! Try again in a moment!"));
                            }
                        }
                    }
                }
            }
        }
    }

}
