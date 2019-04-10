package games.indigo.skooblock.commands;

import games.indigo.skooblock.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IslandCmd implements CommandExecutor {

    Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                main.getMainMenu().open(player);
            } else {
                if (args[0].equalsIgnoreCase("help")) {
                    help(player);
                } else if (args[0].equalsIgnoreCase("create")) {
                    // TODO: create
                } else if (args[0].equalsIgnoreCase("setbiome")) {
                    // TODO: setbiome
                } else if (args[0].equalsIgnoreCase("level")) {
                    // TODO: level
                } else if (args[0].equalsIgnoreCase("warps")) {
                    // TODO: warps
                } else if (args[0].equalsIgnoreCase("settings")) {
                    // TODO: settings
                } else if (args[0].equalsIgnoreCase("members")) {
                    // TODO: members
                } else if (args[0].equalsIgnoreCase("challenges")) {
                    // TODO: challenges
                } else if (args[0].equalsIgnoreCase("top")) {
                    // TODO: top
                }
            }
        }
        return false;
    }

    private void help(Player player) {
        player.sendMessage(main.getUtils().format(""));
        player.sendMessage(main.getUtils().format(""));
        player.sendMessage(main.getUtils().format(""));
    }
}
