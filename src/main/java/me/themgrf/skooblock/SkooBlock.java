package me.themgrf.skooblock;

import me.themgrf.skooblock.island.Island;
import me.themgrf.skooblock.island.IslandGenerator;
import me.themgrf.skooblock.island.IslandManager;
import me.themgrf.skooblock.island.biomes.BiomeManager;
import me.themgrf.skooblock.island.biomes.IslandBiome;
import me.themgrf.skooblock.island.roles.IslandMemberRoleManager;
import me.themgrf.skooblock.island.warps.WarpsManager;
import me.themgrf.skooblock.utils.Utils;
import me.themgrf.skooblock.utils.WorldBorderManager;
import me.themgrf.skooblock.island.BlockLevelIndex;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.*;

public class SkooBlock {

    // Variable Declaration
    public static SkooBlock instance;

    // Managers
    private final Utils utils;
    private IslandGenerator islandGenerator;

    private final IslandManager islandManager;
    private final BiomeManager biomeManager;
    private final WarpsManager warpsManager;
    private final WorldBorderManager worldBorderManager;
    private final IslandMemberRoleManager islandMemberRoleManager;

    public SkooBlock() {
        utils = new Utils();
        islandGenerator = new IslandGenerator();
        islandManager = new IslandManager();
        biomeManager = new BiomeManager();
        warpsManager = new WarpsManager();
        worldBorderManager = new WorldBorderManager();
        islandMemberRoleManager = new IslandMemberRoleManager();islandGenerator = new IslandGenerator();
    }

    public static SkooBlock getInstance() {
        return instance;
    }

    // Managers
    public Utils getUtils() {
        return utils;
    }

    public IslandGenerator getIslandGenerator() {
        return islandGenerator;
    }

    public List<Island> getIslandTypes() {
        return Main.getInstance().islands;
    }

    public List<IslandBiome> getIslandBiomes() {
        return Main.getInstance().biomes;
    }

    public IslandManager getIslandManager() {
        return islandManager;
    }

    public BiomeManager getBiomeManager() {
        return biomeManager;
    }

    public WarpsManager getWarpsManager() {
        return warpsManager;
    }

    public WorldBorderManager getWorldBorderManager() {
        return worldBorderManager;
    }

    public IslandMemberRoleManager getIslandMemberRoleManager() {
        return islandMemberRoleManager;
    }

    public BlockLevelIndex getBlockLevelIndex() {
        return Main.getInstance().blockLevelIndex;
    }

    /**
     * Get the server spawn location
     *
     * @return The servers spawn location
     */
    public static Location getSpawnLocation() {
        String[] locs = Main.getInstance().getConfigManager().config.getString("spawn").split(",");
        return new Location(Bukkit.getWorld("world"), Integer.parseInt(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]));
    }
}
