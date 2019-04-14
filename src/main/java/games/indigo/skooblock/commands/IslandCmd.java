package games.indigo.skooblock.commands;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.utils.Utils;
import net.darkscorner.darkscooldown.Cooldown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class IslandCmd implements CommandExecutor {

    Main main = Main.getInstance();
    Utils utils = Main.getInstance().getUtils();

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
                    if (!SkooBlock.playerHasIsland(player)) {
                        Cooldown cooldown = Cooldown.getCooldown(player, "islandGen");
                        if (cooldown == null || cooldown.isExpired()) {
                            main.getInstance().getIslandTypeSelector().open(player);
                        } else {
                            player.sendMessage(utils.format("&4&l(!) &cYou cannot make another island yet! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                        }
                    } else {
                        player.sendMessage(utils.format("&4&l(!) &cYou already have an island! To reset use &7/" + cmd.getLabel() + " reset&c!"));
                    }
                } else if (args[0].equalsIgnoreCase("home") || args[0].equalsIgnoreCase("go") || args[0].equalsIgnoreCase("tp")) {
                    if (SkooBlock.isIslandResetting(player)) {
                        player.sendMessage(utils.format("&4&l(!) &cYour island is still resetting!"));
                    } else {
                        player.teleport(Main.getInstance().getUtils().getLocationAsBukkitLocation(SkooBlock.getPlayerIsland(player).getHome()).add(0.5, 0, 0.5));
                        player.sendMessage(utils.format("&6&l(!) &eTeleported to your island!"));
                    }
                } else if (args[0].equalsIgnoreCase("reset")) {
                    if (SkooBlock.playerHasIsland(player)) {
                        Cooldown cooldown = Cooldown.getCooldown(player, "islandGen");
                        if (cooldown == null || cooldown.isExpired()) {
                            player.setMetadata("resettingIsland", new FixedMetadataValue(Main.getInstance(), true));
                            main.getInstance().getIslandTypeSelector().open(player);
                        } else {
                            player.sendMessage(utils.format("&4&l(!) &cYou cannot make another island yet! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                        }
                    }
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
                } else {
                    // TODO: Unknown argument
                }
            }
        }
        return false;
    }

    private void help(Player player) {
        player.sendMessage(utils.format(""));
        player.sendMessage(utils.format(""));
        player.sendMessage(utils.format(""));
    }
}
