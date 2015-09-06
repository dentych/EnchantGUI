package me.tychsen.enchantgui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EshopEventManager {
    /* Private variables */
    private EshopSystem esys;

    /* Constructor */
    public EshopEventManager() {
        esys = new EshopSystem();
    }

    /* Public methods */
    public void handleInventoryClickEvent(InventoryClickEvent e) {
        boolean correctEvent = e.getInventory().getName().toLowerCase().startsWith("enchantgui");
        Player p;

        if (correctEvent && e.getWhoClicked() instanceof Player) {
            e.setCancelled(true); // Cancel whatever default action might occur
            p = (Player) e.getWhoClicked();
        }
        else {
            return;
        }

        switch (esys.getPlayerMenuLevel(p)) {
            case 0:
                esys.showEnchantPage(p, e.getCurrentItem());
                break;
            case 1:
                handleEnchantPage(p, e.getCurrentItem(), e.getSlot());
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public boolean handleCommand(CommandSender sender, Command cmd) {
        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("eshop")) {
                if (playerHasUsePerms(p)) {
                    esys.showMainMenu(p);
                }
                else {
                    p.sendMessage(ChatColor.DARK_RED + "[EnchantGUI] " + ChatColor.RED + "Sorry, you do not have access to this command");
                }
            }
        }

        return true;
    }

    /* Private methods */

    /**
     * Returns whether the player has permission to use the plugin or not.
     *
     * @param p The player to check for.
     * @return True if the player has permission or is OP, false otherwise.
     */
    private boolean playerHasUsePerms(Player p) {
        if (p.hasPermission("eshop.use") || p.isOp())
            return true;
        else
            return false;
    }

    private void handleEnchantPage(Player p, ItemStack item, int slot) {
        switch (slot) {
            case 27:
                esys.showMainMenu(p);
                break;
            default:
                if (item.getType() != Material.AIR) {
                    esys.purchaseEnchant(p, item, slot+1);
                }
                break;
        }
    }
}