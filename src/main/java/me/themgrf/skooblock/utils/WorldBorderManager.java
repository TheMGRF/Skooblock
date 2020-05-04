package me.themgrf.skooblock.utils;

import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.UserIsland;
import net.minecraft.server.v1_15_R1.EntityPlayer;
import net.minecraft.server.v1_15_R1.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_15_R1.WorldBorder;
import net.minecraft.server.v1_15_R1.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class WorldBorderManager {

    private final SkooBlock skooBlock = SkooBlock.getInstance();

    public void applyBorder(Player player) {
        if (skooBlock.getIslandManager().getIslandPlayerIsOn(player) != null) {
            if (skooBlock.getIslandManager().isPlayerOnHomeIsland(player) || skooBlock.getIslandManager().isPlayerMember(player, skooBlock.getIslandManager().getIslandPlayerIsOn(player))) {
                UserIsland userIsland = skooBlock.getIslandManager().getPlayerIsland(player.getUniqueId().toString());
                Location centre = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre());

                centre = centre.add(0.5, 0, 0.5);

                sendBorder(player, centre.getX(), centre.getZ(), SkooBlock.getInstance().getIslandGenerator().getIslandSize() + 1);
            } else {
                if (skooBlock.getIslandManager().getIslandPlayerIsOn(player) == null) {
                    sendBorder(player, 0, 0, 20001);
                } else {
                    UserIsland userIsland = skooBlock.getIslandManager().getIslandPlayerIsOn(player);
                    Location centre = SkooBlock.getInstance().getUtils().getLocationAsBukkitLocation(userIsland.getCentre());

                    centre = centre.add(0.5, 0, 0.5);

                    sendBorder(player, centre.getX(), centre.getZ(), SkooBlock.getInstance().getIslandGenerator().getIslandSize() + 1);
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
