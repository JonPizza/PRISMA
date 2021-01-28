package network.jon.viralpizza.listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class PrismaOrePopulator extends BlockPopulator {

    private void setChunkBlock(Chunk chunk, int x, int y, int z, Material type) {
        chunk.getWorld().getBlockAt(new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).setType(type);
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {

        if (random.nextInt(50) == 6) {
            int y = random.nextInt(20) + 40;
            int endY = random.nextInt(20) + 100;
            for (; y < endY; y++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        if (random.nextBoolean() && random.nextBoolean()) {
                            setChunkBlock(chunk, x, y, z, Material.PRISMARINE);
                            if (random.nextInt(150) == 1) {
                                setChunkBlock(chunk, x, y, z, Material.EMERALD_ORE);
                            }
                        }
                    }
                }
            }
        }

    }

}
