package games.indigo.skooblock.commands;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkooblockCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("skooblock.admin")) {
                if (args.length == 0) {
                    player.sendMessage("admin help menu");
                } else {
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage("admin help menu");
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        SkooBlock.getInstance().reload();
                        player.sendMessage(Text.colour("&2&l(!) &aReloaded all configuration files!"));
                    }
                }
            } else {
                player.sendMessage(Text.colour("&cYou do not have access to this command!"));
            }
        }
        return true;
    }

}
