package me.tychsen.enchantgui;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class EshopPermissionSys {

    public boolean hasEnchantPermission(Player p, ItemStack item) {
        if (p.isOp()) return true;

        Map<Enchantment, Integer> enchants = item.getEnchantments();
        if (enchants.size() > 1) throw new RuntimeException("Item has more than one enchant!");

        Enchantment ench = enchants.keySet().toArray(new Enchantment[1])[0];
        String base = "eshop.enchants.";
        String name = ench.getName().toLowerCase();
        String perm = base + name;

        if (p.hasPermission(perm) || p.hasPermission(base + "all"))
            return true;

        return false;
    }

    public boolean hasEnchantPermission(Player p, Enchantment ench, int level) {
        if (p.isOp()) return true;

        String base = "eshop.enchants.";
        String enchName = ench.getName().toLowerCase();
        String perm = base + enchName + "." + level;

        if (p.hasPermission(perm) || p.hasPermission(base + enchName + ".all") || p.hasPermission(base + "all"))
            return true;

        return false;
    }
}
