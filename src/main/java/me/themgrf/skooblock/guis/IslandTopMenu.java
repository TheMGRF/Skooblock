package me.themgrf.skooblock.guis;

import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.UserIsland;
import me.themgrf.skooblock.utils.ItemBuilder;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class IslandTopMenu {

    private final static SkooBlock skooBlock = SkooBlock.getInstance();

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, Text.colour("&d&lTop Islands"));

        int[] slots = {4, 12, 14, 20, 24, 28, 34, 36, 44, 49};
        int loop = 0;
        for (UserIsland userIsland : skooBlock.getIslandManager().getTopIslands(10)) {
            UUID uuid = UUID.fromString(userIsland.getOwner());
            inv.setItem(slots[loop], new ItemBuilder()
                    .itemStack(ItemBuilder.Skulls.buildTopHead(uuid))
                    .name("&7&l(" + (loop + 1) + ") &6&n" + Bukkit.getOfflinePlayer(uuid).getName() + "'s Island")
                    .lore("",
                            " &6Island Level: &a" + userIsland.getLevel(),
                            "",
                            " &6Place: &f" + (loop + 1),
                            " &6Members: &f",
                            "",
                            "&e&l(!) &7Left click to warp",
                            "&e&l(!) &7Right click to view stuff")
                    .build()
            );
            loop++;
        }

        SoundManager.click(player);
        player.openInventory(inv);
    }

}
