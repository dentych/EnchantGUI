package me.tychsen.enchantgui.Menu;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface MenuGenerator {
    Inventory mainMenu(Player p);
    Inventory enchantMenu(Player p, ItemStack item);
}
