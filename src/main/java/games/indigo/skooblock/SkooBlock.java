package games.indigo.skooblock;

import games.indigo.skooblock.island.biomes.BiomeManager;
import games.indigo.skooblock.guis.BiomeSelector;
import games.indigo.skooblock.guis.IslandTypeSelector;
import games.indigo.skooblock.guis.MainMenu;
import games.indigo.skooblock.island.IslandManager;
import games.indigo.skooblock.utils.SoundsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SkooBlock {

    private static Main main = Main.getInstance();

    public static IslandManager getIslandManager() { return main.getIslandManager(); }
    public static SoundsManager getSoundsManager() { return main.getSoundsManager(); }
    public static BiomeManager getBiomeManager() { return main.getBiomeManager(); }
    public static MainMenu getMainMenu() { return main.getMainMenu(); }
    public static IslandTypeSelector getIslandTypeSelector() { return main.getIslandTypeSelector(); }
    public static BiomeSelector getBiomeSelector() { return main.getBiomeSelector(); }


    /**
     * Get the server spawn location
     * @return The servers spawn location
     */
    public static Location getSpawnLocation() {
        String[] locs = Main.getInstance().getConfigManager().config.getString("spawn").split(",");
        return new Location(Bukkit.getWorld("world"), Integer.parseInt(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]));
    }
}
