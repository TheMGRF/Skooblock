package games.indigo.skooblock.listeners;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.biomes.IslandBiome;
import games.indigo.skooblock.island.UserIsland;
import games.indigo.skooblock.island.warps.IslandWarp;
import games.indigo.skooblock.utils.Utils;
import games.indigo.skooblock.island.Island;
import net.darkscorner.darkscooldown.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InventoryClickListener implements Listener {

    Main main = Main.getInstance();
    Utils utils = main.getUtils();

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getInventory().getName().equals(utils.format("&6&lIsland Menu"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    Player player = (Player) e.getWhoClicked();

                    // TODO: Adding clicking sounds -- main.getSoundsManager().click(player);

                    String itemName = item.getItemMeta().getDisplayName();
                    if (itemName.equals(utils.format("&c&lHome"))) {
                        Bukkit.dispatchCommand(player, "is home");
                    } else if (itemName.equals(utils.format("&2&lBiome"))) {
                        Bukkit.dispatchCommand(player, "is biome");
                    } else if (itemName.equals(utils.format("&6&lLevel"))) {
                        Bukkit.dispatchCommand(player, "is level");
                    } else if (itemName.equals(utils.format("&9&lWarps"))) {
                        Bukkit.dispatchCommand(player, "is warps");
                    } else if (itemName.equals(utils.format("&b&lSomething"))) {
                        Bukkit.dispatchCommand(player, "idek");
                    } else if (itemName.equals(utils.format("&4&lSettings"))) {
                        Bukkit.dispatchCommand(player, "is settings");
                    } else if (itemName.equals(utils.format("&a&lMembers"))) {
                        Bukkit.dispatchCommand(player, "is members");
                    } else if (itemName.equals(utils.format("&e&lChallenges"))) {
                        Bukkit.dispatchCommand(player, "is challenges");
                    } else if (itemName.equals(utils.format("&d&lTop Islands"))) {
                        Bukkit.dispatchCommand(player, "is top");
                    }
                }
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
                                if (main.getIslandManager().isIslandResetting(player)) {
                                    player.teleport(SkooBlock.getSpawnLocation());
                                    main.getSoundsManager().success(player);
                                    main.getIslandManager().resetIsland(player);
                                    player.sendMessage(utils.format("&6&l(!) &eResetting island..."));
                                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                                        @Override
                                        public void run() {
                                            UserIsland userIsland = main.getIslandManager().getPlayerIsland(player);
                                            player.teleport(utils.getLocationAsBukkitLocation(userIsland.getCentre()));

                                            main.getIslandGenerator().generateNewIsland(player, island.getIslandType().getValue());
                                            new Cooldown(player, "islandGen", 120);

                                            player.sendMessage(utils.format("&2&l(!) &aIsland reset!"));
                                            player.removeMetadata("resettingIsland", Main.getInstance());
                                        }
                                    }, 20 * 6);
                                    break;
                                } else {
                                    main.getSoundsManager().success(player);
                                    main.getIslandGenerator().generateNewIsland(player, island.getIslandType().getValue());
                                    new Cooldown(player, "islandGen", 120);
                                    break;
                                }
                            } else {
                                player.closeInventory();
                                player.sendMessage(main.getUtils().format("&4&l(!) Sorry, too many islands are being created at the moment! Try again in a moment!"));
                                main.getSoundsManager().error(player);
                            }
                        }
                    }
                }
            }
        } else if (e.getInventory().getName().equals(utils.format("&2&lBiome Selector"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    if (main.getIslandManager().playerHasIsland(player)) {
                        for (IslandBiome islandBiome : main.getIslandBiomes()) {
                            if (utils.format(islandBiome.getFriendlyName()).equals(item.getItemMeta().getDisplayName())) {
                                UserIsland userIsland = main.getIslandManager().getPlayerIsland(player);
                                if (!main.getBiomeManager().getIslandBiome(userIsland).getFriendlyName().equals(item.getItemMeta().getDisplayName())) {
                                    main.getSoundsManager().success(player);
                                    player.closeInventory();
                                    player.sendMessage(utils.format("&6&l(!) &eUpdating island biome..."));
                                    main.getBiomeManager().setIslandBiome(userIsland, islandBiome);
                                    player.sendMessage(utils.format("&2&l(!) &aIsland biome changed to " + islandBiome.getFriendlyName()));
                                } else {
                                    main.getSoundsManager().error(player);
                                }
                            }
                        }
                    } else {
                        player.closeInventory();
                        main.getSoundsManager().error(player);
                        player.sendMessage(utils.format("&4&l(!) &cYou do not have an island! Use &7/is create &cto create one!"));
                    }
                }
            }
        } else if (e.getInventory().getName().contains(utils.format("&3&lWarp Menu"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    String itemName = item.getItemMeta().getDisplayName();
                    int page = player.getMetadata("islandWarpsPage").get(0).asInt();

                    if (itemName.equals(utils.format("&6&lCreate Warp"))) {
                        Bukkit.dispatchCommand(player, "is setwarp");
                        player.closeInventory();
                        main.getSoundsManager().click(player);
                        return;
                    } else if (itemName.equals(utils.format("&b&lPrevious Page"))) {
                        if (page > 0) {
                            main.getWarpMenu().open(player, page - 1);
                        }
                        main.getSoundsManager().click(player);
                        return;
                    } else if (itemName.equals(utils.format("&e&lRefresh"))) {
                        main.getWarpMenu().open(player, 0);
                        main.getSoundsManager().click(player);
                        return;
                    } else if (itemName.equals(utils.format("&b&lNext Page"))) {
                        if (e.getInventory().getItem(44) != null) {
                            main.getWarpMenu().open(player, page + 1);
                        }
                        main.getSoundsManager().click(player);
                        return;
                    }

                    for (IslandWarp islandWarp : main.getWarpsManager().getAllWarps()) {
                        String name = Bukkit.getOfflinePlayer(UUID.fromString(islandWarp.getOwnerUuid())).getName();
                        if (itemName.contains(utils.format(name))) {
                            player.teleport(islandWarp.getLocation());
                            main.getSoundsManager().success(player);
                            player.sendMessage(utils.format("&2&l(!) &aTeleported to &e" + name + "&a's island!"));
                            return;
                        }
                    }
                }
            }
        }
    }

}
