package me.tychsen.enchantgui;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

/**
 * Created by Dennis on 01/09/2015.
 */
public class EshopPermissionSys {

    public boolean hasEnchantPermission(Player p, ItemStack item) {
        if (p.isOp()) return true;

        Map<Enchantment, Integer> enchants = item.getEnchantments();
        if (enchants.size() > 1) throw new RuntimeException("Item has more than one enchant!");

        Enchantment ench = enchants.keySet().toArray(new Enchantment[1])[0];
        String name = ench.getName().toLowerCase();
        String perm = "eshop.enchants." + name;

        if (p.hasPermission(perm)) return true;

        return false;
    }

    public boolean hasEnchantPermission(Player p, ItemStack item, int level) {
        throw new NotImplementedException();
    }
}
