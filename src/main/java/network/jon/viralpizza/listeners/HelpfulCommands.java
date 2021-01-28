package network.jon.viralpizza.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpfulCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.teleport(new Location(Bukkit.getWorld("world_prisma"), 0, 20, 0));
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Warped to prisma!");
        }

        return true;
    }

}
