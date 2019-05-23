package games.indigo.skooblock.guis;

import games.indigo.skooblock.SkooBlock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class MainMenu {

    private SkooBlock skooBlock = SkooBlock.getInstance();

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, skooBlock.getUtils().format("&6&lIsland Menu"));

        inv.setItem(4, skooBlock.getUtils().buildItem(Material.RED_BED, 1, "&c&lHome", Arrays.asList("", "&e&l(!) &7Teleport to your island home!")));

        inv.setItem(11, skooBlock.getUtils().buildItem(Material.OAK_SAPLING, 1, "&2&lBiome", Arrays.asList("", "&e&l(!) &7Edit your island biome settings!")));

        inv.setItem(15, skooBlock.getUtils().buildItem(Material.END_PORTAL_FRAME, 1, "&6&lLevel", Arrays.asList("", "&e&l(!) &7View your island level!")));

        inv.setItem(18, skooBlock.getUtils().buildItem(Material.SIGN, 1, "&9&lWarps", Arrays.asList("", "&e&l(!) &7Warp to other player's islands!")));

        inv.setItem(22, skooBlock.getUtils().buildItem(Material.NETHER_STAR, 1, "&b&lShop", Arrays.asList("", "&e&l(!) &7Click to buy extra island gear!")));

        inv.setItem(26, skooBlock.getUtils().buildItem(Material.COMPARATOR, 1, "&4&lSettings &c&l(Coming Soon)", Arrays.asList("", "&e&l(!) &7Edit your island settings!")));

        inv.setItem(29, skooBlock.getUtils().buildItem(Material.PLAYER_HEAD, 1, "&a&lMembers", Arrays.asList("", "&e&l(!) &7Click to edit your island members!")));

        inv.setItem(33, skooBlock.getUtils().buildItem(Material.CHEST, 1, "&e&lChallenges &c&l(Coming Soon)", Arrays.asList("", "&e&l(!) &7Click to view your island challenges!")));

        inv.setItem(40, skooBlock.getUtils().buildItem(Material.ANVIL, 1, "&d&lTop Islands &c&l(Coming Soon)", Arrays.asList("", "&e&l(!) &7Check out the top islands on the server!")));

        skooBlock.getSoundsManager().click(player);
        player.openInventory(inv);
    }

}
