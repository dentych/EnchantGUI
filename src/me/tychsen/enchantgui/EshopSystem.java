package me.tychsen.enchantgui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class EshopSystem {
    public void openGUI(Player p) {
        Inventory inv = p.getServer().createInventory(p, 36, "EnchantGUI");

        p.openInventory(inv);
    }
}
