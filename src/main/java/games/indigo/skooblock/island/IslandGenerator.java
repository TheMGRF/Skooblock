package games.indigo.skooblock.island;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.Transform;
import games.indigo.skooblock.ConfigManager;
import games.indigo.skooblock.Main;
import games.indigo.skooblock.WorldBorderManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class IslandGenerator {

    private int x, z, maxX, islandSize, buffer;
    private String world;

    private Main main = Main.getInstance();
    ConfigManager configManager = main.getConfigManager();

    public IslandGenerator() {
        getValues();
    }

    public void getValues() {
        x = configManager.getCustomConfig().getInt("x");
        z = configManager.getCustomConfig().getInt("z");
        maxX = configManager.getCustomConfig().getInt("maxX");
        islandSize = configManager.getCustomConfig().getInt("islandSize");
        buffer = configManager.getCustomConfig().getInt("buffer");
        world = configManager.getCustomConfig().getString("world");
    }

    public void saveValues() {
        configManager.getCustomConfig().set("x", x);
        configManager.getCustomConfig().set("z", z);
        configManager.getCustomConfig().set("maxX", maxX);

        try {
            configManager.getCustomConfig().save(configManager.getCustomConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateNewIsland(Player player, String islandType) {
        final Location loc = new Location(Bukkit.getWorld(world), x, 64, z);
        loadSchematic(islandType, loc);

        player.teleport(loc);
        player.sendMessage(main.getUtils().format("&6&l(!) &eEnjoy your new island!"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        // TODO: Need to load the world border each time a player enters their island (custom event)
        new WorldBorderManager().sendBorder(player, loc.getX(), loc.getZ(), islandSize / 2);

        //int islandSize = 100 | 1000
        //int buffer = 10 | 100

        if (x < maxX) {
            setX(x + (islandSize + buffer));
        } else {
            setX(0);
            setZ(z + (islandSize + buffer));
        }

        saveValues();

        // TODO: Save island centre pos, max upper and lower bounds, initial bounds (for expansion + border),
        // TODO: All locations are the same

        Location centre = loc;
        Location lowerBounds = new Location(loc.getWorld(), (loc.getX() - (islandSize / 2) / 2), 0, (loc.getZ()  - (islandSize / 2) / 2));
        Location upperBounds = new Location(loc.getWorld(), (loc.getX() + (islandSize / 2) / 2), 255, (loc.getZ()  + (islandSize / 2) / 2));
        Location home = loc;
        Location warp = loc;

        UserIsland userIsland = new UserIsland(player.getUniqueId().toString(),
                Arrays.asList(""),
                (int) centre.getX() + "," + (int) centre.getY() + "," + (int) centre.getZ(),
                (int) lowerBounds.getX() + "," + (int) lowerBounds.getY() + "," + (int) lowerBounds.getZ(),
                (int) upperBounds.getX() + "," + (int) upperBounds.getY() + "," + (int) upperBounds.getZ(),
                "PLAINS",
                (int) home.getX() + "," + (int) home.getY() + "," + (int) home.getZ(),
                (int) warp.getX() + "," + (int) warp.getY() + "," + (int) warp.getZ(),
                Arrays.asList(true, false, false, false, true),
                islandSize,
                0
        );

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


    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }
}
