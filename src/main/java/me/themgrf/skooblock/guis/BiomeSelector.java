package me.themgrf.skooblock.guis;

import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.biomes.IslandBiome;
import me.themgrf.skooblock.utils.ItemBuilder;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BiomeSelector {

    private final static SkooBlock skooBlock = SkooBlock.getInstance();
    private final static ItemBuilder itemBuilder = new ItemBuilder();

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, Text.colour("&2&lBiome Selector"));

        int loop = 0;
        for (IslandBiome biome : skooBlock.getIslandBiomes()) {
            if (skooBlock.getBiomeManager().getIslandBiome(skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString())).getIdentifier().equals(biome.getIdentifier())) {
                inv.setItem(loop, setCurrentItem(biome.getIcon(), biome.getFriendlyName()));
            } else {
                inv.setItem(loop, setItem(biome.getIcon(), biome.getFriendlyName(), biome.getDescription(), biome.getCostIdentifier(), biome.getCost()));
            }
            loop++;
        }

        SoundManager.click(player);
        player.openInventory(inv);
    }

    private static ItemStack setItem(Material material, String name, String desc, String costType, int cost) {
        if (costType.equals("") && cost == 0) {
            return itemBuilder
                    .material(material)
                    .name(name)
                    .lore("",
                            " &e&lDESCRIPTION",
                            "  &7" + desc + "!",
                            "",
                            " &e&lCOST",
                            "  &a&lFREE",
                            "",
                            "&e&l(!) &7Click to select the " + name + " &7biome!")
                    .build();
        } else {
            return itemBuilder
                    .material(material)
                    .name(name)
                    .lore("",
                            " &e&lDESCRIPTION",
                            "  &7" + desc + "!",
                            "",
                            " &e&lCOST",
                            "  " + costType + cost,
                            "",
                            "&e&l(!) &7Click to select the " + name + " &7biome!")
                    .build();
        }
    }

    private static ItemStack setCurrentItem(Material material, String name) {
        ItemStack item = itemBuilder
                .material(material)
                .name(name + " &c&l(CURRENT)")
                .lore("", "&a&l(!) &7This is your currently active biome!")
                .build();
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(itemMeta);
        return item;
    }

}
