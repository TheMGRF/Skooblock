package games.indigo.skooblock.guis;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.UserIsland;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class IslandLevelMenu {

    private SkooBlock skooBlock = SkooBlock.getInstance();

    public void open(Player player, int page) {
        UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
        if (skooBlock.getIslandManager().playerHasIsland(player.getName())) {
        } else if (skooBlock.getIslandManager().getMemberIsland(player.getName()) != null) {
            userIsland = skooBlock.getIslandManager().getMemberIsland(player.getName());
        }

        if (userIsland != null) {
            Inventory inv = Bukkit.createInventory(null, 54, skooBlock.getUtils().format("&6&lIsland Level §8(Page " + (page + 1) + ")"));

            final int level = skooBlock.getIslandManager().calculateIslandLevel(userIsland);

            inv.setItem(3, skooBlock.getUtils().buildItem(Material.ARROW, 1, "&b&lPrevious Page", Arrays.asList("&7Click to go to the previous page!")));
            inv.setItem(4, skooBlock.getUtils().buildItem(Material.OAK_FENCE_GATE, 1, "&6&l&nIsland Level", Arrays.asList("", " &7Level: &e" + level, " &7Total Points: &e" + skooBlock.getIslandManager().calculateIslandPoints(userIsland))));
            inv.setItem(5, skooBlock.getUtils().buildItem(Material.ARROW, 1, "&b&lNext Page", Arrays.asList("&7Click to go to the next page!")));

            int slot;
            for (slot = 9; slot < 18; slot++) {
                inv.setItem(slot, skooBlock.getUtils().buildItem(Material.GRAY_STAINED_GLASS_PANE, 1, " ", Arrays.asList("")));
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
                        inv.setItem(slot, skooBlock.getUtils().buildItem(key, 1, "&e&n" + skooBlock.getUtils().convertItemMaterial(key), Arrays.asList("", " &7Amount: &e" + total, " &7Total Points: &e" + (skooBlock.getBlockLevelIndex().getBlockWorth(key) * total))));
                        slot++;
                    }
                    loop++;
                }
            }

            skooBlock.getConfigManager().getUserConfig(userIsland.getOwner()).set("level", level);
            player.sendMessage("old level: " + skooBlock.getConfigManager().getUserConfig(userIsland.getOwner()).get("level"));
            player.sendMessage("Config Man: " + skooBlock.getConfigManager());
            player.sendMessage("Onwer: " + userIsland.getOwner());
            player.sendMessage("User conf: " + skooBlock.getConfigManager().getUserConfig(userIsland.getOwner()));
            player.sendMessage("level: " + level);
            try {
                skooBlock.getConfigManager().getUserConfig(userIsland.getOwner()).save(skooBlock.getConfigManager().getUserFile(userIsland.getOwner()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            skooBlock.getSoundsManager().click(player);
            player.setMetadata("islandLevelPage", new FixedMetadataValue(skooBlock, page));
            player.openInventory(inv);
        }
    }

}
