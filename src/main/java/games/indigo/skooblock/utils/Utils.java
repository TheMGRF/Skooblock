package games.indigo.skooblock.utils;

import games.indigo.skooblock.Main;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Utils {

    /**
     * Build an item stack
     * @param material The material to use in the item stack
     * @param amount How many items should be in the item stack
     * @param name The display name of the item stack
     * @param description The lore of the item stack
     * @return The finished item stack
     */
    public ItemStack buildItem(Material material, int amount, String name, List<String> description) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(format(name));
        itemMeta.setLore(formatArray(description));

        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     * Build an item stack
     * @param material The material to use in the item stack
     * @param amount How many items should be in the item stack
     * @param name The display name of the item stack
     * @return The finished item stack
     */
    public ItemStack buildItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(format(name));

        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     *
     * @param item
     * @param name
     * @param description
     * @return
     */
    public ItemStack buildItem(ItemStack item, String name, List<String> description) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(formatArray(description));

        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     *
     * @param item
     * @param name
     * @return
     */
    public ItemStack buildItem(ItemStack item, String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);

        item.setItemMeta(itemMeta);

        return item;
    }

    public ItemStack buildWarpHead(UUID uuid, List<String> description) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(format("&a" + offlinePlayer.getName() + "'s Island"));

        itemMeta.setLore(description);
        item.setItemMeta(itemMeta);

        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwningPlayer(offlinePlayer);
        item.setItemMeta(skullMeta);

        return item;
    }

    /**
     * Format a string to contain Bukkit colour codes
     * @param msg The message to format
     * @return The formatted colour coded string
     */
    public String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Format an array to contain Bukkit colour codes
     * @param msgs The array to format
     * @return The formatted colour coded array
     */
    public List<String> formatArray(List<String> msgs) {
        for (int x = 0; x < msgs.size(); x++) {
            msgs.set(x, format(msgs.get(x)));
        }
        return msgs;
    }

    /**
     * Check to see if a player has linked their Discord account
     * @param player The player to check
     * @return <code>true</code> if the player has linked their account; <code>false</code> if the player has not linked their account
     */
    public boolean hasLinkedDiscord(Player player) {
        return DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(player.getUniqueId()) != null;
    }

    /**
     * Convert a condig string location to a Bukkit location
     * @param stringLoc The string location to convert
     * @return The converted string config location
     */
    public Location getLocationAsBukkitLocation(String stringLoc) {
        String[] locs = stringLoc.split(",");
        return new Location(Bukkit.getWorld(Main.getInstance().getConfigManager().getCustomConfig().getString("world")), Integer.parseInt(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]));
    }

    public List<Location> getBlocksInRegion(Location loc1, Location loc2) {
        List<Location> result = new ArrayList<>();
        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        for (int x = minX; x <= maxX; x += 1) {
            for (int y = minY; y <= maxY; y += 1) {
                for (int z = minZ; z <= maxZ; z += 1) {
                    result.add(new Location(loc1.getWorld(), x,y,z));
                }
            }
        }
        return result;
    }

}
