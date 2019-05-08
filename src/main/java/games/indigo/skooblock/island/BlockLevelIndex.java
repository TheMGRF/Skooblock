package games.indigo.skooblock.island;

import org.bukkit.Material;

import java.util.HashMap;

public class BlockLevelIndex {

    private HashMap<Material, Integer> blockIndex;

    public BlockLevelIndex(HashMap<Material, Integer> blockIndex) {
        this.blockIndex = blockIndex;
    }

    public int getBlockWorth(Material material) {
        for (Material loop : blockIndex.keySet()) {
            if (loop == material) {
                return blockIndex.get(loop).intValue();
            }
        }
        return 0;
    }
}
