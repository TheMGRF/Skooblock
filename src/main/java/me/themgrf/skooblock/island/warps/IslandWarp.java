package me.themgrf.skooblock.island.warps;

import org.bukkit.Location;

public class IslandWarp {

    private String uuid, description;
    private Location location;

    /**
     * Constructor to create an island warp
     * @param uuid The uuid of the island warp owner
     * @param description The description given the island warp
     * @param location The location of the island warp
     */
    public IslandWarp(String uuid, String description, Location location) {
        this.uuid = uuid;
        this.description = description;
        this.location = location;
    }

    /**
     * Get the island warp owner's UUID
     * @return The island warp owner's UUID
     */
    public String getOwnerUuid() {
        return uuid;
    }

    /**
     * Get the description of the island warp
     * @return The description of the island warp
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the location of the island warp
     * @return The location of the island warp
     */
    public Location getLocation() {
        return location.add(0.5, 0, 0.5);
    }

    /**
     * Set the island warp location
     * @param location
     */
    public void setLocation(Location location) { this.location = location; }
}
