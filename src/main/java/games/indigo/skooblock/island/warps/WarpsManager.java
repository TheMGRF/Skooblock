package games.indigo.skooblock.island.warps;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.UserIsland;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarpsManager {

    private SkooBlock skooBlock = SkooBlock.getInstance();

    public List<IslandWarp> getAllWarps() {
        List<IslandWarp> warps = new ArrayList<>();

        for (UserIsland userIsland : skooBlock.getIslandManager().getAllPlayerIslands()) {
            if (!userIsland.getWarp().equals("0,0,0")) {
                Location loc = skooBlock.getUtils().getLocationAsBukkitLocation(userIsland.getWarp());
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

    public IslandWarp getWarpByUUID(String uuid) {
        for (IslandWarp islandWarp : getAllWarps()) {
            if (islandWarp.getOwnerUuid().equals(uuid)) {
                return islandWarp;
            }
        }
        return null;
    }

    public void setWarp(Player player) {
        UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
        String uuid = player.getUniqueId().toString();

        Location loc = player.getLocation();

        FileConfiguration config = skooBlock.getConfigManager().getUserConfig(uuid);
        config.set("warp", (int) loc.getX() + "," + (int) loc.getY() + "," + (int) loc.getZ());
        try {
            config.save(skooBlock.getConfigManager().getUserFile(uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }

        userIsland.setWarp((int) loc.getX() + "," + (int) loc.getY() + "," + (int) loc.getZ());
        player.sendMessage(skooBlock.getUtils().format("&2&l(!) &aIsland warp location set to &f" + (int) loc.getX() + ", " + (int) loc.getY() + ", " + (int) loc.getZ() + "&a!"));
    }
}
