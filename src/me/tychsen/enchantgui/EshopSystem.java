package me.tychsen.enchantgui;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class EshopSystem {
    private Map<String, Integer> playerNavigation;
    private int inventorySize;

    private EshopEnchants enchants;
    private EshopPermissionSys permsys;
    private EshopConfig config;
    private EshopVault vault;

    public EshopSystem(JavaPlugin plugin) {
        playerNavigation = new HashMap<>();
        inventorySize = 36;
        enchants = new EshopEnchants();
        permsys = new EshopPermissionSys();
        config = new EshopConfig(plugin);
        vault = new EshopVault(plugin);
    }

    public void showMainMenu(Player p) {
        playerNavigation.put(p.getName(), 0);

        Inventory inv = p.getServer().createInventory(p, inventorySize, "EnchantGUI");
        generateMainMenu(p, inv);
        p.openInventory(inv);
    }

    public void showEnchantPage(Player p, ItemStack item) {
        playerNavigation.put(p.getName(), 1);

        Inventory inv = p.getServer().createInventory(p, inventorySize,
                "EnchantGUI");

        generateEnchantPage(p, inv, item);
        p.openInventory(inv);
    }

    public int getPlayerMenuLevel(Player p) {
        if (playerNavigation.containsKey(p.getName())) {
            return playerNavigation.get(p.getName());
        } else {
            throw new NoSuchElementException("Player is missing from navigation list.");
        }
    }

    public void purchaseEnchant(Player p, ItemStack item, int level) {
        Enchantment ench = item.getEnchantments().keySet().toArray(new Enchantment[1])[0];
        ItemStack playerHand = p.getItemInHand();
        int price = config.getPrice(ench, level);
        String start = ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE;

        if (playerHand == null || playerHand.getType() == Material.AIR) {
            p.sendMessage(start + "You can't enchant that!");
            p.closeInventory();
            return;
        }

        if (ench.canEnchantItem(playerHand)) {
            if (vault.withdrawMoney(p, price))
                playerHand.addEnchantment(ench, level);
            else {
                p.sendMessage(start + "Insufficient funds.");
            }
        }
        else {
            p.sendMessage(start + "Enchant can't be applied to the item in your hand...");
        }
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

    private void generateEnchantPage(Player p, Inventory inv, ItemStack item) {
        Enchantment ench = item.getEnchantments().keySet().toArray(new Enchantment[1])[0];
        int maxLevel = ench.getMaxLevel();
        String name = item.getItemMeta().getDisplayName();
        List<ItemStack> itemlist = new ArrayList<>();

        // Generate the correct items for the player.
        // Based on permissions or OP status.
        for (int i = 1; i <= maxLevel; i++) {
            ItemStack tmp;
            if (permsys.hasEnchantPermission(p, ench, i)) {
                tmp = item.clone();
                ItemMeta meta = tmp.getItemMeta();
                int price = config.getPrice(ench, i);
                meta.setLore(Arrays.asList(ChatColor.GOLD + "Level: " + i, ChatColor.GREEN + "Price: $" + price));
                tmp.setItemMeta(meta);
            } else {
                tmp = new ItemStack(Material.AIR);
            }

            itemlist.add(tmp);
        }

        // Put the generated item list in the inventory
        inv.setContents(itemlist.toArray(new ItemStack[itemlist.size()]));

        // Generate and insert a back button
        ItemStack backitem = new ItemStack(Material.EMERALD);
        ItemMeta meta = backitem.getItemMeta();
        meta.setDisplayName("Go back");
        backitem.setItemMeta(meta);
        inv.setItem(27, backitem);
    }
}
