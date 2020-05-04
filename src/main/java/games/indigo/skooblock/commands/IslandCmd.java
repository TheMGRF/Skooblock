package games.indigo.skooblock.commands;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.utils.SoundManager;
import games.indigo.skooblock.utils.Text;
import games.indigo.skooblock.utils.Utils;
import net.darkscorner.darkscooldown.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class IslandCmd implements CommandExecutor {

    private final SkooBlock skooBlock = SkooBlock.getInstance();

    // TODO: Use ACF for crying out loud

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
                if (args.length == 0) {
                    skooBlock.getMainMenu().open(player);
                } else {
                    if (args[0].equalsIgnoreCase("help")) {
                        help(player);
                    } else if (args[0].equalsIgnoreCase("create")) {
                        hasIsland(cmd, player);
                    } else if (args[0].equalsIgnoreCase("home") || args[0].equalsIgnoreCase("go") || args[0].equalsIgnoreCase("tp")) {
                        if (skooBlock.getIslandManager().isIslandResetting(player)) {
                            player.sendMessage(Text.colour("&4&l(!) &cYour island is still resetting!"));
                        } else {
                            player.teleport(SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString()).getHome()).add(0.5, 0, 0.5));
                            player.sendMessage(Text.colour("&6&l(!) &eTeleported to your island!"));
                        }
                    } else if (args[0].equalsIgnoreCase("reset")) {
                        if (skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
                            Cooldown cooldown = Cooldown.getCooldown(player, "islandGen");
                            if (cooldown == null || cooldown.isExpired()) {
                                player.setMetadata("resettingIsland", new FixedMetadataValue(SkooBlock.getInstance(), true));
                                skooBlock.getIslandTypeSelector().open(player);
                            } else {
                                player.sendMessage(Text.colour("&4&l(!) &cYou cannot make another island yet! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                            }
                        } else {
                            player.sendMessage(Text.colour("&4&l(!) &cYou do not have an island! Use &7/is create &cto create one!"));
                        }
                    } else if (args[0].equalsIgnoreCase("biome")) {
                        if (skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
                            Cooldown cooldown = Cooldown.getCooldown(player, "islandBiome");
                            if (cooldown == null || cooldown.isExpired()) {
                                player.setMetadata("islandBiome", new FixedMetadataValue(SkooBlock.getInstance(), true));
                                skooBlock.getBiomeSelector().open(player);
                            } else {
                                player.sendMessage(Text.colour("&4&l(!) &cYou cannot change your biome again yet! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                            }
                        } else {
                            player.sendMessage(Text.colour("&4&l(!) &cYou do not have an island! Use &7/is create &cto create one!"));
                        }
                    } else if (args[0].equalsIgnoreCase("level")) {
                        Cooldown cooldown = Cooldown.getCooldown(player, "island-level");
                        if (player.hasPermission("indigo.command.islevel.bypass") || cooldown == null || cooldown.isExpired()) {
                            skooBlock.getIslandLevelMenu().open(player, 0);
                            new Cooldown(player, "island-level", 300);
                        } else {
                            SoundManager.error(player);
                            player.sendMessage(Text.colour("&4&l(!) &cIsland level is still on cooldown! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
                        }
                    } else if (args[0].equalsIgnoreCase("setwarp")) {
                        if (skooBlock.getIslandManager().isPlayerOnHomeIsland(player)) {
                            skooBlock.getWarpsManager().setWarp(player);
                            SoundManager.success(player);
                        } else {
                            player.sendMessage(Text.colour("&4&l(!) &cYou have to be on your island to set a warp!"));
                            SoundManager.error(player);
                        }
                    } else if (args[0].equalsIgnoreCase("warp")) {
                        // TODO: goto warp location
                        skooBlock.getWarpMenu().open(player, 0);
                    } else if (args[0].equalsIgnoreCase("warps")) {
                        skooBlock.getWarpMenu().open(player, 0);
                    } else if (args[0].equalsIgnoreCase("description")) {
                        // TODO: description
                    } else if (args[0].equalsIgnoreCase("settings")) {
                        // TODO: settings
                        player.sendMessage("settings");
                    } else if (args[0].equalsIgnoreCase("members")) {
                        // TODO: members
                        skooBlock.getIslandMembersMenu().open(player);
                    } else if (args[0].equalsIgnoreCase("promote")) {
                        // TODO: promote
                        player.sendMessage("promote");
                    } else if (args[0].equalsIgnoreCase("demote")) {
                        // TODO: demote
                        player.sendMessage("demote");
                    } else if (args[0].equalsIgnoreCase("invite")) {
                        // TODO: invite member
                        if (args.length >= 2) {
                            String name = args[1];
                            if (Bukkit.getPlayer(name) != null) {
                                skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString()).addMember(Bukkit.getPlayer(name).getUniqueId().toString());
                                player.sendMessage(Text.colour("&2&l(!) &aInvited &f" + Bukkit.getPlayer(name).getName() + " &ato your island!"));
                                SoundManager.success(player);
                            } else {
                                player.sendMessage(Text.colour("&4&l(!) &cCould not find player by the name of &f" + name));
                                SoundManager.error(player);
                            }
                        } else {
                            player.sendMessage(Text.colour("&4&l(!) &cPlease specify a player to invite! &7/is invite <player>"));
                            SoundManager.error(player);
                        }
                    } else if (args[0].equalsIgnoreCase("challenges")) {
                        // TODO: challenges
                        player.sendMessage("challenges");
                    } else if (args[0].equalsIgnoreCase("top")) {
                        skooBlock.getIslandTopMenu().open(player);
                    } else {
                        // TODO: Unknown argument
                        player.sendMessage(Text.colour("&4&l(!) &cUnknown argument!"));
                    }
                }
            } else {
                hasIsland(cmd, player);
            }
        }
        return false;
    }

    private void hasIsland(Command cmd, Player player) {
        if (!skooBlock.getIslandManager().playerHasIsland(player.getUniqueId().toString())) {
            Cooldown cooldown = Cooldown.getCooldown(player, "islandGen");
            if (cooldown == null || cooldown.isExpired()) {
                skooBlock.getIslandTypeSelector().open(player);
            } else {
                player.sendMessage(Text.colour("&4&l(!) &cYou cannot make another island yet! &e" + cooldown.getFormattedTimeLeft() + " &cremaining!"));
            }
        } else {
            player.sendMessage(Text.colour("&4&l(!) &cYou already have an island! To reset use &7/" + cmd.getLabel() + " reset&c!"));
        }
    }

    private void help(Player player) {
        player.sendMessage(Text.colour(""));
        player.sendMessage(Text.colour(""));
        player.sendMessage(Text.colour(""));
    }
}
