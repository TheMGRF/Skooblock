package games.indigo.skooblock.utils;

import games.indigo.skooblock.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private Main main = Main.getInstance();

    public File configFile, islandsFile;
    public FileConfiguration config, islandsConfig;

    public void loadFiles() {
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
    }

    public FileConfiguration getCustomConfig() { return config; }
    public File getCustomConfigFile() { return configFile; }
    public FileConfiguration getIslandsConfig() { return islandsConfig; }
    public File getIslandsFile() { return islandsFile; }

    public void reloadConfigs() {
        try {
            getCustomConfig().load(configFile);
            main.getIslandGenerator().getValues();

            getIslandsConfig().load(islandsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void createUserConfig(String owner,
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
        File localFile = new File(main.getDataFolder() + "/user-islands/", owner + ".yml");
        FileConfiguration localConfig = new YamlConfiguration();
        try {
            localConfig.save(localFile);
            localConfig.load(localFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        localConfig.set("owner", owner);
        localConfig.set("members", members);
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

    public FileConfiguration getUserConfig(Player player) {
        File localFile = new File(main.getDataFolder() + "/user-islands/", player.getUniqueId().toString() + ".yml");
        FileConfiguration localConfig = new YamlConfiguration();
        try {
            localConfig.load(localFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return localConfig;
    }
}
