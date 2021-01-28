package network.jon.viralpizza.listeners;

import org.bukkit.*;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class PrismaTreePopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int y = 0;
        int chunkX = random.nextInt(15);
        int chunkZ = random.nextInt(15);
        while (chunk.getBlock(chunkX, y, chunkZ).getType() == Material.AIR) y++;
        y++;
        Location location = new Location(world, chunk.getX() * 16 + chunkX, y, chunk.getZ() * 16 + chunkZ);
        if (random.nextBoolean() && random.nextBoolean())
            world.generateTree(location, TreeType.WARPED_FUNGUS);
    }

}
