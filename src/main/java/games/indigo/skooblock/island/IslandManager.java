package games.indigo.skooblock.island;

import com.boydti.fawe.util.EditSessionBuilder;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BlockTypes;
import games.indigo.skooblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IslandManager {

    /**
     * Check to see if a player has an island
     * @param player The player to check
     * @return <code>true</code> if player has an island; <code>false</code> if the player does not have an island
     */
    public boolean playerHasIsland(Player player) {
        return new File(Main.getInstance().getDataFolder() + "/user-islands/", player.getUniqueId().toString() + ".yml").exists();
    }

    /**
     * Get a players island
     * @param player The player whos island to get
     * @return A players island
     */
    public UserIsland getPlayerIsland(Player player) {
        if (playerHasIsland(player)) {
            FileConfiguration config = Main.getInstance().getConfigManager().getUserConfig(player);

            UserIsland userIsland = generateUserIslandFromConfig(config);
            return userIsland;
        }
        return null;
    }

    /**
     * Check to see if a player is on their island
     * @param player The player to check
     * @return <code>true</code> if the player is on their island; <code>false</code> if the player is not on their island
     */
    public boolean isPlayerOnHomeIsland(Player player) {
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
     * Get the island the player is on
     * @param player The player to check
     * @return The user island the player is on
     */
    public UserIsland getIslandPlayerIsOn(Player player) {

        // Online check
        for (Player loop : Bukkit.getOnlinePlayers()) {
            if (getPlayerIsland(loop).getPlayersOnIsland().containsKey(player)) {
                return getPlayerIsland(loop);
            }
        }

        // Config check
        Location loc = player.getLocation();
        for (UserIsland userIsland : getAllPlayerIslands()) {

            Location lowerBound = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
            Location upperBound = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());

            if (((loc.getX() > lowerBound.getX()) && (loc.getY() > lowerBound.getY()) && (loc.getZ() > lowerBound.getZ())) && ((loc.getX() < upperBound.getX()) && (loc.getY() < upperBound.getY()) && (loc.getZ() < upperBound.getZ()))) {
                return userIsland;
            }
        }

        return null;
    }

    /**
     * Check to see if a player is a member of a user island
     * @param player The player to check
     * @param userIsland The user island to check
     * @return <code>true</code> if the player is an island member; <code>false</code> if the player is not an island member
     */
    public boolean isPlayerMember(Player player, UserIsland userIsland) {
        return userIsland.getMembers().contains(player.getUniqueId().toString());
    }

    /**
     * Reset a players island
     * @param player The player whos island should be reset
     */
    public void resetIsland(Player player) {
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
    public boolean isIslandResetting(Player player) {
        return player.hasMetadata("resettingIsland");
    }

    /**
     * Generate a user island from config data
     * @param fileConfiguration The configuration file to get the information from
     * @return The generated user island
     */
    public UserIsland generateUserIslandFromConfig(FileConfiguration fileConfiguration) {
        return new UserIsland(fileConfiguration.getString("owner"),
                fileConfiguration.getStringList("members"),
                fileConfiguration.getString("centre"),
                fileConfiguration.getString("lowerBound"),
                fileConfiguration.getString("upperBound"),
                fileConfiguration.getString("biome"),
                fileConfiguration.getString("home"),
                fileConfiguration.getString("warp"),
                fileConfiguration.getString("description"),
                fileConfiguration.getBooleanList("settings"),
                fileConfiguration.getInt("size"),
                fileConfiguration.getInt("level"));
    }

    /**
     * Get a list of all user islands
     * @return A list of all user islands
     */
    public List<UserIsland> getAllPlayerIslands() {
        List<UserIsland> userIslands = new ArrayList<>();

        for (File file : new File(Main.getInstance().getDataFolder() + "/user-islands/").listFiles()) {
            FileConfiguration fileConfiguration = new YamlConfiguration();
            try {
                fileConfiguration.load(file);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }

            userIslands.add(generateUserIslandFromConfig(fileConfiguration));
        }

        return userIslands;
    }

}
