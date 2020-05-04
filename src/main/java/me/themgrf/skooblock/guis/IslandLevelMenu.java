package me.themgrf.skooblock.guis;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.UserIsland;
import me.themgrf.skooblock.utils.ItemBuilder;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.IOException;
import java.util.HashMap;

public class IslandLevelMenu {

    private final static Main main = Main.getInstance();
    private final static SkooBlock skooBlock = SkooBlock.getInstance();
    private final static ItemBuilder itemBuilder = new ItemBuilder();

    public static void open(Player player, int page) {
        UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
        if (!skooBlock.getIslandManager().playerHasIsland(player.getName()) || skooBlock.getIslandManager().getMemberIsland(player.getName()) != null) {
            userIsland = skooBlock.getIslandManager().getMemberIsland(player.getName());
        }

        if (userIsland != null) {
            Inventory inv = Bukkit.createInventory(null, 54, Text.colour("&6&lIsland Level &8(Page " + (page + 1) + ")"));

            final int level = skooBlock.getIslandManager().calculateIslandLevel(userIsland);

            inv.setItem(3, itemBuilder
                    .material(Material.ARROW)
                    .name("&b&lPrevious Page")
                    .lore("&7Click to go to the previous page!")
                    .build()
            );
            inv.setItem(4, itemBuilder
                    .material(Material.OAK_FENCE_GATE)
                    .name("&6&l&nIsland Level")
                    .lore("",
                            " &7Level: &e" + level,
                            " &7Total Points: &e" + skooBlock.getIslandManager().calculateIslandPoints(userIsland))
                    .build()
            );
            inv.setItem(5, itemBuilder
                    .material(Material.ARROW)
                    .name("&b&lNext Page")
                    .lore("&7Click to go to the next page!")
                    .build()
            );

            int slot;
            for (slot = 9; slot < 18; slot++) {
                inv.setItem(slot, itemBuilder.material(Material.GRAY_STAINED_GLASS_PANE).name(" ").build());
            }

            HashMap<Material, Integer> blocks = new HashMap<>();
            for (Block block : skooBlock.getIslandManager().getBlocksOnIsland(userIsland)) {
                if (blocks.containsKey(block.getType())) {
                    blocks.replace(block.getType(), blocks.get(block.getType()).intValue() + 1);
                } else {
                    blocks.put(block.getType(), 1);
                }
            }

            slot = 18;
            for (Material key : blocks.keySet()) {
                if (skooBlock.getBlockLevelIndex().getBlockWorth(key) > 0) {
                    int itemIndex = 54 * page;
                    int loop = 0;

                    if (loop >= itemIndex && slot < 54) {
                        int total = blocks.get(key).intValue();
                        inv.setItem(slot, itemBuilder
                                .material(key)
                                .name("&e&n" + Text.convertUnformattedString(key.name()))
                                .lore("",
                                        " &7Amount: &e" + total,
                                        " &7Total Points: &e" + (skooBlock.getBlockLevelIndex().getBlockWorth(key) * total))
                                .build()
                        );
                        slot++;
                    }
                    loop++;
                }
            }

            main.getConfigManager().getUserConfig(userIsland.getOwner()).set("level", level);
            player.sendMessage("old level: " + main.getConfigManager().getUserConfig(userIsland.getOwner()).get("level"));
            player.sendMessage("Config Man: " + main.getConfigManager());
            player.sendMessage("Owner: " + userIsland.getOwner());
            player.sendMessage("User conf: " + main.getConfigManager().getUserConfig(userIsland.getOwner()));
            player.sendMessage("level: " + level);
            try {
                main.getConfigManager().getUserConfig(userIsland.getOwner()).save(main.getConfigManager().getUserFile(userIsland.getOwner()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            SoundManager.click(player);
            player.setMetadata("islandLevelPage", new FixedMetadataValue(main, page));
            player.openInventory(inv);
        }
    }

}
