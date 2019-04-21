package games.indigo.skooblock.guis;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.island.UserIsland;
import games.indigo.skooblock.island.members.IslandMember;
import games.indigo.skooblock.island.members.IslandMemberRole;
import games.indigo.skooblock.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class IslandMembersMenu {

    Main main = Main.getInstance();
    Utils utils = main.getUtils();

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, main.getUtils().format("&a &lIsland Members"));

        inv.setItem(4, utils.buildItem(getHead(player.getUniqueId().toString()), utils.format("&6&lTheMGRF")));

        UserIsland userIsland = main.getIslandManager().getPlayerIsland(player);

        // TODO: This bs
        // for (IslandMemberRole islandMemberRole : userIsland.getMembers()) {
        //}

        //inv.setItem(9-17, filler);

        // members

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
