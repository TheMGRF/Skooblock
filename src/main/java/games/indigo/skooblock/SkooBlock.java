package games.indigo.skooblock;

import com.boydti.fawe.util.EditSessionBuilder;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BlockTypes;
import games.indigo.skooblock.island.UserIsland;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SkooBlock {

    /**
     * Check to see if a player has an island
     * @param player The player to check
     * @return <code>true</code> if player has an island; <code>false</code> if the player does not have an island
     */
    public static boolean playerHasIsland(Player player) {
        return new File(Main.getInstance().getDataFolder() + "/user-islands/", player.getUniqueId().toString() + ".yml").exists();
    }

    /**
     * Get a players island
     * @param player The player whos island to get
     * @return A players island
     */
    public static UserIsland getPlayerIsland(Player player) {
        if (playerHasIsland(player)) {
            FileConfiguration config = Main.getInstance().getConfigManager().getUserConfig(player);

            UserIsland userIsland = new UserIsland(config.getString("owner"),
                    config.getStringList("members"),
                    config.getString("centre"),
                    config.getString("lowerBound"),
                    config.getString("upperBound"),
                    config.getString("biome"),
                    config.getString("home"),
                    config.getString("warp"),
                    config.getBooleanList("settings"),
                    config.getInt("size"),
                    config.getInt("level"));
            return userIsland;
        }
        return null;
    }

    /**
     * Check to see if a player is on their island
     * @param player The player to check
     * @return <code>true</code> if the player is on their island; <code>false</code> if the player is not on their island
     */
    public static boolean isPlayerOnIsland(Player player) {
        if (getPlayerIsland(player) != null) {
            UserIsland userIsland = getPlayerIsland(player);

            Location loc = player.getLocation();
            Location lowerBound = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
            Location upperBound = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());

            if (((loc.getX() > lowerBound.getX()) && (loc.getY() > lowerBound.getY()) && (loc.getZ() > lowerBound.getZ())) && ((loc.getX() < upperBound.getX()) && (loc.getY() < upperBound.getY()) && (loc.getZ() < upperBound.getZ()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reset a players island
     * @param player The player whos island should be reset
     */
    public static void resetIsland(Player player) {
        UserIsland userIsland = getPlayerIsland(player);

        World world = new BukkitWorld(Bukkit.getWorld(Main.getInstance().getIslandGenerator().getWorld()));
        Location lowerLoc = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
        Location upperLoc = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());
        BlockVector3 lowerVec = BlockVector3.at(lowerLoc.getX(), lowerLoc.getY(), lowerLoc.getZ());
        BlockVector3 upperVec = BlockVector3.at(upperLoc.getX(), upperLoc.getY(), upperLoc.getZ());

        EditSession editSession = new EditSessionBuilder(world).fastmode(true).build();
        Region region = new CuboidRegion(world, lowerVec , upperVec);

        // TODO: Should really find a more efficient method of doing this
        editSession.setBlocks(region, BlockTypes.AIR.getDefaultState());
    }

    /**
     * Check if a players island is currently resetting
     * @param player
     * @return
     */
    public static boolean isIslandResetting(Player player) {
        return player.hasMetadata("resettingIsland");
    }

    /**
     * Get the server spawn location
     * @return The servers spawn location
     */
    public static Location getSpawnLocation() {
        String[] locs = Main.getInstance().getConfigManager().config.getString("spawn").split(",");
        return new Location(Bukkit.getWorld("world"), Integer.parseInt(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]));
    }
}
