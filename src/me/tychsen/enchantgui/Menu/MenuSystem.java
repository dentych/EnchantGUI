package me.tychsen.enchantgui.Menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by denni on 26/01/2016.
 */
public interface MenuSystem {
    void showMainMenu(Player p);

    void showEnchantPage(Player p, ItemStack item);

    int getPlayerMenuLevel(Player p);

    void purchaseEnchant(Player p, ItemStack item, int slot);
}
