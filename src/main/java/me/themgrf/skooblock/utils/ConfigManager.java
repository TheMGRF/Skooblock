package me.themgrf.skooblock.utils;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private final SkooBlock skooBlock = SkooBlock.getInstance();
    private final Main main = Main.getInstance();

    public File configFile, islandsFile, biomeFile, blockIndexFile;
    public FileConfiguration config, islandsConfig, biomeConfig, blockIndexConfig;

    public void loadFiles() {
        // Custom Config
        configFile = new File(main.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            main.saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        // Islands Config
        islandsFile = new File(main.getDataFolder(), "islands.yml");
        if (!islandsFile.exists()) {
            islandsFile.getParentFile().mkdirs();
            main.saveResource("islands.yml", false);
        }

        islandsConfig = new YamlConfiguration();
        try {
            islandsConfig.load(islandsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        // Biome Config
        biomeFile = new File(main.getDataFolder(), "biomes.yml");
        if (!biomeFile.exists()) {
            biomeFile.getParentFile().mkdirs();
            main.saveResource("biomes.yml", false);
        }

        biomeConfig = new YamlConfiguration();
        try {
            biomeConfig.load(biomeFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        // Block Index Config
        blockIndexFile = new File(main.getDataFolder(), "block-index.yml");
        if (!blockIndexFile.exists()) {
            blockIndexFile.getParentFile().mkdirs();
            main.saveResource("block-index.yml", false);
        }

        blockIndexConfig = new YamlConfiguration();
        try {
            blockIndexConfig.load(blockIndexFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getCustomConfig() { return config; }
    public File getCustomConfigFile() { return configFile; }

    public FileConfiguration getIslandsConfig() { return islandsConfig; }
    public File getIslandsFile() { return islandsFile; }

    public File getBiomeFile() { return biomeFile; }
    public FileConfiguration getBiomeConfig() { return biomeConfig; }

    public File getBlockIndexFile() { return blockIndexFile; }
    public FileConfiguration getBlockIndexConfig() { return blockIndexConfig; }

    public void reloadConfigs() {
        try {
            getCustomConfig().load(getCustomConfigFile());

            skooBlock.getIslandGenerator().getValues();
            getIslandsConfig().load(getIslandsFile());

            getBiomeConfig().load(getBiomeFile());

            getBlockIndexConfig().load(getBlockIndexFile());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void createUserConfig(String owner,
                                 String centre,
                                 String lowerBound,
                                 String upperBound,
                                 String biome,
                                 String home,
                                 String warp,
                                 List<Boolean> settings,
                                 int size,
                                 int level) {
        File localFile = new File(main.getDataFolder() + "/user-islands/", owner + ".yml");
        FileConfiguration localConfig = new YamlConfiguration();
        try {
            localConfig.save(localFile);
            localConfig.load(localFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        localConfig.set("owner", owner);
        localConfig.set("centre", centre);
        localConfig.set("lowerBound", lowerBound);
        localConfig.set("upperBound", upperBound);
        localConfig.set("biome", biome);
        localConfig.set("home", home);
        localConfig.set("warp", warp);
        localConfig.set("settings", settings);
        localConfig.set("size", size);
        localConfig.set("level", level);

        try {
            localConfig.save(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getUserConfig(String uuid) {
        File localFile = new File(main.getDataFolder() + "/user-islands/", uuid + ".yml");
        FileConfiguration localConfig = new YamlConfiguration();
        try {
            localConfig.load(localFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return localConfig;
    }

    public File getUserFile(String uuid) { return new File(main.getDataFolder() + "/user-islands/", uuid + ".yml"); }

    public List<FileConfiguration> getAllUserConfigs() {
        //main.getDataFolder().listFiles();
        List<FileConfiguration> configs = new ArrayList<>();
        for (File file : new File(main.getDataFolder() + "/user-islands").listFiles()) {
            FileConfiguration config = new YamlConfiguration();
            try {
                config.load(file);
                configs.add(config);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        return configs;
    }
}
