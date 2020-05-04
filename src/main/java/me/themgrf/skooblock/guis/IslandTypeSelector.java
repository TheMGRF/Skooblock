package me.themgrf.skooblock.guis;

import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.Island;
import me.themgrf.skooblock.utils.ItemBuilder;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class IslandTypeSelector {

    private final static SkooBlock skooBlock = SkooBlock.getInstance();
    private final static ItemBuilder itemBuilder = new ItemBuilder();

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, Text.colour("&a&lIsland Creator"));

        int loop = 0;
        for (Island island : skooBlock.getIslandTypes()) {
            inv.setItem(loop, setItem(island.getIcon(), 1, island.getFriendlyName(), island.getDescription(), island.getCostIdentifier(), island.getCost()));
            loop++;
        }

        SoundManager.click(player);
        player.openInventory(inv);
    }

    private static ItemStack setItem(Material material, int amount, String name, String desc, String costType, int cost) {
        String costValue = "  &a&lFREE";
        if (!costType.equals("") && cost > 0) {
            costValue = "  " + costType + cost;
        }

        return itemBuilder
                .material(material)
                .name(name)
                .lore("",
                        " &e&lDESCRIPTION",
                        "  &7" + desc + "!",
                        "",
                        " &e&lCOST",
                        costValue,
                        "",
                        "&e&l(!) &7Click to start off with the " + name + " &7island!")
                .build();
    }

}
