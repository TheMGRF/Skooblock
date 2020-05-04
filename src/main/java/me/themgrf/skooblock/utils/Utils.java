package me.themgrf.skooblock.utils;

import me.themgrf.skooblock.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * Check to see if a player has linked their Discord account
     * @param player The player to check
     * @return <code>true</code> if the player has linked their account; <code>false</code> if the player has not linked their account
     * @deprecated As of SkooBlock 1.0.0, because we don't currently use DiscordSRV.
     */
    @Deprecated
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
        return new Location(Bukkit.getWorld(Main.getInstance().getConfigManager().getCustomConfig().getString("world")), Integer.parseInt(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]));
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
        return Main.getInstance().getEconomy().getBalance(offlinePlayer);
    }
}
