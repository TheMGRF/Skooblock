package games.indigo.skooblock.utils;

import games.indigo.skooblock.Main;
import games.indigo.skooblock.island.UserIsland;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_13_R2.WorldBorder;
import net.minecraft.server.v1_13_R2.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class WorldBorderManager {

    private Main main = Main.getInstance();

    public void applyBorder(Player player) {
        if (main.getIslandManager().getIslandPlayerIsOn(player) != null) {
            if (main.getIslandManager().isPlayerOnHomeIsland(player) || main.getIslandManager().isPlayerMember(player, main.getIslandManager().getIslandPlayerIsOn(player))) {
                UserIsland userIsland = main.getIslandManager().getPlayerIsland(player);
                Location centre = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre());

                new WorldBorderManager().sendBorder(player, centre.getX(), centre.getZ(), Main.getInstance().getIslandGenerator().getIslandSize() / 2);
                return;
            } else {
                if (main.getIslandManager().getIslandPlayerIsOn(player) == null) {
                    new WorldBorderManager().sendBorder(player, 0, 0, Integer.MAX_VALUE);
                    return;
                } else {
                    UserIsland userIsland = main.getIslandManager().getIslandPlayerIsOn(player);
                    Location centre = Main.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre());

                    new WorldBorderManager().sendBorder(player, centre.getX(), centre.getZ(), Main.getInstance().getIslandGenerator().getIslandSize() / 2);
                    return;
                }
            }
        }
    }

    private void sendBorder(Player p, double x, double z, double radius) {
        EntityPlayer entityPlayer = ((CraftPlayer) p).getHandle();
        WorldBorder worldBorder = new WorldBorder();
        worldBorder.setCenter(x, z);
        worldBorder.setSize(radius);
        worldBorder.setWarningDistance(0);
        worldBorder.world = ((WorldServer) entityPlayer.world);

        setBorder(entityPlayer, worldBorder);
    }

    private void setBorder(EntityPlayer entityPlayer, WorldBorder worldBorder) {
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE));
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER));
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS));
    }

}
