package network.jon.viralpizza.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobSpawningListener implements Listener {

    public Entity replaceEntity(Entity entity, EntityType entityType) {
        World world = entity.getWorld();
        Location loc = entity.getLocation();
        entity.remove();
        return world.spawnEntity(loc, entityType);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntity().getWorld().getName().equals("world_prisma")) {
            Entity entity = e.getEntity();
            switch (e.getEntity().getType().toString()) {
                case "GHAST": {
                    replaceEntity(entity, EntityType.PHANTOM);
                    break;
                } case "ZOMBIFIED_PIGLIN": {
                    if (Math.random() > 0.7) {
                        // too many!!
                        Slime slime = (Slime) replaceEntity(entity, EntityType.SLIME);
                        slime.setSize(slime.getSize() * 2);
                        slime.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 5));
                    } else {
                        entity.remove();
                    }
                    break;
                } case "PIGLIN": {
                    MagmaCube mc = (MagmaCube) replaceEntity(entity, EntityType.MAGMA_CUBE);
                    mc.setSize(mc.getSize() * 2);
                    mc.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 5));
                    break;
                } case "HOGLIN": {
                    replaceEntity(entity, EntityType.MUSHROOM_COW);
                    break;
                } case "CREEPER": {
                    Creeper creeper = (Creeper) entity;
                    creeper.setPowered(true);
                    break;
                }
            }
        }
    }

}
