package games.indigo.skooblock.island.biomes;

import games.indigo.skooblock.SkooBlock;
import games.indigo.skooblock.island.UserIsland;
import games.indigo.skooblock.utils.PacketWorldChunk;
import games.indigo.skooblock.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BiomeManager {

    Utils utils = SkooBlock.getInstance().getUtils();

    public void setIslandBiome(UserIsland userIsland, IslandBiome islandBiome) {
        Location loc1 = utils.getLocationAsBukkitLocation(userIsland.getLowerBound());
        loc1.setY(64);
        Location loc2 = utils.getLocationAsBukkitLocation(userIsland.getUpperBound());
        loc2.setY(64);

        for (Location loc : SkooBlock.getInstance().getUtils().getBlocksInRegion(loc1, loc2)) {
            loc.getBlock().setBiome(islandBiome.getBiome());

            PacketWorldChunk packetWorldChunk = new PacketWorldChunk(loc.getChunk());
            for (Player player : userIsland.getPlayersOnIsland().keySet()) {
                packetWorldChunk.send(player);
            }
        }
    }

    public IslandBiome getIslandBiome(UserIsland userIsland) {
        IslandBiome islandBiome = getIslandBiomeFromID(SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre()).getBlock().getBiome().name().toLowerCase());
        return islandBiome != null ? islandBiome : getDefaultIslandBiome();
    }

    public IslandBiome getDefaultIslandBiome() {
        return getIslandBiomeFromID("plains");
    }

    public IslandBiome getIslandBiomeFromID(String identifier) {
        for (IslandBiome islandBiome : SkooBlock.getInstance().getIslandBiomes()) {
            if (islandBiome.getIdentifier().equals(identifier)) {
                return islandBiome;
            }
        }
        return null;
    }

}
