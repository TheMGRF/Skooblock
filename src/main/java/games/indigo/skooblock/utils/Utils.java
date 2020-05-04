package games.indigo.skooblock.utils;

import games.indigo.skooblock.SkooBlock;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.math.BigDecimal;
import java.util.ArrayList;
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
     * Build an item stack
     * @param item The item stack to use as a base
     * @param name The name of the item
     * @param description The description of the item
     * @return The finished item stack
     */
    public ItemStack buildItem(ItemStack item, String name, List<String> description) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(format(name));
        itemMeta.setLore(formatArray(description));

        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     * Build an item stack
     * @param item The item stack to use as a base
     * @param name The name of the item
     * @return The finished item stack
     */
    public ItemStack buildItem(ItemStack item, String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(format(name));

        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     * Build a player head for the warp GUI
     * @param uuid The UUID for for the player to use
     * @param description The island description for the player head
     * @return The itemstack player head
     */
    public ItemStack buildWarpHead(UUID uuid, List<String> description) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
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
     * Create a top player item head
     * @param uuid The UUID of the player to use
     * @return The itemstack player head
     */
    public ItemStack buildTopHead(UUID uuid) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
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
        //return DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(player.getUniqueId()) != null;
        return false;
    }

    /**
     * Convert a condig string location to a Bukkit location
     * @param stringLoc The string location to convert
     * @return The converted string config location
     */
    public Location getLocationAsBukkitLocation(String stringLoc) {
        String[] locs = stringLoc.split(",");
        return new Location(Bukkit.getWorld(SkooBlock.getInstance().getConfigManager().getCustomConfig().getString("world")), Integer.parseInt(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]));
    }

    /**
     * Get all the blocks within a region
     * @param loc1 The lower boundary of a region
     * @param loc2 The upper boundary of a region
     * @return The list of blocks in a region
     */
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

    /**
     * Get a players current balance
     * @param offlinePlayer The player's balance to fetch
     * @return The players current balance
     */
    public double getBalance(OfflinePlayer offlinePlayer) {
        return SkooBlock.getInstance().getEconomy().getBalance(offlinePlayer);
    }

    /**
     * Convert material to readable name
     * @param material The material to convert
     * @return The human readable material name
     */
    public String convertItemMaterial(Material material) {

        String convert = material.toString().toLowerCase().replaceAll("\\_", " ");
        String newName = "";

        boolean space = true;
        for (int i = 0; i < convert.length(); i++) {
            if (space) newName += Character.toUpperCase(convert.charAt(i));
            else newName += convert.charAt(i);
            space = convert.charAt(i) == ' ';
        }

        return newName;
    }
}
