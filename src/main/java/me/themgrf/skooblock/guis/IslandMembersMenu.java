package me.themgrf.skooblock.guis;

import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.UserIsland;
import me.themgrf.skooblock.island.members.IslandMember;
import me.themgrf.skooblock.utils.ItemBuilder;
import me.themgrf.skooblock.utils.SoundManager;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.NumberFormat;
import java.util.UUID;

public class IslandMembersMenu {

    private final static SkooBlock skooBlock = SkooBlock.getInstance();

    public static void open(Player player) {
        UserIsland userIsland = null;
        String uuid = player.getUniqueId().toString();

        if (skooBlock.getIslandManager().playerHasIsland(uuid)) {
            userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
        } else if (skooBlock.getIslandManager().getMemberIsland(uuid) != null) {
            userIsland = skooBlock.getIslandManager().getMemberIsland(player.getUniqueId().toString());
        } else {
            player.sendMessage(Text.colour("&4&l(!) &cFailed to find your island! Please try again."));
            SoundManager.error(player);
            return;
        }

        Inventory inv = Bukkit.createInventory(null, 27, Text.colour("&a&lIsland Members"));

        int loop = 0;
        for (IslandMember islandMember : userIsland.getMembers()) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(islandMember.getUuid()));
            String role = islandMember.getMemberRole().getName();
            String balance = NumberFormat.getInstance().format(skooBlock.getUtils().getBalance(offlinePlayer));
            inv.setItem(loop, new ItemBuilder()
                    .itemStack(getHead(islandMember.getUuid()))
                    .name("&a" + offlinePlayer.getName())
                    .lore("",
                            " &7Role: &e" + role,
                            " &7Balance: &e$" + balance,
                            "",
                            "&e&l(!) &fLeft-Click &7to &apromote&7!",
                            "&e&l(!) &fRight-Click &7to &cdemote&7!",
                            "&e&l(!) &fMiddle-Click &7to &4kick&7!")
                    .build()
            );
            loop++;
        }

        player.openInventory(inv);
    }

    private static ItemStack getHead(String uuid) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = item.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) itemMeta;
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)));

        item.setItemMeta(itemMeta);

        return item;
    }

}
