package games.indigo.skooblock.island.warps;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.island.UserIsland;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarpsManager {

    Main main = Main.getInstance();

    public List<IslandWarp> getAllWarps() {
        List<IslandWarp> warps = new ArrayList<>();

        for (UserIsland userIsland : main.getIslandManager().getAllPlayerIslands()) {
            if (!userIsland.getWarp().equals("0,0,0")) {
                Location loc = main.getUtils().getLocationAsBukkitLocation(userIsland.getWarp());
                warps.add(new IslandWarp(userIsland.getOwner(), userIsland.getDescription(), loc));
            }
        }

        return warps;
    }

    public IslandWarp getWarp(UserIsland userIsland) {
        for (IslandWarp islandWarp : getAllWarps()) {
            if (islandWarp.getOwnerUuid().equals(userIsland.getOwner())) {
                return islandWarp;
            }
        }

        return null;
    }

    public void setWarp(Player player) {
        UserIsland userIsland = main.getIslandManager().getPlayerIsland(player);

        Location loc = player.getLocation();
        main.getConfigManager().getUserConfig(player).set("warp", (int) loc.getX() + "," + (int) loc.getY() + "," + (int) loc.getZ());
        try {
            if (main.getConfigManager().getUserFile(player) != null) {
                main.getConfigManager().getUserConfig(player).save(main.getConfigManager().getUserFile(player));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (IslandWarp islandWarp : getAllWarps()) {
            if (islandWarp.getOwnerUuid().equals(player.getUniqueId().toString())) {
                islandWarp.setLocation(loc);
            }
        }

        player.sendMessage(main.getUtils().format("&2&l(!) &aIsland warp location set to &f" + (int) loc.getX() + "," + (int) loc.getY() + "," + (int) loc.getZ() + "&a!"));
    }

}
