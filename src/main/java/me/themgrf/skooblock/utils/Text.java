package me.themgrf.skooblock.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * A simple string modification class to colour or reformat text
 */
public class Text {

    /**
     * Format a string to contain Bukkit colour codes
     *
     * @param msg The message to format
     * @return The formatted colour coded string
     */
    public static String colour(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Format an array to contain Bukkit colour codes
     *
     * @param lore The array to format
     * @return The formatted colour coded array
     */
    public static List<String> colourArray(List<String> lore) {
        for (int x = 0; x < lore.size(); x++) {
            lore.set(x, colour(lore.get(x)));
        }
        return lore;
    }

    /**
     * Shortened method for grabbing an items name from Paper's API if you dont know what i18n is.
     * @param itemStack The item to get the name of
     * @return The name of the specified item
     */
    public static String getItemName(ItemStack itemStack) {
        return itemStack.getI18NDisplayName();
    }

    /**
     * Convert a bukkit string value to a readable string
     *
     * @param old The old string value to convert
     * @return The readable string value
     */
    public static String convertUnformattedString(String old) {

        String convert = old.toLowerCase().replaceAll("_", " ");
        StringBuilder newValue = new StringBuilder();

        boolean space = true;
        for (int i = 0; i < convert.length(); i++) {
            if (space) {
                newValue.append(Character.toUpperCase(convert.charAt(i)));
            } else {
                newValue.append(convert.charAt(i));
            }
            space = convert.charAt(i) == ' ';
        }

        return newValue.toString();
    }
}