package games.indigo.skooblock.guis;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.warps.IslandWarp;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class WarpMenu {

    SkooBlock skooBlock = SkooBlock.getInstance();

    public void open(Player player, int page) {
        Inventory inv = Bukkit.createInventory(null, 54, skooBlock.getUtils().format("&3&lWarp Menu §8(Page " + (page + 1) + ")"));

        Bukkit.getScheduler().runTaskAsynchronously(skooBlock, new Runnable() {
            @Override
            public void run() {
                int slot = 0;
                int itemIndex = 45 * page;

                int loop = 0;
                for (IslandWarp islandWarp : skooBlock.getWarpsManager().getAllWarps()) {
                    if (loop >= itemIndex && slot < 45) {
                        inv.setItem(slot, setItem(islandWarp));
                        slot++;
                    }
                    loop++;
                }
            }
        });

        setFooter(inv);

        player.setMetadata("islandWarpsPage", new FixedMetadataValue(skooBlock, page));
        player.openInventory(inv);
    }

    private ItemStack setItem(IslandWarp islandWarp) {
        return skooBlock.getUtils().buildWarpHead(UUID.fromString(islandWarp.getOwnerUuid()), setLore(islandWarp.getDescription()));
    }

    private List<String> setLore(String description) {

        List<String> lore = new ArrayList<>();
        lore.add("");

        if (description != null) {
            String[] loreLines = WordUtils.wrap(description, 48).split("\n");

            for (String loreLine : loreLines) {
                lore.add(skooBlock.getUtils().format(" &7" + loreLine));
            }

            lore.add("");
        }

        lore.add(skooBlock.getUtils().format("&e&l(!) &7Click to teleport to this island!"));

        return lore;
    }

    private void setFooter(Inventory inv) {
        ItemStack create = skooBlock.getUtils().buildItem(Material.SIGN, 1, "&6&lCreate Warp", Arrays.asList("&7Click to update your warp location!"));
        ItemStack previous = skooBlock.getUtils().buildItem(Material.ARROW, 1, "&b&lPrevious Page", Arrays.asList("&7Click to go to the previous page!"));
        ItemStack refresh = skooBlock.getUtils().buildItem(Material.SUNFLOWER, 1, "&e&lRefresh", Arrays.asList("&7Click to refresh the warps list!"));
        ItemStack next = skooBlock.getUtils().buildItem(Material.ARROW, 1, "&b&lNext Page", Arrays.asList("&7Click to go to the next page!"));
        ItemStack wot = skooBlock.getUtils().buildItem(Material.KNOWLEDGE_BOOK, 1, "&2&lWhat Is This?", Arrays.asList("&7Here you can view other players &3warps", "&7and &6create your own&7! These warps will", "&7take you somewhere on another players", "&bisland! &7Useful for &aAFKing &7or &dshops&7!"));

        inv.setItem(45, create);
        inv.setItem(48, previous);
        inv.setItem(49, refresh);
        inv.setItem(50, next);
        inv.setItem(53, wot);
    }

}