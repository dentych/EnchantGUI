package me.tychsen.enchantgui.Menu;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.Economy.EshopVault;
import me.tychsen.enchantgui.Config.EshopEnchants;
import me.tychsen.enchantgui.Permissions.EshopPermissionSys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class DefaultMenuSystem implements MenuSystem {
    public static String start = ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE;

    private Map<String, Inventory> playerMenu;
    private int inventorySize;

    private EshopEnchants enchants;
    private EshopPermissionSys permsys;
    private EshopConfig config;
    private EshopVault vault;
    private MenuGenerator generator;

    public DefaultMenuSystem() {
        playerMenu = new HashMap<>();
        inventorySize = 36;
        permsys = new EshopPermissionSys();
        config = new EshopConfig();
        generator = new DefaultMenuGenerator(36, config, permsys);

        if (!config.economyDisabled()) {
            vault = new EshopVault();
        }
    }

    @Override
    public void showMainMenu(Player p) {
        if (playerMenu.containsKey(p.getName())) playerMenu.remove(p.getName());
        if (permsys.hasUsePermission(p)) {
            Inventory inv = generator.mainMenu(p);

            p.openInventory(inv);
        } else {
            p.sendMessage(ChatColor.DARK_RED + "[EnchantGUI] " + ChatColor.RED + "Sorry, you do not have " +
                    "permission for this.");
        }
    }

    @Override
    public void handleMenuClick(Player p, InventoryClickEvent event) {
        if (playerMenu.containsKey(p.getName())) {
            if (event.getCurrentItem().getType() == Material.EMERALD) {
                playerMenu.remove(p.getName());
                showMainMenu(p);
            } else if (event.getCurrentItem().getType() != Material.AIR){
                // TODO: Sell enchant to player.
                p.sendMessage("Yay, you bai enchant. Slot: " + event.getSlot());
            }
        } else {
            Inventory inv = generator.enchantMenu(p, event.getCurrentItem());
            playerMenu.put(p.getName(), inv);
            p.openInventory(inv);
        }
    }

    public void purchaseEnchant(Player p, ItemStack item, int slot) {
        Enchantment ench = item.getEnchantments().keySet().toArray(new Enchantment[1])[0];
        ItemStack playerHand = p.getItemInHand();
        String[] enchantLevels = config.getEnchantLevels(ench);
        int level = Integer.parseInt(enchantLevels[slot - 1].substring(5));
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
            } else {
                p.sendMessage(start + "Insufficient funds.");
            }
        } else {
            p.sendMessage(start + "Enchant can't be applied to the item in your hand...");
        }
    }

    private void enchantItem(ItemStack playerHand, Enchantment ench, int level) {
        if (level > ench.getMaxLevel()) {
            // Unsafe enchant
            playerHand.addUnsafeEnchantment(ench, level);
        } else {
            // Safe, regular enchant
            playerHand.addEnchantment(ench, level);
        }
    }
}
