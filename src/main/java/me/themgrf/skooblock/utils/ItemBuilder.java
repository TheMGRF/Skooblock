package me.themgrf.skooblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ItemBuilder {

    private ItemStack itemStack;
    private Material material = Material.STONE;
    private String name;
    private List<String> lore = new ArrayList<>();
    private HashMap<Enchantment, Integer> enchants = new HashMap<>();
    private int amount = 1, customModelData = 0;

    /**
     * Get the itemstack of the item
     * @param itemStack The itemstack of the item
     * @return The item stack with specified itemstack
     */
    public ItemBuilder itemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    /**
     * Set the material of the item
     * @param material The material of the item
     * @return The item stack with the specified material
     */
    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Set the name of the item
     * @param name The name of the item
     * @return The item stack with the specified name
     */
    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the lore of the item
     * @param lore The lore of the item
     * @return The item stack with the specified lore
     */
    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Set the lore of the item
     * @param lore The lore of the item
     * @return The item stack with the specified lore
     */
    public ItemBuilder lore(String... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    /**
     * Set the enchantments of the item
     * @param enchants The enchantments and their levels for the item
     * @return The item stack with the specified enchantments
     */
    public ItemBuilder enchants(HashMap<Enchantment, Integer> enchants) {
        this.enchants = enchants;
        return this;
    }

    /**
     * Set the amount of the item
     * @param amount The amount of the item
     * @return The item stack with the specified amount
     */
    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Set the custom model data of the item
     * @param customModelData The custom model data of the item
     * @return The item stack with the specified custom model data
     */
    public ItemBuilder customModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    /**
     * Build the item
     * @return The built item with defined attributes
     */
    public ItemStack build() {
        if (material == null) return null;
        ItemStack item = itemStack != null ? itemStack : new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        if (!name.isEmpty()) meta.setDisplayName(Text.colour(name));
        if (!lore.isEmpty()) meta.setLore(Text.colourArray(lore));
        if (customModelData > 0) meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);

        return item;
    }

    public static class Skulls {

        /**
         * Build a player head for the warp GUI
         * @param uuid The UUID for for the player to use
         * @param description The island description for the player head
         * @return The itemstack player head
         */
        public static ItemStack buildWarpHead(UUID uuid, List<String> description) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

            ItemStack item = new ItemBuilder()
                    .material(Material.PLAYER_HEAD)
                    .name("&a" + offlinePlayer.getName() + "'s Island")
                    .lore(description)
                    .build();

            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setOwningPlayer(offlinePlayer);
            item.setItemMeta(skullMeta);

            return item;
        }

        /**
         * Create a top player item head
         * @param uuid The UUID of the player to use
         * @return The itemstack player head
         */
        public static ItemStack buildTopHead(UUID uuid) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setOwningPlayer(offlinePlayer);
            item.setItemMeta(skullMeta);

            return item;
        }
    }

}
