package network.jon.viralpizza.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PrismaChunkGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        int currentHeight;

        ChunkData chunk = createChunkData(world);
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        generator.setScale(0.005D);

        int chunkMod = random.nextInt(20) + 5;

        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++) {
                currentHeight = (int) ((generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true) + 1) * 15D + 50D);

                currentHeight = currentHeight % 17 + chunkMod;

                switch (biome.getBiome(1, 1, 1)) {
                    case NETHER_WASTES: {
                        if (random.nextBoolean()) {
                            chunk.setBlock(x, currentHeight, z, Material.WARPED_NYLIUM);
                        } else {
                            chunk.setBlock(x, currentHeight, z, Material.PRISMARINE_BRICKS);
                        }
                        break;
                    } case BASALT_DELTAS:
                    case SOUL_SAND_VALLEY: {
                        if (random.nextBoolean()) {
                            chunk.setBlock(x, currentHeight, z, Material.WHITE_CONCRETE);
                        } else {
                            chunk.setBlock(x, currentHeight, z, Material.QUARTZ_BLOCK);
                        }
                        break;
                    } default: {
                        if (random.nextBoolean()) {
                            chunk.setBlock(x, currentHeight, z, Material.WARPED_NYLIUM);
                        } else {
                            chunk.setBlock(x, currentHeight, z, Material.PACKED_ICE);
                        }
                    }
                }
            }
        return chunk;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(
                new PrismaOrePopulator(),
                new PrismaStructurePopulator(),
                new PrismaBossTotemPopulator(),
                new PrismaTreePopulator()
                );
    }

}
