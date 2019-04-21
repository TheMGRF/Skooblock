package games.indigo.skooblock.commands;

import games.indigo.skooblock.Main;
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
                    if (!main.getIslandManager().playerHasIsland(player)) {
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
                    if (main.getIslandManager().isIslandResetting(player)) {
                        player.sendMessage(utils.format("&4&l(!) &cYour island is still resetting!"));
                    } else {
                        player.teleport(Main.getInstance().getUtils().getLocationAsBukkitLocation(main.getIslandManager().getPlayerIsland(player).getHome()).add(0.5, 0, 0.5));
                        player.sendMessage(utils.format("&6&l(!) &eTeleported to your island!"));
                    }
                } else if (args[0].equalsIgnoreCase("reset")) {
                    if (main.getIslandManager().playerHasIsland(player)) {
                        Cooldown cooldown = Cooldown.getCooldown(player, "islandGen");
                        if (cooldown == null || cooldown.isExpired()) {
                            player.setMetadata("resettingIsland", new FixedMetadataValue(Main.getInstance(), true));
                            main.getInstance().getIslandTypeSelector().open(player);
                        } else {
                            player.sendMessage(utils.format("&4&l(!) &cYou cannot make another island yet! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                        }
                    } else {
                        player.sendMessage(utils.format("&4&l(!) &cYou do not have an island! Use &7/is create &cto create one!"));
                    }
                } else if (args[0].equalsIgnoreCase("biome")) {
                    if (main.getIslandManager().playerHasIsland(player)) {
                        Cooldown cooldown = Cooldown.getCooldown(player, "islandBiome");
                        if (cooldown == null || cooldown.isExpired()) {
                            player.setMetadata("islandBiome", new FixedMetadataValue(Main.getInstance(), true));
                            main.getInstance().getBiomeSelector().open(player);
                        } else {
                            player.sendMessage(utils.format("&4&l(!) &cYou cannot change your biome again yet! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                        }
                    } else {
                        player.sendMessage(utils.format("&4&l(!) &cYou do not have an island! Use &7/is create &cto create one!"));
                    }
                } else if (args[0].equalsIgnoreCase("level")) {
                    // TODO: level
                    player.sendMessage("level");
                } else if (args[0].equalsIgnoreCase("setwarp")) {
                    if (main.getIslandManager().isPlayerOnHomeIsland(player)) {
                        main.getWarpsManager().setWarp(player);
                        main.getSoundsManager().success(player);
                    } else {
                        player.sendMessage(utils.format("&4&l(!) &cYou have to be on your island to set a warp!"));
                        main.getSoundsManager().error(player);
                    }
                } else if (args[0].equalsIgnoreCase("warps")) {
                    // TODO: warps
                    main.getWarpMenu().open(player, 0);
                } else if (args[0].equalsIgnoreCase("settings")) {
                    // TODO: settings
                    player.sendMessage("settings");
                } else if (args[0].equalsIgnoreCase("members")) {
                    // TODO: members
                    main.getIslandMembersMenu().open(player);
                } else if (args[0].equalsIgnoreCase("challenges")) {
                    // TODO: challenges
                    player.sendMessage("challenges");
                } else if (args[0].equalsIgnoreCase("top")) {
                    // TODO: top
                    player.sendMessage("top");
                } else {
                    // TODO: Unknown argument
                    player.sendMessage("unknown argument");
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
