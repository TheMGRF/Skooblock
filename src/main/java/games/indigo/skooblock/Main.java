package games.indigo.skooblock;

import games.indigo.skooblock.commands.IslandCmd;
import games.indigo.skooblock.commands.SkooblockCmd;
import games.indigo.skooblock.guis.IslandTypeSelector;
import games.indigo.skooblock.guis.MainMenu;
import games.indigo.skooblock.island.Island;
import games.indigo.skooblock.island.IslandGenerator;
import games.indigo.skooblock.island.IslandType;
import games.indigo.skooblock.listeners.*;
import games.indigo.skooblock.utils.ConfigManager;
import games.indigo.skooblock.utils.SoundsManager;
import games.indigo.skooblock.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {

    public static Main instance;

    private MainMenu mainMenu;
    private Utils utils;
    private IslandGenerator islandGenerator;
    private IslandTypeSelector islandTypeSelector;
    private ConfigManager configManager;
    private SoundsManager soundsManager;


    public List<Island> islands = new ArrayList<>();

    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();

        configManager.loadFiles();
        loadIslands();

        // Classes
        mainMenu = new MainMenu();
        utils = new Utils();
        islandGenerator = new IslandGenerator();
        islandTypeSelector = new IslandTypeSelector();
        soundsManager = new SoundsManager();

        // Commands
        getCommand("island").setExecutor(new IslandCmd());
        getCommand("skooblock").setExecutor(new SkooblockCmd());

        // Listeners
        List<Listener> listeners = Arrays.asList(new InventoryClickListener(), new BlockBreakListener(), new BlockPlaceListener(), new PlayerJoinListener(), new PlayerTeleportListener());
        PluginManager pm = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            pm.registerEvents(listener, this);
        }

    }

    public void loadIslands() {
        islands.clear();
        FileConfiguration islandConfig = configManager.getIslandsConfig();
        for (String identifier : islandConfig.getConfigurationSection("islands").getKeys(false)) {
            String path = "islands." + identifier + ".";
            islands.add(new Island(identifier,
                    Material.valueOf(islandConfig.getString(path + "icon")),
                    islandConfig.getString(path + "friendlyName"),
                    islandConfig.getString(path + " description"),
                    islandConfig.isSet(path + "costType") ? islandConfig.getString(path + "costType") : "",
                    islandConfig.isSet(path + "cost") ? islandConfig.getInt(path + "cost") : 0,
                    IslandType.valueOf(identifier.toUpperCase())));
        }
    }

    public static Main getInstance() { return instance; }

    public MainMenu getMainMenu() { return mainMenu; }
    public Utils getUtils() { return utils; }
    public IslandGenerator getIslandGenerator() { return islandGenerator; }
    public List<Island> getIslandTypes() { return islands; }
    public IslandTypeSelector getIslandTypeSelector() { return islandTypeSelector; }
    public ConfigManager getConfigManager() { return configManager; }
    public SoundsManager getSoundsManager() { return soundsManager; }
}
