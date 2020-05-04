package me.themgrf.skooblock.island;

import org.bukkit.Material;

import java.util.HashMap;

public class BlockLevelIndex {

    private final HashMap<Material, Integer> blockIndex;

    public BlockLevelIndex(HashMap<Material, Integer> blockIndex) {
        this.blockIndex = blockIndex;
    }

    public int getBlockWorth(Material material) {
        return blockIndex.get(material);
    }
}
