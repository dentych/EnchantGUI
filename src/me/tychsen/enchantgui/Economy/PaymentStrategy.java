package me.tychsen.enchantgui.Economy;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PaymentStrategy {
    boolean withdraw(Player p, int amount);
    boolean hasSufficientFunds(Player p, int amount);
}
