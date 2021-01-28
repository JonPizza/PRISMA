package network.jon.viralpizza.listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class PrismaBossTotemPopulator extends BlockPopulator {

    private void setChunkBlock(Chunk chunk, int x, int y, int z, Material type) {
        chunk.getWorld().getBlockAt(new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).setType(type);
    }

    private void createPillar(Chunk chunk, int x, int z, Material type) {
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                int y = 0;
                while (chunk.getWorld().getBlockAt(chunk.getX() * 16 + x + i, y, chunk.getZ() * 16 + z + i2).getType() == Material.AIR)
                    y++;
                y++;
                while (chunk.getWorld().getBlockAt(chunk.getX() * 16 + x + i, y, chunk.getZ() * 16 + z + i2).getType() == Material.AIR) {
                    chunk.getWorld().getBlockAt(new Location(chunk.getWorld(), chunk.getX() * 16 + x + i, y, chunk.getZ() * 16 + z + i2)).setType(type);
                    y++;
                }
            }
        }
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextInt(3000) == 69) {
            int y = 0;
            while (world.getBlockAt(new Location(world, chunk.getX() * 16, y, chunk.getZ() * 16)).getType() == Material.AIR) y++;
            y += 30;
            for (int x = 0; x < 32; x++) {
                for (int z = 0; z < 32; z++) {
                    setChunkBlock(chunk, x, y, z, Material.OBSIDIAN);
                    if (x == 0 || z == 0 || x == 31 || z == 31) {
                        setChunkBlock(chunk, x, y + 1, z, Material.POLISHED_BLACKSTONE_BRICKS);
                        setChunkBlock(chunk, x, y + 2, z, Material.POLISHED_BLACKSTONE_BRICKS);
                        if ((x % 2 == 1 && z % 2 == 1) || (x % 2 == 0 && z % 2 == 0)) {
                            setChunkBlock(chunk, x, y + 3, z, Material.POLISHED_BLACKSTONE_BRICKS);
                        }
                    }
                }
            }
            setChunkBlock(chunk, 16, y, 16, Material.BEDROCK);
            setChunkBlock(chunk, 15, y + 1, 16, Material.GOLD_BLOCK);
            setChunkBlock(chunk, 15, y + 1, 15, Material.GOLD_BLOCK);
            setChunkBlock(chunk, 16, y + 1, 15, Material.GOLD_BLOCK);
            setChunkBlock(chunk, 17, y + 1, 16, Material.GOLD_BLOCK);
            setChunkBlock(chunk, 16, y + 1, 17, Material.GOLD_BLOCK);
            setChunkBlock(chunk, 17, y + 1, 17, Material.GOLD_BLOCK);

            createPillar(chunk, 1, 1, Material.POLISHED_BLACKSTONE_BRICKS);
            createPillar(chunk, 1, 27, Material.POLISHED_BLACKSTONE_BRICKS);
            createPillar(chunk, 27, 1, Material.POLISHED_BLACKSTONE_BRICKS);
            createPillar(chunk, 27, 27, Material.POLISHED_BLACKSTONE_BRICKS);
        }
    }

}
