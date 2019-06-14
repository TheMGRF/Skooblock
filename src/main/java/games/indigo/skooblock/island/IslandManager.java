package games.indigo.skooblock.island;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.members.IslandMember;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class IslandManager {

    /**
     * Check to see if a player has an island
     *
     * @param uuid The UUID of the player to check
     * @return <code>true</code> if player has an island; <code>false</code> if the player does not have an island
     */
    public boolean playerHasIsland(String uuid) {
        return new File(SkooBlock.getInstance().getDataFolder() + "/user-islands/", uuid + ".yml").exists();
    }

    /**
     * Get a players island
     *
     * @param uuid The UUID of the player whos island to get
     * @return A players island
     */
    public UserIsland getPlayerIsland(String uuid) {
        if (playerHasIsland(uuid)) {
            FileConfiguration config = SkooBlock.getInstance().getConfigManager().getUserConfig(uuid);

            UserIsland userIsland = generateUserIslandFromConfig(config);
            return userIsland;
        }
        for (UserIsland userIsland : getAllPlayerIslands()) {
            for (IslandMember islandMember : userIsland.getMembers()) {
                if (islandMember.getUuid().equals(uuid)) {
                    return userIsland;
                }
            }
        }
        return null;
    }

    /**
     * Check to see if a player is on their island
     *
     * @param player The player to check
     * @return <code>true</code> if the player is on their island; <code>false</code> if the player is not on their island
     */
    public boolean isPlayerOnHomeIsland(Player player) {
        if (getPlayerIsland(player.getUniqueId().toString()) != null) {
            UserIsland userIsland = getPlayerIsland(player.getUniqueId().toString());

            Location loc = player.getLocation();
            Location lowerBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
            Location upperBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());

            if (((loc.getX() > lowerBound.getX()) && (loc.getY() > lowerBound.getY()) && (loc.getZ() > lowerBound.getZ())) && ((loc.getX() < upperBound.getX()) && (loc.getY() < upperBound.getY()) && (loc.getZ() < upperBound.getZ()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the island the player is on
     *
     * @param player The player to check
     * @return The user island the player is on
     */
    public UserIsland getIslandPlayerIsOn(Player player) {
        // Online check
        for (Player loop : Bukkit.getOnlinePlayers()) {
            if (playerHasIsland(loop.getUniqueId().toString())) {
                if (getPlayerIsland(loop.getUniqueId().toString()).getPlayersOnIsland().containsKey(player)) {
                    return getPlayerIsland(loop.getUniqueId().toString());
                }
            }
        }

        // Config check
        Location loc = player.getLocation();
        for (UserIsland userIsland : getAllPlayerIslands()) {

            Location lowerBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
            Location upperBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());

            if (((loc.getX() > lowerBound.getX()) && (loc.getY() > lowerBound.getY()) && (loc.getZ() > lowerBound.getZ())) && ((loc.getX() < upperBound.getX()) && (loc.getY() < upperBound.getY()) && (loc.getZ() < upperBound.getZ()))) {
                return userIsland;
            }
        }

        return null;
    }

    /**
     * Get a user island at a specific location
     *
     * @param loc The location to check
     * @return The user island at the location
     */
    public UserIsland getUserIslandAtLocation(Location loc) {
        // Online check
        for (Player loop : Bukkit.getOnlinePlayers()) {
            if (SkooBlock.getInstance().getIslandManager().playerHasIsland(loop.getUniqueId().toString())) {
                UserIsland userIsland = getPlayerIsland(loop.getUniqueId().toString());
                Location lowerBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
                Location upperBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());

                if (((loc.getX() > lowerBound.getX()) && (loc.getY() > lowerBound.getY()) && (loc.getZ() > lowerBound.getZ())) && ((loc.getX() < upperBound.getX()) && (loc.getY() < upperBound.getY()) && (loc.getZ() < upperBound.getZ()))) {
                    return userIsland;
                }
            }
        }

        // Config check
        for (UserIsland userIsland : getAllPlayerIslands()) {
            Location lowerBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
            Location upperBound = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());

            if (((loc.getX() > lowerBound.getX()) && (loc.getY() > lowerBound.getY()) && (loc.getZ() > lowerBound.getZ())) && ((loc.getX() < upperBound.getX()) && (loc.getY() < upperBound.getY()) && (loc.getZ() < upperBound.getZ()))) {
                return userIsland;
            }
        }

        return null;
    }

    /**
     * Get the island a player is a member of
     *
     * @param uuid The UUID of the player to check
     * @return The user island the player is a member of
     */
    public UserIsland getMemberIsland(String uuid) {
        for (UserIsland userIsland : getAllPlayerIslands()) {
            for (IslandMember islandMember : userIsland.getMembers()) {
                if (islandMember.getUuid().equals(uuid)) {
                    return userIsland;
                }
            }
        }

        return null;
    }

    /**
     * Check to see if a player is a member of a user island
     *
     * @param player     The player to check
     * @param userIsland The user island to check
     * @return <code>true</code> if the player is an island member; <code>false</code> if the player is not an island member
     */
    public boolean isPlayerMember(Player player, UserIsland userIsland) {
        return userIsland.getMembers().contains(player.getUniqueId().toString());
    }

    /**
     * Reset a players island
     *
     * @param player The player whos island should be reset
     */
    public void resetIsland(Player player) {
        UserIsland userIsland = getPlayerIsland(player.getUniqueId().toString());

        World world = new BukkitWorld(Bukkit.getWorld(SkooBlock.getInstance().getIslandGenerator().getWorld()));
        Location lowerLoc = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
        Location upperLoc = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());
        BlockVector3 lowerVec = BlockVector3.at(lowerLoc.getX(), lowerLoc.getY(), lowerLoc.getZ());
        BlockVector3 upperVec = BlockVector3.at(upperLoc.getX(), upperLoc.getY(), upperLoc.getZ());

        //EditSession editSession = new EditSessionBuilder(world).fastmode(true).build();
        //Region region = new CuboidRegion(world, lowerVec, upperVec);

        for (Block block : getBlocksOnIsland(userIsland)) {
            block.setType(Material.AIR);
        }


        // TODO: Should really find a more efficient method of doing this
        //editSession.setBlocks(region, BlockTypes.AIR.getDefaultState());
    }

    /**
     * Check if a players island is currently resetting
     *
     * @param player
     * @return
     */
    public boolean isIslandResetting(Player player) {
        return player.hasMetadata("resettingIsland");
    }

    /**
     * Generate a user island from config data
     *
     * @param fileConfiguration The configuration file to get the information from
     * @return The generated user island
     */
    public UserIsland generateUserIslandFromConfig(FileConfiguration fileConfiguration) {
        List<String> members = new ArrayList<>();
        if (fileConfiguration.isSet("members.")) {
            // TODO: Bug here NPE

            Map<String, Object> map = fileConfiguration.getConfigurationSection("members.").getValues(false);
            for (String uuid : map.keySet()) {
                members.add(uuid);
            }
        }

        return new UserIsland(fileConfiguration.getString("owner"),
                members,
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
     * Get a user island from a UUID
     * @param uuid The UUID to check
     * @return The user island relating to the UUID
     */
    public UserIsland getUserIslandFromUUID(String uuid) {
        for (UserIsland userIsland : getAllPlayerIslands()) {
            if (userIsland.getOwner().equals(uuid)) {
                return userIsland;
            }
        }

        return null;
    }

    /**
     * Get a list of all user islands
     *
     * @return A list of all user islands
     */
    public List<UserIsland> getAllPlayerIslands() {
        List<UserIsland> userIslands = new ArrayList<>();

        for (File file : new File(SkooBlock.getInstance().getDataFolder() + "/user-islands/").listFiles()) {
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

    // TODO: 1.276*500 = 638
    // TODO: 638/500 = 1.276

    /**
     * Calculate the island level
     * @param userIsland The island to calculate the level of
     * @return The user islands level
     */
    public int calculateIslandLevel(UserIsland userIsland) {
        return calculateIslandPoints(userIsland)/500;
    }

    /**
     * Calculate the points on a users island
     * @param userIsland The user island to calculate the points of
     * @return The user island points
     */
    public int calculateIslandPoints(UserIsland userIsland) {
        int points = 0;
        for (Block block : getBlocksOnIsland(userIsland)) {
            points += SkooBlock.getInstance().getBlockLevelIndex().getBlockWorth(block.getType());
        }

        return points;
    }

    /**
     * Get a list of blocks on a users island
     * @param userIsland The user island to get the blocks on
     * @return The list of blocks on a users island
     */
    public List<Block> getBlocksOnIsland(UserIsland userIsland) {
        Location loc1 = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getLowerBound());
        Location loc2 = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getUpperBound());

        List<Block> blocks = new ArrayList<Block>();

        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());

        int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());

        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);

                    if (block.getType() != Material.AIR) {
                        blocks.add(block);
                    }
                }
            }
        }

        return blocks;
    }

    /**
     * Get a list of the top islands
     * @param top The amount of top islands to get
     * @return The list of top islands
     */
    public List<UserIsland> getTopIslands(int top) {
        List<UserIsland> userIslands = getAllPlayerIslands();
        Collections.sort(userIslands);
        Collections.reverse(userIslands);

        if (userIslands.size() < top) { top = userIslands.size(); }

        return userIslands.subList(0, top);
    }


}
