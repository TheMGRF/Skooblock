package me.themgrf.skooblock.island;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.utils.ConfigManager;
import me.themgrf.skooblock.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class IslandGenerator {

    private int x, z, maxX, islandSize, buffer, islandsBeingCreated;
    private String world;

    private final SkooBlock skooBlock = SkooBlock.getInstance();
    private final Main main = Main.getInstance();
    private final ConfigManager configManager = main.getConfigManager();

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
        incrementIslandsBeingCreated();

        Location loc = new Location(Bukkit.getWorld(world), x, 64, z);

        if (skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
            UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
            Location centre = skooBlock.getUtils().getLocationAsBukkitLocation(userIsland.getCentre());
            loc = new Location(Bukkit.getWorld(world), centre.getX(), centre.getY(), centre.getZ());
        }

        loadSchematic(islandType, loc);

        player.teleport(loc.add(0.5, 0, 0.5));
        player.sendMessage(Text.colour("&6&l(!) &eEnjoy your new island!"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        // TODO: Need to load the world border each time a player enters their island (custom event)
        skooBlock.getWorldBorderManager().applyBorder(player);

        //int islandSize = 100 | 1000
        //int buffer = 10 | 100

        if (x < maxX) {
            setX(x + (islandSize + buffer));
        } else {
            setX(0);
            setZ(z + (islandSize + buffer));
        }

        saveValues();

        Location centre = loc;
        Location lowerBounds = new Location(loc.getWorld(), (loc.getX() - (islandSize / 2) - 1), 0, (loc.getZ() - (islandSize / 2) - 1));
        Location upperBounds = new Location(loc.getWorld(), (loc.getX() + (islandSize / 2) + 1), 255, (loc.getZ() + (islandSize / 2) + 1));
        Location home = loc;

        UserIsland userIsland = new UserIsland(player.getUniqueId().toString(),
                Arrays.asList(""),
                (int) centre.getX() + "," + (int) centre.getY() + "," + (int) centre.getZ(),
                (int) lowerBounds.getX() + "," + (int) lowerBounds.getY() + "," + (int) lowerBounds.getZ(),
                (int) upperBounds.getX() + "," + (int) upperBounds.getY() + "," + (int) upperBounds.getZ(),
                "PLAINS",
                (int) home.getX() + "," + (int) home.getY() + "," + (int) home.getZ(),
                "0,0,0",
                "",
                Arrays.asList(true, false, false, false, true),
                islandSize,
                0
        );
        userIsland.generateConfig(userIsland);
    }

    // TODO: Get new schematic API from Dom hopefully
    private void loadSchematic(String name, Location loc) {
        File file = new File(main.getDataFolder() + "/schematics/", name + ".schem");
        //EditSession editSession = ClipboardFormats.findByFile(file).load(file).paste(new BukkitWorld(Bukkit.getWorld(world)), BlockVector3.at(loc.getX(), loc.getY(), loc.getZ()), false, false, (Transform) null);
        //ClipboardFormat format = ClipboardFormats.findByFile(file);
        //ClipBoardReader reader = format.getReader(new FileInputStream(file));
        //Clipboard clipboard = reader.read();
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public int getMaxX() {
        return maxX;
    }

    public String getWorld() { return world; }

    public int getIslandSize() { return islandSize; }

    public int getBuffer() { return buffer; }

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getIslandsBeingCreated() { return islandsBeingCreated; }
    public void incrementIslandsBeingCreated() { islandsBeingCreated++; }
    public void setIslandsBeingCreated(int islandsBeingCreated) { this.islandsBeingCreated = islandsBeingCreated; }
}
