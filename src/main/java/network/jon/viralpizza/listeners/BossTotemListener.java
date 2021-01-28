package network.jon.viralpizza.listeners;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftGiant;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BossTotemListener implements Listener {

    private void spawnBoss(Location location) {
        EntityGiantZombie giant = ((CraftGiant) location.getWorld().spawnEntity(location, EntityType.GIANT)).getHandle();

        giant.goalSelector.a(8, new PathfinderGoalLookAtPlayer(giant, EntityHuman.class, 0.0f));
        giant.goalSelector.a(8, new PathfinderGoalRandomLookaround(giant));
        giant.goalSelector.a(7, new PathfinderGoalRandomStrollLand(giant, 0.5));
        giant.goalSelector.a(1, new PathfinderGoalMeleeAttack(giant, 0.5, false));
        giant.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(giant, EntityHuman.class, true));
        giant.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(giant, EntityIronGolem.class, true));

        Giant bukkitGiant = (Giant) giant.getBukkitEntity();

        // deprecated - oh well, i don't care xD
        bukkitGiant.setMaxHealth(100);
        bukkitGiant.setHealth(100);

        bukkitGiant.getEquipment().setItemInMainHand(new ItemStack(Material.GOLDEN_SWORD));
        bukkitGiant.getEquipment().setItemInOffHand(new ItemStack(Material.PRISMARINE_SHARD));
        bukkitGiant.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
        bukkitGiant.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
        bukkitGiant.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
        bukkitGiant.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));

        bukkitGiant.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
        bukkitGiant.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 4));
    }

    private void spawnEntityOnBat(Location location, EntityType entityType) {
        Bat bat = (Bat) location.getWorld().spawnEntity(location, EntityType.BAT);
        Entity entity = location.getWorld().spawnEntity(location, entityType);
        bat.addPassenger(entity);
        bat.setGravity(false);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        World world = e.getBlock().getWorld();
        Location location = e.getBlockPlaced().getLocation();
        if (e.getBlockPlaced().getType() == Material.BEDROCK) {
            if (world.getName().equals("world_prisma")
                && e.getBlockAgainst().getType() == Material.BEDROCK) {
                world.playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10, 10);
                world.playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10, 10);
                world.playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10, 10);

                location.setY(location.getY() + 3);

                for (int i = 0; i < 12; i++) {
                    switch (i % 3) {
                        case 0: {
                            spawnEntityOnBat(location, EntityType.SKELETON);
                        } case 1: {
                            spawnEntityOnBat(location, EntityType.BLAZE);
                        } case 2: {
                            spawnEntityOnBat(location, EntityType.ENDER_CRYSTAL);
                        }
                    }
                }

                spawnBoss(location);
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getType() == EntityType.GIANT) {
            e.getDrops().clear();
            ItemStack spawner = new ItemStack(Material.SPAWNER);
            ItemMeta spawnerMeta = spawner.getItemMeta();
            spawnerMeta.setDisplayName(ChatColor.YELLOW + "Zombie Spawner");
            spawner.setItemMeta(spawnerMeta);
            e.getDrops().add(spawner);
        }
    }

}
