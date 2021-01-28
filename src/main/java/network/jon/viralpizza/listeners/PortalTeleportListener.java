package network.jon.viralpizza.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PortalTeleportListener implements Listener {

    private void setWorldBlock(World world, int x, int y, int z, Material type) {
        world.getBlockAt(new Location(world, x, y, z)).setType(type);
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent e) {
        World prisma = Bukkit.getWorld("world_prisma");
        World overworld = Bukkit.getWorld("world");

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.WARPED_BUTTON) {
            Player p = e.getPlayer();

            int x = (int) e.getClickedBlock().getLocation().getX();
            int y = (int) e.getClickedBlock().getLocation().getY();
            int z = (int) e.getClickedBlock().getLocation().getZ();

            if (e.getPlayer().getWorld() != prisma) {
                // Build a lil platform

                for (int pX = -2; pX < 3; pX++) {
                    for (int pZ = -2; pZ < 3; pZ++) {
                        setWorldBlock(prisma, x + pX, y, z + pZ, Material.DARK_PRISMARINE);
                    }
                }

                setWorldBlock(prisma, x, y + 1, z, Material.SEA_LANTERN);
                setWorldBlock(prisma, x, y + 1, z - 1, Material.WARPED_BUTTON);

                p.teleport(new Location(prisma, x, y + 1, z));
            } else {
                if (p.getBedSpawnLocation() != null) {
                    p.teleport(p.getBedSpawnLocation());
                } else {
                    p.teleport(overworld.getSpawnLocation());
                }
            }
        }
    }

}
