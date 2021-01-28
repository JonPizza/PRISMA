package network.jon.viralpizza;

import network.jon.viralpizza.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class ViralPizza extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BossTotemListener(), this);
        getServer().getPluginManager().registerEvents(new SpawnerPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new PortalTeleportListener(), this);
        getServer().getPluginManager().registerEvents(new MobSpawningListener(), this);

        if (Bukkit.getWorld("world_prisma") == null) {
            WorldCreator wc = new WorldCreator("world_prisma");
            wc.environment(World.Environment.NETHER);
            Bukkit.createWorld(wc);
        }

        this.getCommand("prisma").setExecutor(new HelpfulCommands());
        LeapingBoots.addJumpBoostScheduler(this);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new PrismaChunkGenerator();
    }

}
