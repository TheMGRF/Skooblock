package games.indigo.skooblock.guis;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class IslandTypeSelector {

    Main main = Main.getInstance();

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, main.getUtils().format("&a&lIsland Creator"));

        int loop = 0;
        for (Island island : main.getIslandTypes()) {
            inv.setItem(loop, setItem(island.getIcon(), 1, island.getFriendlyName(), island.getDescription(), island.getCostIdentifier(), island.getCost()));
            loop++;
        }

        main.getSoundsManager().click(player);
        player.openInventory(inv);
    }

    private ItemStack setItem(Material material, int amount, String name, String desc, String costType, int cost) {
        if (costType.equals("") && cost == 0) {
            return main.getUtils().buildItem(material, amount, name, Arrays.asList("", " &e&lDESCRIPTION", "  &7" + desc + "!", "", " &e&lCOST", "  &a&lFREE", "", "&e&l(!) &7Click to start off with the " + name + " &7island!"));
        } else {
            return main.getUtils().buildItem(material, amount, name, Arrays.asList("", " &e&lDESCRIPTION", "  &7" + desc + "!", "", " &e&lCOST", "  " + costType + cost, "", "&e&l(!) &7Click to start off with the " + name + " &7island!"));
        }
    }

}
