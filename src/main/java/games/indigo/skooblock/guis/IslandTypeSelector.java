package games.indigo.skooblock.guis;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class IslandTypeSelector {

    SkooBlock skooBlock = SkooBlock.getInstance();

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, skooBlock.getUtils().format("&a&lIsland Creator"));

        int loop = 0;
        for (Island island : skooBlock.getIslandTypes()) {
            inv.setItem(loop, setItem(island.getIcon(), 1, island.getFriendlyName(), island.getDescription(), island.getCostIdentifier(), island.getCost()));
            loop++;
        }

        skooBlock.getSoundsManager().click(player);
        player.openInventory(inv);
    }

    private ItemStack setItem(Material material, int amount, String name, String desc, String costType, int cost) {
        if (costType.equals("") && cost == 0) {
            return skooBlock.getUtils().buildItem(material, amount, name, Arrays.asList("", " &e&lDESCRIPTION", "  &7" + desc + "!", "", " &e&lCOST", "  &a&lFREE", "", "&e&l(!) &7Click to start off with the " + name + " &7island!"));
        } else {
            return skooBlock.getUtils().buildItem(material, amount, name, Arrays.asList("", " &e&lDESCRIPTION", "  &7" + desc + "!", "", " &e&lCOST", "  " + costType + cost, "", "&e&l(!) &7Click to start off with the " + name + " &7island!"));
        }
    }

}
