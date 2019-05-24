package games.indigo.skooblock.guis;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.UserIsland;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.UUID;

public class IslandTopMenu {

    private SkooBlock skooBlock = SkooBlock.getInstance();

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, skooBlock.getUtils().format("&d&lTop Islands"));

        int[] slots = {4, 12, 14, 20, 24, 28, 34, 36, 44, 49};
        int loop = 0;
        for (UserIsland userIsland : skooBlock.getIslandManager().getTopIslands(10)) {
            UUID uuid = UUID.fromString(userIsland.getOwner());
            inv.setItem(slots[loop], skooBlock.getUtils().buildItem(skooBlock.getUtils().buildTopHead(uuid), "&7&l(" + (loop + 1) + ") &6&n" + Bukkit.getOfflinePlayer(uuid).getName() + "'s Island", Arrays.asList("", " &6Island Level: &a" + userIsland.getLevel(), "", " &6Place: &f" + (loop + 1), " &6Members: &f", "", "&e&l(!) &7Left click to warp", "&e&l(!) &7Right click to view stuff")));
            loop++;
        }

        skooBlock.getSoundsManager().click(player);
        player.openInventory(inv);
    }

}
