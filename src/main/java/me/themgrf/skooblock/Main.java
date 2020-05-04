package me.themgrf.skooblock;

import me.themgrf.skooblock.commands.IslandCmd;
import me.themgrf.skooblock.commands.SkooblockCmd;
import me.themgrf.skooblock.island.Island;
import me.themgrf.skooblock.island.biomes.IslandBiome;
import me.themgrf.skooblock.utils.ConfigManager;
import me.themgrf.skooblock.island.BlockLevelIndex;
import me.themgrf.skooblock.island.IslandType;
import me.themgrf.skooblock.listeners.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main instance;

    private Economy economy;
    private ConfigManager configManager;

    public BlockLevelIndex blockLevelIndex;

    public final List<Island> islands = new ArrayList<>();
    public final List<IslandBiome> biomes = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        configManager = new ConfigManager();

        configManager.loadFiles();
        loadIslands();
        loadBiomes();

        setupEconomy();



        loadBlockIndex();

        // Commands
        getCommand("island").setExecutor(new IslandCmd());
        getCommand("skooblock").setExecutor(new SkooblockCmd());

        // Listeners
        registerListeners(new InventoryClickListener(), new BlockBreakListener(), new BlockPlaceListener(), new PlayerJoinListener(), new PlayerTeleportListener(), new PlayerMoveListener(), new IslandLeaveListener(), new IslandEnterListener());
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
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

    public static Main getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Economy getEconomy() {
        return economy;
    }

}
