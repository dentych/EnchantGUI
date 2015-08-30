package me.tychsen.enchantgui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class EshopSystem {
    private int inventorySize = 36;
    EshopEnchants enchants = new EshopEnchants();

    public void openGUI(Player p) {
        Inventory inv = p.getServer().createInventory(p, inventorySize, "EnchantGUI");

        populateInventory(inv, p);

        p.openInventory(inv);
    }

    private void populateInventory(Inventory inv, Player p) {
        ItemStack[] items = enchants.generateMenuItemsForPlayer(p);
        inv.setContents(items);
    }
}
