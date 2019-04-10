package games.indigo.skooblock;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Utils {

    public ItemStack buildItem(Material material, int amount, String name, List<String> description) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(format(name));
        itemMeta.setLore(formatArray(description));

        item.setItemMeta(itemMeta);

        return item;
    }

    public String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public List<String> formatArray(List<String> msgs) {
        for (int x = 0; x < msgs.size(); x++) {
            msgs.set(x, format(msgs.get(x)));
        }
        return msgs;
    }

}
