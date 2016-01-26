package me.tychsen.enchantgui.Menu;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.EshopEnchants;
import me.tychsen.enchantgui.Permissions.EshopPermissionSys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DefaultMenuGenerator implements MenuGenerator {
    private int inventorySize;
    private EshopConfig config;
    private EshopEnchants enchants;
    private EshopPermissionSys permsys;

    public DefaultMenuGenerator(int inventorySize, EshopConfig config, EshopPermissionSys permsys) {
        this.inventorySize = inventorySize;
        this.config = config;
        // TODO: Figure out better fix.
        this.enchants = new EshopEnchants();
        this.permsys = permsys;
    }

    public Inventory mainMenu(Player p) {
        Inventory inv = p.getServer().createInventory(p, inventorySize, config.getMenuName());
        generateMainMenu(p, inv);

        return inv;
    }

    public Inventory enchantMenu(Player p, ItemStack item) {
        Inventory inv = generateEnchantMenu(p, item);

        return inv;
    }

    private void generateMainMenu(Player p, Inventory inv) {
        List<ItemStack> enchList = enchants.getEnchantList();
        List<ItemStack> itemlist = new ArrayList<>();

        for (ItemStack item : enchList) {
            if (permsys.hasEnchantPermission(p, item)) {
                itemlist.add(item);
            }
        }

        inv.setContents(itemlist.toArray(new ItemStack[itemlist.size()]));
    }

    private Inventory generateEnchantMenu(Player p, ItemStack item) {
        Inventory inv = p.getServer().createInventory(p, inventorySize, config.getMenuName());

        Enchantment ench = item.getEnchantments().keySet().toArray(new Enchantment[1])[0];
        String name = item.getItemMeta().getDisplayName();
        List<ItemStack> itemlist = new ArrayList<>();

        // Generate the correct items for the player.
        // Based on permissions or OP status.
        String[] enchantLevels = config.getEnchantLevels(ench);

        for (String enchantLevel : enchantLevels) {
            enchantLevel = enchantLevel.substring(5);
            int level = Integer.parseInt(enchantLevel);
            if (permsys.hasEnchantPermission(p, ench, level)) {
                ItemStack tmp = item.clone();
                ItemMeta meta = tmp.getItemMeta();
                List<String> lores = new ArrayList<String>();
                lores.add(ChatColor.GOLD + "Level: " + level);
                if (!config.economyDisabled()) {
                    int price = config.getPrice(ench, level);
                    lores.add(ChatColor.GREEN + "Price: $" + price);
                }
                meta.setLore(lores);
                tmp.setItemMeta(meta);

                itemlist.add(tmp);
            }
        }

        // Put the generated item list in the inventory
        inv.setContents(itemlist.toArray(new ItemStack[itemlist.size()]));

        // Generate and insert a back button
        ItemStack backitem = new ItemStack(Material.EMERALD);
        ItemMeta meta = backitem.getItemMeta();
        meta.setDisplayName("Go back");
        backitem.setItemMeta(meta);
        inv.setItem(27, backitem);

        return inv;
    }
}
