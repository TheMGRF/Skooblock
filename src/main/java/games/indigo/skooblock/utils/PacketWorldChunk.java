package games.indigo.skooblock.utils;

import net.minecraft.server.v1_13_R2.PacketPlayOutMapChunk;
import net.minecraft.server.v1_13_R2.PacketPlayOutUnloadChunk;
import net.minecraft.server.v1_13_R2.PlayerConnection;
import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_13_R2.CraftChunk;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketWorldChunk {

    private PacketPlayOutMapChunk packet;
    private PacketPlayOutUnloadChunk unload;

    public PacketWorldChunk(Chunk chunk) {
        unload = new PacketPlayOutUnloadChunk(chunk.getX(), chunk.getZ());
        packet = new PacketPlayOutMapChunk(((CraftChunk) chunk).getHandle(), 65535);
    }

    public void send(Player player) {
        PlayerConnection conn = ((CraftPlayer) player).getHandle().playerConnection;
        conn.sendPacket(unload);
        conn.sendPacket(packet);
    }

}
