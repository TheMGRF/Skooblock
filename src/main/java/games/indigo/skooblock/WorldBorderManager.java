package games.indigo.skooblock;

import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_13_R2.WorldBorder;
import net.minecraft.server.v1_13_R2.WorldServer;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class WorldBorderManager {

    public void sendBorder(Player p, double x, double z, double radius) {
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
