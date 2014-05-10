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

    public ItemStack[] generateMenu(Player p) {
        ItemStack[] menuItems = new ItemStack[24];
        int count = 0;
        Enchantment ent;

        ent = Enchantment.ARROW_DAMAGE;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.BOW);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.ARROW_FIRE;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.BOW);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.ARROW_INFINITE;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.BOW);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.ARROW_KNOCKBACK;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.BOW);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.DAMAGE_ALL;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_SWORD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.DAMAGE_ARTHROPODS;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_SWORD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.DAMAGE_UNDEAD;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_SWORD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.DIG_SPEED;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_PICKAXE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.DURABILITY;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.FIRE_ASPECT;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_SWORD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.KNOCKBACK;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_SWORD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.LOOT_BONUS_BLOCKS;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_PICKAXE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.LOOT_BONUS_MOBS;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_SWORD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.LUCK;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.FISHING_ROD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.LURE;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.FISHING_ROD);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.OXYGEN;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_HELMET);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.PROTECTION_ENVIRONMENTAL;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.PROTECTION_EXPLOSIONS;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.PROTECTION_FALL;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.PROTECTION_FIRE;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.PROTECTION_PROJECTILE;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.SILK_TOUCH;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_PICKAXE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.THORNS;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_CHESTPLATE);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        ent = Enchantment.WATER_WORKER;
        if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
            if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                menuItems[count] = new ItemStack(Material.DIAMOND_HELMET);
            else
                menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = menuItems[count].getItemMeta();
            im.setDisplayName(main.getDisplayName(ent));
            String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
            im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
            menuItems[count].setItemMeta(im);
            count++;
        }

        return menuItems;
    }
}
