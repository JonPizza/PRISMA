package network.jon.viralpizza.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnerPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
         if (e.getBlock().getType() == Material.SPAWNER) {
             CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
             spawner.setSpawnedType(EntityType.ZOMBIE);
             spawner.update();
         }
    }

}
