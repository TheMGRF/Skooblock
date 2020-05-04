package me.themgrf.skooblock.listeners;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.guis.IslandLevelMenu;
import me.themgrf.skooblock.guis.IslandTopMenu;
import me.themgrf.skooblock.guis.WarpMenu;
import me.themgrf.skooblock.island.biomes.IslandBiome;
import me.themgrf.skooblock.island.UserIsland;
import me.themgrf.skooblock.island.members.IslandMember;
import me.themgrf.skooblock.island.warps.IslandWarp;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import me.themgrf.skooblock.utils.Utils;
import me.themgrf.skooblock.island.Island;
import net.darkscorner.darkscooldown.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InventoryClickListener implements Listener {

    private final Main main = Main.getInstance();
    private final SkooBlock skooBlock = SkooBlock.getInstance();
    private final Utils utils = skooBlock.getUtils();

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Text.colour("&6&lIsland Menu"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    Player player = (Player) e.getWhoClicked();

                    // TODO: Adding clicking sounds -- SoundManager.click(player);

                    String itemName = item.getItemMeta().getDisplayName();
                    if (itemName.equals(Text.colour("&c&lHome"))) {
                        Bukkit.dispatchCommand(player, "is home");
                    } else if (itemName.equals(Text.colour("&2&lBiome"))) {
                        Bukkit.dispatchCommand(player, "is biome");
                    } else if (itemName.equals(Text.colour("&6&lLevel"))) {
                        Cooldown cooldown = Cooldown.getCooldown(player, "island-level");
                        if (player.hasPermission("indigo.command.islevel.bypass") || cooldown == null || cooldown.isExpired()) {
                            IslandLevelMenu.open(player, 0);
                            new Cooldown(player, "island-level", 300);
                        } else {
                            SoundManager.error(player);
                            player.sendMessage(Text.colour("&4&l(!) &cIsland level is still on cooldown! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                        }
                    } else if (itemName.equals(Text.colour("&9&lWarps"))) {
                        Bukkit.dispatchCommand(player, "is warps");
                    } else if (itemName.equals(Text.colour("&b&lSomething"))) {
                        Bukkit.dispatchCommand(player, "idek");
                    } else if (itemName.equals(Text.colour("&4&lSettings"))) {
                        Bukkit.dispatchCommand(player, "is settings");
                    } else if (itemName.equals(Text.colour("&a&lMembers"))) {
                        Bukkit.dispatchCommand(player, "is members");
                    } else if (itemName.equals(Text.colour("&e&lChallenges"))) {
                        Bukkit.dispatchCommand(player, "is challenges");
                    } else if (itemName.equals(Text.colour("&d&lTop Islands"))) {
                        IslandTopMenu.open(player);
                    }
                }
            }

        } else if (e.getView().getTitle().equals(Text.colour("&a&lIsland Creator"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    for (Island island : skooBlock.getIslandTypes()) {
                        if (Text.colour(island.getFriendlyName()).equals(item.getItemMeta().getDisplayName())) {
                            if (skooBlock.getIslandGenerator().getIslandsBeingCreated() <= 5) {
                                if (skooBlock.getIslandManager().isIslandResetting(player)) {
                                    player.teleport(SkooBlock.getSpawnLocation());
                                    SoundManager.success(player);
                                    skooBlock.getIslandManager().resetIsland(player);
                                    player.sendMessage(Text.colour("&6&l(!) &eResetting island..."));

                                    Bukkit.getScheduler().runTaskLater(main, () -> skooBlock.getIslandGenerator().generateNewIsland(player, island.getIslandType().getValue()), 20 * 3);

                                    Bukkit.getScheduler().runTaskLater(main, () -> {
                                        UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
                                        player.teleport(utils.getLocationAsBukkitLocation(userIsland.getCentre()));


                                        new Cooldown(player, "islandGen", 120);

                                        player.sendMessage(Text.colour("&2&l(!) &aIsland reset!"));
                                        player.removeMetadata("resettingIsland", main);
                                    }, 20 * 6);
                                    break;
                                } else {
                                    SoundManager.success(player);
                                    skooBlock.getIslandGenerator().generateNewIsland(player, island.getIslandType().getValue());
                                    new Cooldown(player, "islandGen", 120);
                                    break;
                                }
                            } else {
                                player.closeInventory();
                                player.sendMessage(Text.colour("&4&l(!) Sorry, too many islands are being created at the moment! Try again in a moment!"));
                                SoundManager.error(player);
                            }
                        }
                    }
                }
            }
        } else if (e.getView().getTitle().equals(Text.colour("&2&lBiome Selector"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    if (skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
                        for (IslandBiome islandBiome : skooBlock.getIslandBiomes()) {
                            if (Text.colour(islandBiome.getFriendlyName()).equals(item.getItemMeta().getDisplayName())) {
                                UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
                                if (!skooBlock.getBiomeManager().getIslandBiome(userIsland).getFriendlyName().equals(item.getItemMeta().getDisplayName())) {
                                    SoundManager.success(player);
                                    player.closeInventory();
                                    player.sendMessage(Text.colour("&6&l(!) &eUpdating island biome..."));
                                    skooBlock.getBiomeManager().setIslandBiome(userIsland, islandBiome);
                                    player.sendMessage(Text.colour("&2&l(!) &aIsland biome changed to " + islandBiome.getFriendlyName()));
                                } else {
                                    SoundManager.error(player);
                                }
                            }
                        }
                    } else {
                        player.closeInventory();
                        SoundManager.error(player);
                        player.sendMessage(Text.colour("&4&l(!) &cYou do not have an island! Use &7/is create &cto create one!"));
                    }
                }
            }
        } else if (e.getView().getTitle().contains(Text.colour("&3&lWarp Menu"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    String itemName = item.getItemMeta().getDisplayName();
                    int page = player.getMetadata("islandWarpsPage").get(0).asInt();

                    if (itemName.equals(Text.colour("&6&lCreate Warp"))) {
                        Bukkit.dispatchCommand(player, "is setwarp");
                        player.closeInventory();
                        SoundManager.click(player);
                        return;
                    } else if (itemName.equals(Text.colour("&b&lPrevious Page"))) {
                        if (page > 0) {
                            WarpMenu.open(player, page - 1);
                        } else {
                            SoundManager.click(player);
                            SoundManager.error(player);
                        }
                        return;
                    } else if (itemName.equals(Text.colour("&e&lRefresh"))) {
                        WarpMenu.open(player, 0);
                        SoundManager.click(player);
                        return;
                    } else if (itemName.equals(Text.colour("&b&lNext Page"))) {
                        if (e.getInventory().getItem(44) != null) {
                            WarpMenu.open(player, page + 1);
                        } else {
                            SoundManager.click(player);
                            SoundManager.error(player);
                        }
                        return;
                    }

                    for (IslandWarp islandWarp : skooBlock.getWarpsManager().getAllWarps()) {
                        String name = Bukkit.getOfflinePlayer(UUID.fromString(islandWarp.getOwnerUuid())).getName();
                        if (itemName.contains(Text.colour(name))) {
                            player.teleport(islandWarp.getLocation());
                            SoundManager.success(player);
                            player.sendMessage(Text.colour("&2&l(!) &aTeleported to &e" + name + "&a's island!"));
                            return;
                        }
                    }
                }
            }
        } else if (e.getView().getTitle().equals(Text.colour("&a&lIsland Members"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    String itemName = item.getItemMeta().getDisplayName();
                    String memberName = ChatColor.stripColor(itemName);

                    for (IslandMember islandMember : skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString()).getMembers()) {
                        if (Bukkit.getOfflinePlayer(UUID.fromString(islandMember.getUuid())).equals(memberName)) {
                            // TODO: Check clicks and if user has permission to promote, demote and kick.
                            return;
                        }
                    }
                }
            }
        } else if (e.getView().getTitle().contains(Text.colour("&6&lIsland Level"))) {
            e.setCancelled(true);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    String itemName = item.getItemMeta().getDisplayName();
                    int page = player.getMetadata("islandLevelPage").get(0).asInt();

                    if (itemName.equals(Text.colour("&b&lPrevious Page"))) {
                        if (page > 0) {
                            IslandLevelMenu.open(player, page + 1);
                        } else {
                            SoundManager.click(player);
                            SoundManager.error(player);
                        }
                    } else if (itemName.equals(Text.colour("&b&lNext Page"))) {
                        if (e.getInventory().getItem(44) != null) {
                            IslandLevelMenu.open(player, page - 1);
                        } else {
                            SoundManager.click(player);
                            SoundManager.error(player);
                        }
                    } else {
                        SoundManager.click(player);
                    }
                }
            }
        }
    }

}
