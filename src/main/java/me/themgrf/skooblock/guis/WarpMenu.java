package me.themgrf.skooblock.guis;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.warps.IslandWarp;
import me.themgrf.skooblock.utils.ItemBuilder;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WarpMenu {

    private final static Main main = Main.getInstance();
    private final static SkooBlock skooBlock = SkooBlock.getInstance();

    public static void open(Player player, int page) {
        Inventory inv = Bukkit.createInventory(null, 54, Text.colour("&3&lWarp Menu §8(Page " + (page + 1) + ")"));

        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
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
        });

        setFooter(inv);
        
        SoundManager.click(player);
        player.setMetadata("islandWarpsPage", new FixedMetadataValue(main, page));
        player.openInventory(inv);
    }

    private static ItemStack setItem(IslandWarp islandWarp) {
        return ItemBuilder.Skulls.buildWarpHead(UUID.fromString(islandWarp.getOwnerUuid()), setLore(islandWarp.getDescription()));
    }

    private static List<String> setLore(String description) {

        List<String> lore = new ArrayList<>();
        lore.add("");

        if (description != null) {
            String[] loreLines = WordUtils.wrap(description, 48).split("\n");

            for (String loreLine : loreLines) {
                lore.add(Text.colour(" &7" + loreLine));
            }

            lore.add("");
        }

        lore.add(Text.colour("&e&l(!) &7Click to teleport to this island!"));

        return lore;
    }

    private static void setFooter(Inventory inv) {
        ItemBuilder itemBuilder = new ItemBuilder();

        ItemStack create = itemBuilder
                .material(Material.OAK_SIGN)
                .name("&6&lCreate Warp")
                .lore("&7Click to update your warp location!")
                .build();

        ItemStack previous = itemBuilder
                .material(Material.ARROW)
                .name("&b&lPrevious Page")
                .lore("&7Click to go to the previous page!")
                .build();

        ItemStack refresh = itemBuilder
                .material(Material.SUNFLOWER)
                .name("&e&lRefresh")
                .lore("&7Click to refresh the warps list!")
                .build();

        ItemStack next = itemBuilder.material(Material.ARROW).name("&b&lNext Page").lore("&7Click to go to the next page!").build();

        ItemStack wot = itemBuilder
                .material(Material.KNOWLEDGE_BOOK)
                .name("&2&lWhat Is This?")
                .lore("&7Here you can view other players &3warps",
                        "&7and &6create your own&7! These warps will",
                        "&7take you somewhere on another players",
                        "&bisland! &7Useful for &aAFKing &7or &dshops&7!")
                .build();

        inv.setItem(45, create);
        inv.setItem(48, previous);
        inv.setItem(49, refresh);
        inv.setItem(50, next);
        inv.setItem(53, wot);
    }

}
