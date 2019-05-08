package games.indigo.skooblock.guis;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.UserIsland;
import games.indigo.skooblock.island.members.IslandMember;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.UUID;

public class IslandMembersMenu {

    private SkooBlock skooBlock = SkooBlock.getInstance();

    public void open(Player player) {
        UserIsland userIsland = null;
        String uuid = player.getUniqueId().toString();

        if (skooBlock.getIslandManager().playerHasIsland(uuid)) {
            userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
        } else if (skooBlock.getIslandManager().getMemberIsland(uuid) != null) {
            userIsland = skooBlock.getIslandManager().getMemberIsland(player.getUniqueId().toString());
        } else {
            player.sendMessage(skooBlock.getUtils().format("&4&l(!) &cFailed to find your island! Please try again."));
            skooBlock.getSoundsManager().error(player);
            return;
        }

        Inventory inv = Bukkit.createInventory(null, 27, skooBlock.getUtils().format("&a&lIsland Members"));

        int loop = 0;
        for (IslandMember islandMember : userIsland.getMembers()) {
            String name = Bukkit.getOfflinePlayer(UUID.fromString(islandMember.getUuid())).getName();
            String role = islandMember.getMemberRole().getName();
            String balance = NumberFormat.getInstance().format(skooBlock.getUtils().getBalance(name));
            inv.setItem(loop, skooBlock.getUtils().buildItem(getHead(player.getUniqueId().toString()), "&a" + name, Arrays.asList(""," &7Role: &e" + role, " &7Balance: &e$" + balance,"", "&e&l(!) &fLeft-Click &7to &apromote&7!", "&e&l(!) &fRight-Click &7to &cdemote&7!", "&e&l(!) &fMiddle-Click &7to &4kick&7!")));
            loop++;
        }

        player.openInventory(inv);
    }

    private ItemStack getHead(String uuid) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = item.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) itemMeta;
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)));

        item.setItemMeta(itemMeta);

        return item;
    }

}
