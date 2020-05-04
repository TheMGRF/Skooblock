package me.themgrf.skooblock.island.biomes;

import org.bukkit.Material;
import org.bukkit.block.Biome;

public class IslandBiome {

    private final String identifier, friendlyName, description, costIdentifier;
    private final Material icon;
    private final int cost;

    /**
     * Create a new island biome
     * @param identifier The identifier name for the biome
     * @param icon The item icon that should be used for the biome
     * @param friendlyName The user friendly name for the biome
     * @param description The basic description for the biome
     * @param costIdentifier The cost identifier in dollars or crystals
     * @param cost The cost of using the biome
     */
    public IslandBiome(String identifier, Material icon, String friendlyName, String description, String costIdentifier, int cost) {
        this.identifier = identifier;
        this.icon = icon;
        this.friendlyName = friendlyName;
        this.description = description;
        this.costIdentifier = costIdentifier;
        this.cost = cost;
    }

    /**
     * Get the identifier name for the biome
     * @return The identifier name for the biome
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Get the item icon that should be used for the biome
     * @return The item icon that should be used for the biome
     */
    public Material getIcon() {
        return icon;
    }

    /**
     * Get the user friendly name for the biome
     * @return The user friendly name for the biome
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * Get the basic description for the biome
     * @return The basic description for the biome
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the cost identifier in dollars or crystals
     * @return The cost identifier in dollars or crystals
     */
    public String getCostIdentifier() {
        return costIdentifier;
    }

    /**
     * Get the cost of using the biome
     * @return The cost of using the biome
     */
    public int getCost() {
        return cost;
    }

    /**
     * Get the Bukkit biome value
     * @return The Bukkit biome value
     */
    public Biome getBiome() { return Biome.valueOf(getIdentifier().toUpperCase()); }
}
