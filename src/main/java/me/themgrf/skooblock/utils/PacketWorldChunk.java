package me.themgrf.skooblock.utils;

import net.minecraft.server.v1_15_R1.PacketPlayOutMapChunk;
import net.minecraft.server.v1_15_R1.PacketPlayOutUnloadChunk;
import net.minecraft.server.v1_15_R1.PlayerConnection;
import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_15_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketWorldChunk {

    private final PacketPlayOutMapChunk packet;
    private final PacketPlayOutUnloadChunk unload;

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
