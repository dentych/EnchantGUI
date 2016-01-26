package me.tychsen.enchantgui.Economy;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PaymentStrategy {
    boolean purchaseEnchant(Player p, ItemStack item);
}
