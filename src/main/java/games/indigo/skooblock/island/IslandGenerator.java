package games.indigo.skooblock.island;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.Transform;
import games.indigo.skooblock.ConfigManager;
import games.indigo.skooblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class IslandGenerator {

    private double x;
    private double z;
    private double maxX;
    private String world;

    private Main main = Main.getInstance();
    ConfigManager configManager = main.getConfigManager();

    public IslandGenerator() {
        getValues();
    }

    public void getValues() {
        x = configManager.getCustomConfig().getDouble("x");
        z = configManager.getCustomConfig().getDouble("y");
        maxX = configManager.getCustomConfig().getDouble("maxX");
        world = configManager.getCustomConfig().getString("world");
    }

    public void saveValues() {
        configManager.getCustomConfig().set("x", x);
        configManager.getCustomConfig().set("z", z);
        configManager.getCustomConfig().set("maxX", maxX);
    }

    public void generateNewIsland(Player player, String islandType) {
        Location loc = new Location(Bukkit.getWorld(world), x, 64, z);
        loadSchematic(islandType, loc);

        player.teleport(loc);
        player.sendMessage(main.getUtils().format("&6&l(!) &eEnjoy your new island!"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        int islandSize = 100; // 100 | 1000
        int buffer = 10; // 10 | 100

        if (x < maxX) {
            setX(x + (islandSize + buffer));
        } else {
            setX(0);
            setZ(z + (islandSize + buffer));
        }
    }

    private void loadSchematic(String name, Location loc) {
        File file = new File(main.getDataFolder() + "/schematics/", name + ".schem");

        try {
            EditSession editSession = ClipboardFormats.findByFile(file).load(file).paste(new BukkitWorld(Bukkit.getWorld(world)), BlockVector3.at(loc.getX(), loc.getY(), loc.getZ()), false, false, (Transform) null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public double getMaxX() {
        return maxX;
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }
}
