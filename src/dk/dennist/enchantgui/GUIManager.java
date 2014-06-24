package dk.dennist.enchantgui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class GUIManager {
    JavaPlugin plugin;
    Main main;
    int menuBuilt = 0;
    InventoryView iw;

    GUIManager(JavaPlugin plugin, Main main) {
        this.plugin = plugin;
        this.main = main;
    }

    public void openEnchantShop(Player p) {
        Server s = p.getServer();

        Inventory inv = s.createInventory(p, 27, "Enchant GUI");
        ItemStack[] menuItems = this.generateMenu(p);
        inv.setContents(menuItems);
        p.openInventory(inv);
    }

    private ItemStack[] generateMenu(Player p) {
        ItemStack[] menuItems = new ItemStack[24];
        int count = 0;

        for (Enchantment ent : Enchantment.values()) {
            if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta im = menuItems[count].getItemMeta();
                im.setDisplayName(main.getDisplayName(ent));
                String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
                im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
                menuItems[count].setItemMeta(im);
                count++;
            }
        }

        return menuItems;
    }
}
