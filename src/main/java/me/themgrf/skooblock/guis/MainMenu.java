package me.themgrf.skooblock.guis;

import me.themgrf.skooblock.utils.ItemBuilder;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainMenu {

    private final static ItemBuilder itemBuilder = new ItemBuilder();

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, Text.colour("&6&lIsland Menu"));

        inv.setItem(4, itemBuilder
                .material(Material.RED_BED)
                .name("&c&lHome")
                .lore("", "&e&l(!) &7Teleport to your island home!")
                .build()
        );

        inv.setItem(11, itemBuilder
                .material(Material.OAK_SAPLING)
                .name("&2&lBiome")
                .lore("", "&e&l(!) &7Edit your island biome settings!")
                .build()
        );

        inv.setItem(15, itemBuilder
                .material(Material.END_PORTAL_FRAME)
                .name("&6&lLevel")
                .lore("", "&e&l(!) &7View your island level!")
                .build()
        );

        inv.setItem(18, itemBuilder
                .material(Material.OAK_SIGN)
                .name("&9&lWarps")
                .lore("", "&e&l(!) &7Warp to other player's islands!")
                .build()
        );

        inv.setItem(22, itemBuilder
                .material(Material.NETHER_STAR)
                .name("&b&lShop")
                .lore("", "&e&l(!) &7Click to buy extra island gear!")
                .build()
        );

        inv.setItem(26, itemBuilder
                .material(Material.COMPARATOR)
                .name("&4&lSettings")
                .lore("", "&e&l(!) &7Edit your island settings!")
                .build()
        );

        inv.setItem(29, itemBuilder
                .material(Material.PLAYER_HEAD)
                .name("&a&lMemebers")
                .lore("", "&e&l(!) &7Click to edit your island members!")
                .build()
        );

        inv.setItem(33, itemBuilder
                .material(Material.CHEST)
                .name("&e&lChallenges")
                .lore("", "&e&l(!) &7Click to view your island challenges!")
                .build()
        );

        inv.setItem(40, itemBuilder
                .material(Material.ANVIL)
                .name("&d&lTop Islands")
                .lore("", "&e&l(!) &7Check out the top islands on the server!")
                .build()
        );

        SoundManager.click(player);
        player.openInventory(inv);
    }

}
