package games.indigo.skooblock;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private Main main = Main.getInstance();

    public File configFile, islandsFile, userIslandsFile;
    public FileConfiguration config, islandsConfig, userIslandsConfig;

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

        userIslandsFile = new File(main.getDataFolder(), "user-islands.yml");
        if (!userIslandsFile.exists()) {
            userIslandsFile.getParentFile().mkdirs();
            main.saveResource("user-islands.yml", false);
        }

        userIslandsConfig = new YamlConfiguration();
        try {
            userIslandsConfig.load(userIslandsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getCustomConfig() { return config; }
    public FileConfiguration getIslandsConfig() { return islandsConfig; }
    public FileConfiguration getUserIslandsConfig() { return userIslandsConfig; }

    public void reloadConfigs() {
        try {
            getCustomConfig().load(configFile);
            getIslandsConfig().load(islandsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
