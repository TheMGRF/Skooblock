package games.indigo.skooblock;

import games.indigo.skooblock.island.*;
import games.indigo.skooblock.island.biomes.BiomeManager;
import games.indigo.skooblock.island.biomes.IslandBiome;
import games.indigo.skooblock.commands.IslandCmd;
import games.indigo.skooblock.commands.SkooblockCmd;
import games.indigo.skooblock.guis.*;
import games.indigo.skooblock.island.roles.IslandMemberRoleManager;
import games.indigo.skooblock.island.warps.WarpsManager;
import games.indigo.skooblock.listeners.*;
import games.indigo.skooblock.utils.ConfigManager;
import games.indigo.skooblock.utils.SoundsManager;
import games.indigo.skooblock.utils.Utils;
import games.indigo.skooblock.utils.WorldBorderManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class SkooBlock extends JavaPlugin {

    // Variable Declaration
    public static SkooBlock instance;

    // GUIs
    private MainMenu mainMenu;
    private BiomeSelector biomeSelector;
    private IslandTypeSelector islandTypeSelector;
    private WarpMenu warpMenu;
    private IslandMembersMenu islandMembersMenu;
    private IslandLevelMenu islandLevelMenu;
    private IslandTopMenu islandTopMenu;

    // Managers
    private Utils utils;
    private IslandGenerator islandGenerator;
    private ConfigManager configManager;
    private SoundsManager soundsManager;
    private IslandManager islandManager;
    private BiomeManager biomeManager;
    private WarpsManager warpsManager;
    private WorldBorderManager worldBorderManager;
    private IslandMemberRoleManager islandMemberRoleManager;

    private BlockLevelIndex blockLevelIndex;

    private List<Island> islands = new ArrayList<>();
    private List<IslandBiome> biomes = new ArrayList<>();

    private Economy economy;

    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();

        configManager.loadFiles();
        loadIslands();
        loadBiomes();

        setupEconomy();

        // Classes
        // GUIs
        mainMenu = new MainMenu();
        biomeSelector = new BiomeSelector();
        islandTypeSelector = new IslandTypeSelector();
        warpMenu = new WarpMenu();
        islandMembersMenu = new IslandMembersMenu();
        islandLevelMenu = new IslandLevelMenu();
        islandTopMenu = new IslandTopMenu();

        // Managers
        utils = new Utils();
        islandGenerator = new IslandGenerator();
        soundsManager = new SoundsManager();
        islandManager = new IslandManager();
        biomeManager = new BiomeManager();
        warpsManager = new WarpsManager();
        worldBorderManager = new WorldBorderManager();
        islandMemberRoleManager = new IslandMemberRoleManager();

        loadBlockIndex();

        // Commands
        getCommand("island").setExecutor(new IslandCmd());
        getCommand("skooblock").setExecutor(new SkooblockCmd());

        // Listeners
        List<Listener> listeners = Arrays.asList(new InventoryClickListener(), new BlockBreakListener(), new BlockPlaceListener(), new PlayerJoinListener(), new PlayerTeleportListener(), new PlayerMoveListener(), new IslandLeaveListener(), new IslandEnterListener());
        PluginManager pm = Bukkit.getPluginManager();
        for (Listener listener : listeners) {
            pm.registerEvents(listener, this);
        }
    }

    public void reload() {
        getConfigManager().reloadConfigs();
        loadIslands();
        loadBiomes();
        loadBlockIndex();
    }

    private void setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        economy = rsp.getProvider();
    }

    private void loadBlockIndex() {
        HashMap<Material, Integer> blockIndex = new HashMap<>();
        for (String material : getConfigManager().getBlockIndexConfig().getConfigurationSection("materials").getKeys(false)) {
            if (Material.valueOf(material) != null) {
                blockIndex.put(Material.valueOf(material), getConfigManager().getBlockIndexConfig().getInt("materials." + material));
            }
        }
        blockLevelIndex = new BlockLevelIndex(blockIndex);
    }

    private void loadIslands() {
        islands.clear();
        FileConfiguration islandConfig = configManager.getIslandsConfig();
        for (String identifier : islandConfig.getConfigurationSection("islands").getKeys(false)) {
            String path = "islands." + identifier + ".";
            islands.add(new Island(identifier,
                    Material.valueOf(islandConfig.getString(path + "icon")),
                    islandConfig.getString(path + "friendlyName"),
                    islandConfig.getString(path + "description"),
                    islandConfig.isSet(path + "costType") ? islandConfig.getString(path + "costType") : "",
                    islandConfig.isSet(path + "cost") ? islandConfig.getInt(path + "cost") : 0,
                    IslandType.valueOf(identifier.toUpperCase())));
        }
    }

    private void loadBiomes() {
        biomes.clear();
        FileConfiguration biomeConfig = configManager.getBiomeConfig();
        for (String identifier : biomeConfig.getConfigurationSection("biomes").getKeys(false)) {
            String path = "biomes." + identifier + ".";

            biomes.add(new IslandBiome(identifier,
                    Material.valueOf(biomeConfig.getString(path + "icon")),
                    biomeConfig.getString(path + "friendlyName"),
                    biomeConfig.getString(path + "description"),
                    biomeConfig.isSet(path + "costType") ? biomeConfig.getString(path + "costType") : "",
                    biomeConfig.isSet(path + "cost") ? biomeConfig.getInt(path + "cost") : 0));
        }
    }

    // Global Accessors
    public static SkooBlock getInstance() { return instance; }

    public Economy getEconomy() {
        return getEconomy();
    }

    // GUIs
    public MainMenu getMainMenu() { return mainMenu; }
    public BiomeSelector getBiomeSelector() { return biomeSelector; }
    public IslandTypeSelector getIslandTypeSelector() { return islandTypeSelector; }
    public WarpMenu getWarpMenu() { return warpMenu; }
    public IslandMembersMenu getIslandMembersMenu() { return islandMembersMenu; }
    public IslandLevelMenu getIslandLevelMenu() { return islandLevelMenu; }
    public IslandTopMenu getIslandTopMenu() { return islandTopMenu; }

    // Managers
    public Utils getUtils() { return utils; }
    public IslandGenerator getIslandGenerator() { return islandGenerator; }
    public List<Island> getIslandTypes() { return islands; }
    public List<IslandBiome> getIslandBiomes() { return biomes; }
    public ConfigManager getConfigManager() { return configManager; }
    public SoundsManager getSoundsManager() { return soundsManager; }
    public IslandManager getIslandManager() { return islandManager; }
    public BiomeManager getBiomeManager() { return biomeManager; }
    public WarpsManager getWarpsManager() { return warpsManager; }
    public WorldBorderManager getWorldBorderManager() { return worldBorderManager; }
    public IslandMemberRoleManager getIslandMemberRoleManager() { return islandMemberRoleManager; }

    public BlockLevelIndex getBlockLevelIndex() { return blockLevelIndex; }

    /**
     * Get the server spawn location
     * @return The servers spawn location
     */
    public static Location getSpawnLocation() {
        String[] locs = SkooBlock.getInstance().getConfigManager().config.getString("spawn").split(",");
        return new Location(Bukkit.getWorld("world"), Integer.parseInt(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]));
    }
}
