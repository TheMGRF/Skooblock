package me.themgrf.skooblock.island;

import org.bukkit.Material;

public class Island {

    private final String identifier, friendlyName, description, costIdentifier;
    private final Material icon;
    private final int cost;
    private final IslandType islandType;

    /**
     * Create a new island base
     * @param identifier The identifier name for the island
     * @param icon The item icon that should be used for the island
     * @param friendlyName The user friendly name for the island
     * @param description The basic description for the island
     * @param costIdentifier The cost identifier in dollars or crystals
     * @param cost The cost of using the island
     * @param islandType The type of the island
     */
    public Island(String identifier, Material icon, String friendlyName, String description, String costIdentifier, int cost, IslandType islandType) {
        this.identifier = identifier;
        this.icon = icon;
        this.friendlyName = friendlyName;
        this.description = description;
        this.costIdentifier = costIdentifier;
        this.cost = cost;
        this.islandType = islandType;
    }

    /**
     * Get the identifier name for the island
     * @return The identifier name for the island
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Get the item icon that should be used for the island
     * @return The item icon that should be used for the island
     */
    public Material getIcon() {
        return icon;
    }

    /**
     * Get the user friendly name for the island
     * @return The user friendly name for the island
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * Get the basic description for the island
     * @return The basic description for the island
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
     * Get the cost of using the island
     * @return The cost of using the island
     */
    public int getCost() {
        return cost;
    }

    /**
     * Get the type of the island
     * @return The type of the island
     */
    public IslandType getIslandType() {
        return islandType;
    }
}
