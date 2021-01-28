package network.jon.viralpizza.listeners;

import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootTables;

import java.util.Random;

public class PrismaStructurePopulator extends BlockPopulator {

    private void setChunkBlock(Chunk chunk, int x, int y, int z, Material type) {
        chunk.getWorld().getBlockAt(new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).setType(type);
    }

    private void placeSpawnerInChunk(Chunk chunk, int x, int y, int z, EntityType entityType) {
        chunk.getWorld().getBlockAt(
                new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).setType(Material.SPAWNER);
        CreatureSpawner spawner = (CreatureSpawner) chunk.getWorld().getBlockAt(
                new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).getState();
        spawner.setSpawnedType(entityType);
        spawner.update();
    }

    private void placeLootChest(Chunk chunk, int x, int y, int z, Random random) {
        chunk.getWorld().getBlockAt(
                new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).setType(Material.CHEST);
        Chest chest = (Chest) chunk.getWorld().getBlockAt(
                new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).getState();
        chest.setLootTable(LootTables.END_CITY_TREASURE.getLootTable());
        chest.update();
    }

    private void placeOPLootChest(Chunk chunk, int x, int y, int z, Random random) {
        chunk.getWorld().getBlockAt(
                new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).setType(Material.CHEST);
        Chest chest = (Chest) chunk.getWorld().getBlockAt(
                new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16 + z)).getState();
        Inventory chestInv = chest.getInventory();
        chest.setLootTable(LootTables.END_CITY_TREASURE.getLootTable());
        chest.update();
        if (random.nextBoolean()) {
            chestInv.setItem(14, LeapingBoots.getLeapingBoots(random));
        } else {
            ItemStack bedrock = new ItemStack(Material.BEDROCK);
            ItemMeta bedrockMeta = bedrock.getItemMeta();
            bedrockMeta.setDisplayName(ChatColor.YELLOW + "Oddstone");
            bedrock.setItemMeta(bedrockMeta);
            chestInv.setItem(14, bedrock);
        }
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextInt(500) == 69) {
            int y = 0;
            while (world.getBlockAt(new Location(world, chunk.getX() * 16, y, chunk.getZ() * 16)).getType() == Material.AIR) y++;
            y++;

            int maxHeight = (random.nextInt(20) + 5) * 5 + y;

            for (; y < maxHeight; y += 5) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        if (random.nextInt(10) == 5) {
                            setChunkBlock(chunk, x, y, z, Material.MOSSY_STONE_BRICKS);
                        } else {
                            setChunkBlock(chunk, x, y, z, Material.STONE_BRICKS);
                        }
                    }
                }

                for (int y2 = y; y2 < y + 5; y2++) {
                    setChunkBlock(chunk, 15, y2, 15, Material.STONE_BRICKS);
                    setChunkBlock(chunk, 15, y2, 0, Material.STONE_BRICKS);
                    setChunkBlock(chunk, 0, y2, 15, Material.STONE_BRICKS);
                    setChunkBlock(chunk, 0, y2, 0, Material.STONE_BRICKS);
                }

                switch (random.nextInt(5)) {
                    case 0: {
                        for (int x = 0; x < 16; x++) {
                            for (int z = 0; z < 16; z++) {
                                if (x == 0 || x == 15 || z == 0 || z == 15) {
                                    setChunkBlock(chunk, x, y + 1, z, Material.BLACKSTONE);
                                } else {
                                    if (random.nextBoolean() && random.nextBoolean())
                                        setChunkBlock(chunk, x, y + 1, z, Material.TNT);
                                }
                            }
                        }
                        break;
                    }
                    case 1: {
                        for (int x = 0; x < 16; x++) {
                            for (int z = 0; z < 16; z++) {
                                if (x == 0 || x == 15 || z == 0 || z == 15) {
                                    setChunkBlock(chunk, x, y + 1, z, Material.STONE_BRICKS);
                                } else {
                                    setChunkBlock(chunk, x, y + 1, z, Material.LAVA);
                                }
                            }
                        }
                        break;
                    }
                    case 2: {
                        for (int x = 0; x < 16; x++) {
                            for (int z = 0; z < 16; z++) {
                                if (x == 0 || x == 15 || z == 0 || z == 15) {
                                    setChunkBlock(chunk, x, y + 1, z, Material.WARPED_HYPHAE);
                                    setChunkBlock(chunk, x, y + 4, z, Material.WARPED_HYPHAE);
                                }
                            }
                        }
                        placeSpawnerInChunk(chunk, 7, y + 1, 7, EntityType.PHANTOM);
                        if (random.nextInt(5) == 1)
                            placeLootChest(chunk, 8, y + 1, 8, random);
                        break;
                    }
                    case 3: {
                        for (int x = 0; x < 16; x++) {
                            for (int z = 0; z < 16; z++) {
                                if (x == 0 || x == 15 || z == 0 || z == 15) {
                                    setChunkBlock(chunk, x, y + 1, z, Material.CRIMSON_HYPHAE);
                                    setChunkBlock(chunk, x, y + 4, z, Material.CRIMSON_HYPHAE);
                                }
                            }
                        }
                        placeSpawnerInChunk(chunk, 7, y + 1, 7, EntityType.CREEPER);
                        if (random.nextInt(5) == 1)
                            placeLootChest(chunk, 8, y + 1, 8, random);
                        break;
                    }
                    case 4: {
                        if (y > 100) {
                            for (int x = 0; x < 16; x++) {
                                for (int z = 0; z < 16; z++) {
                                    if (x == 0 || x == 15 || z == 0 || z == 15) {
                                        setChunkBlock(chunk, x, y + 1, z, Material.BLACKSTONE);
                                        setChunkBlock(chunk, x, y + 2, z, Material.GLASS);
                                        setChunkBlock(chunk, x, y + 3, z, Material.GLASS);
                                        setChunkBlock(chunk, x, y + 4, z, Material.BLACKSTONE);
                                    }
                                }
                                setChunkBlock(chunk, 7, y + 1, 7, Material.DIAMOND_ORE);
                                setChunkBlock(chunk, 8, y + 1, 7, Material.DIAMOND_ORE);
                                setChunkBlock(chunk, 8, y + 1, 8, Material.DIAMOND_ORE);
                                setChunkBlock(chunk, 7, y + 2, 7, Material.DIAMOND_ORE);
                            }
                            placeOPLootChest(chunk, 7, y + 1, 8, random);
                            placeLootChest(chunk, 12, y + 1, 12, random);
                            placeLootChest(chunk, 3, y + 1, 3, random);
                        } else {
                            setChunkBlock(chunk, 7, y + 1, 7, Material.REDSTONE_BLOCK);
                            setChunkBlock(chunk, 7, y + 1, 8, Material.REDSTONE_BLOCK);
                            setChunkBlock(chunk, 8, y + 1, 7, Material.REDSTONE_BLOCK);
                            setChunkBlock(chunk, 8, y + 1, 8, Material.REDSTONE_BLOCK);
                        }
                    }
                }
            }
        }
    }

}
