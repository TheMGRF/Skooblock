package games.indigo.skooblock.island;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.utils.Utils;
import org.bukkit.Location;
import org.bukkit.block.Biome;

import java.util.List;

public class UserIsland {

    private String owner, biome;
    private List<String> members;
    private String centre, lowerBound, upperBound, home, warp;
    private List<Boolean> settings;
    private int size, level;

    /**
     * Constructor for creating a user island
     * @param owner The UUID of the island owner
     * @param members A list of the island members
     * @param centre The centre location of the island
     * @param lowerBound The lower bounds of the island border
     * @param upperBound The upper bounds of the island border
     * @param biome The islands current biome
     * @param home The home location of the island
     * @param warp The warp location of the island
     * @param settings The settings associated with the island
     * @param size The size of the island
     * @param level The current level of the island
     */
    public UserIsland(String owner,
                      List<String> members,
                      String centre,
                      String lowerBound,
                      String upperBound,
                      String biome,
                      String home,
                      String warp,
                      List<Boolean> settings,
                      int size,
                      int level) {
        this.owner = owner;
        this.members = members;
        this.centre = centre;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.biome = biome;
        this.home = home;
        this.warp = warp;
        this.settings = settings;
        this.size = size;
        this.level = level;
    }

    /**
     * Generate a config file associated with the user island
     * @param userIsland The users island to create a config for
     */
    public void generateConfig(UserIsland userIsland) {
        Main.getInstance().getConfigManager().createUserConfig(userIsland.owner, userIsland.members, userIsland.centre, userIsland.lowerBound, userIsland.upperBound, userIsland.biome, userIsland.home, userIsland.warp, userIsland.settings, userIsland.size, userIsland.level);
    }

    /**
     * Get the owner of an islands UUID
     * @return The owner of an islands UUID
     */
    public String getOwner() {
        return owner;
    }

    /**
     * The the UUIDs of the members of an island
     * @return The UUIDs of the members of an island
     */
    public List<String> getMembers() {
        return members;
    }

    /**
     * Get the current biome of an island
     * @return The current biome of an island
     */
    public String getBiome() {
        return biome;
    }

    /**
     * Get the centre point of an island
     * @return The centre point of an island
     */
    public String getCentre() {
        return centre;
    }

    /**
     * Get the lower bounds of an island
     * @return The lower bounds of an island
     */
    public String getLowerBound() {
        return lowerBound;
    }

    /**
     * Get the upper bounds of an island
     * @return The upper bounds of an island
     */
    public String getUpperBound() {
        return upperBound;
    }

    /**
     * Get the home location of an island
     * @return The home location of an island
     */
    public String getHome() {
        return home;
    }

    /**
     * Get the warp location of an island
     * @return The warp location of an island
     */
    public String getWarp() {
        return warp;
    }

    /**
     * Get the list of settings associated with an island
     * @return The list of settings associated with an island
     */
    public List<Boolean> getSettings() {
        return settings;
    }

    /**
     * Get the size of an island
     * @return The size of an island
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the current level of an island
     * @return The current level of an island
     */
    public int getLevel() {
        return level;
    }


    public void setBiome(Biome biome) {
        Utils utils = Main.getInstance().getUtils();
        for (Location loc : utils.getBlocksInRegion(utils.getLocationAsBukkitLocation(getUpperBound()), utils.getLocationAsBukkitLocation(getLowerBound()))) {
            loc.getBlock().setBiome(biome);
        }
    }
}
