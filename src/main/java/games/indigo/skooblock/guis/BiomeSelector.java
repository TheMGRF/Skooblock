package games.indigo.skooblock.guis;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.biomes.IslandBiome;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BiomeSelector {

    SkooBlock skooBlock = SkooBlock.getInstance();

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, skooBlock.getUtils().format("&2&lBiome Selector"));

        int loop = 0;
        for (IslandBiome biome : skooBlock.getIslandBiomes()) {
            if (skooBlock.getBiomeManager().getIslandBiome(skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString())).getIdentifier().equals(biome.getIdentifier())) {
                inv.setItem(loop, setCurrentItem(biome.getIcon(), 1, biome.getFriendlyName()));
            } else {
                inv.setItem(loop, setItem(biome.getIcon(), 1, biome.getFriendlyName(), biome.getDescription(), biome.getCostIdentifier(), biome.getCost()));
            }
            loop++;
        }

        skooBlock.getSoundsManager().click(player);
        player.openInventory(inv);
    }

    private ItemStack setItem(Material material, int amount, String name, String desc, String costType, int cost) {
        if (costType.equals("") && cost == 0) {
            return skooBlock.getUtils().buildItem(material, amount, name, Arrays.asList("", " &e&lDESCRIPTION", "  &7" + desc + "!", "", " &e&lCOST", "  &a&lFREE", "", "&e&l(!) &7Click to select the " + name + " &7biome!"));
        } else {
            return skooBlock.getUtils().buildItem(material, amount, name, Arrays.asList("", " &e&lDESCRIPTION", "  &7" + desc + "!", "", " &e&lCOST", "  " + costType + cost, "", "&e&l(!) &7Click to select the " + name + " &7biome!"));
        }
    }

    private ItemStack setCurrentItem(Material material, int amount, String name) {
        ItemStack item = skooBlock.getUtils().buildItem(material, amount, name + " &c&l(CURRENT)", Arrays.asList("", "&a&l(!) &7This is your currently active biome!"));
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(itemMeta);
        return item;
    }

}
