package games.indigo.skooblock.utils;

import org.bukkit.Material;

public abstract class ConfigReadable {

    private String identifier;
    private Material icon;
    private String friendlyName;
    private String description;
    private String costIdentifier;
    private int cost;

    /**
     * Create a new island base
     * @param identifier The identifier name for the island
     * @param icon The item icon that should be used for the island
     * @param friendlyName The user friendly name for the island
     * @param description The basic description for the island
     * @param costIdentifier The cost identifier in dollars or crystals
     * @param cost The cost of using the island
     */
    public ConfigReadable(String identifier, Material icon, String friendlyName, String description, String costIdentifier, int cost) {
        this.identifier = identifier;
        this.icon = icon;
        this.friendlyName = friendlyName;
        this.description = description;
        this.costIdentifier = costIdentifier;
        this.cost = cost;
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
}
