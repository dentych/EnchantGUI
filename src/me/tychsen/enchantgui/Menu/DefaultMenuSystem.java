package me.tychsen.enchantgui.Menu;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.Economy.EshopVault;
import me.tychsen.enchantgui.EshopEnchants;
import me.tychsen.enchantgui.Permissions.EshopPermissionSys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class DefaultMenuSystem implements MenuSystem {
    public static String start = ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE;

    private Map<String, Integer> playerNavigation;
    private int inventorySize;

    private EshopEnchants enchants;
    private EshopPermissionSys permsys;
    private EshopConfig config;
    private EshopVault vault;

    public DefaultMenuSystem() {
        playerNavigation = new HashMap<>();
        inventorySize = 36;
        enchants = new EshopEnchants();
        permsys = new EshopPermissionSys();
        config = new EshopConfig();

        if (!config.economyDisabled()) {
            vault = new EshopVault();
        }
    }

    @Override
    public void showMainMenu(Player p) {
        playerNavigation.put(p.getName(), 0);

        Inventory inv = p.getServer().createInventory(p, inventorySize, config.getMenuName());
        generateMainMenu(p, inv);
        p.openInventory(inv);
    }

    @Override
    public void showEnchantPage(Player p, ItemStack item) {
        playerNavigation.put(p.getName(), 1);

        Inventory inv = p.getServer().createInventory(p, inventorySize,
                config.getMenuName());

        generateEnchantPage(p, inv, item);
        p.openInventory(inv);
    }

    @Override
    public int getPlayerMenuLevel(Player p) {
        if (playerNavigation.containsKey(p.getName())) {
            return playerNavigation.get(p.getName());
        } else {
            throw new NoSuchElementException("Player is missing from navigation list.");
        }
    }

    @Override
    public void purchaseEnchant(Player p, ItemStack item, int slot) {
        Enchantment ench = item.getEnchantments().keySet().toArray(new Enchantment[1])[0];
        ItemStack playerHand = p.getItemInHand();
        String[] enchantLevels = config.getEnchantLevels(ench);
        int level = Integer.parseInt(enchantLevels[slot-1].substring(5));
        int price = config.getPrice(ench, level);

        // DEBUG
        //p.sendMessage("Slot: " + slot + ". Level: " + level);

        if (playerHand == null || playerHand.getType() == Material.AIR) {
            p.sendMessage(start + "You can't enchant that!");
            p.closeInventory();
            return;
        }

        if (ench.canEnchantItem(playerHand)) {
            if (config.economyDisabled() || vault.withdrawMoney(p, price)) {
                enchantItem(playerHand, ench, level);
                p.sendMessage(start + "Your item was enchanted with " + ChatColor.LIGHT_PURPLE + item.getItemMeta().getDisplayName() + " " + level);
                p.closeInventory();
            }
            else {
                p.sendMessage(start + "Insufficient funds.");
            }
        }
        else {
            p.sendMessage(start + "Enchant can't be applied to the item in your hand...");
        }
    }

    private void enchantItem(ItemStack playerHand, Enchantment ench, int level) {
        if (level > ench.getMaxLevel()) {
            // Unsafe enchant
            playerHand.addUnsafeEnchantment(ench, level);
        }
        else {
            // Safe, regular enchant
            playerHand.addEnchantment(ench, level);
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
        String[] enchantLevels = config.getEnchantLevels(ench);

        for (String enchantLevel : enchantLevels) {
            enchantLevel = enchantLevel.substring(5);
            int level = Integer.parseInt(enchantLevel);
            ItemStack tmp;
            if (permsys.hasEnchantPermission(p, ench, level)) {
                tmp = item.clone();
                ItemMeta meta = tmp.getItemMeta();
                int price = config.getPrice(ench, level);
                List<String> lores = new ArrayList<String>();
                lores.add(ChatColor.GOLD + "Level: " + level);
                if (!config.economyDisabled()) {
                    lores.add(ChatColor.GREEN + "Price: $" + price);
                }
                meta.setLore(lores);
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
