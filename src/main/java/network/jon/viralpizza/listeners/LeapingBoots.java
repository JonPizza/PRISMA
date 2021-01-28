package network.jon.viralpizza.listeners;

import network.jon.viralpizza.ViralPizza;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.Random;

public class LeapingBoots {

    private static String toRoman(int i) {
        switch (i) {
            case 1: {
                return "I";
            }
            case 2: {
                return "II";
            }
            case 3: {
                return "III";
            }
            default: {
                // should never happen
                return "IV";
            }
        }
    }

    public static ItemStack getLeapingBoots(Random random) {
        int level = random.nextInt(3) + 1;
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setLore(Arrays.asList(ChatColor.YELLOW + "Leaping " + toRoman(level)));
        boots.setItemMeta(bootsMeta);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, random.nextInt(3) + 1);
        if (random.nextBoolean()) {
            boots.addEnchantment(Enchantment.MENDING, 1);
        } else {
            boots.addEnchantment(Enchantment.DURABILITY, 3);
        }
        return boots;
    }

    public static void addJumpBoostScheduler(ViralPizza viralPizza) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(viralPizza, () -> {
            Player[] players = (Bukkit.getOnlinePlayers()).toArray(new Player[0]);
            for (Player p : players) {
                ItemStack boots = p.getInventory().getBoots();
                if (boots != null && boots.getItemMeta() != null && boots.getItemMeta().hasLore()) {
                    if (boots.getItemMeta().getLore().get(0).contains("Leaping III")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 250, 6));
                    } else if (boots.getItemMeta().getLore().get(0).contains("Leaping II")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 250, 4));
                    } else if (boots.getItemMeta().getLore().get(0).contains("Leaping I")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 250, 2));
                    }
                }
            }
        }, 0L, 200L);
    }

}
