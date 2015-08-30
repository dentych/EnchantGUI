package me.tychsen.enchantgui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class EshopEventManager {
    /* Private variables */
    private EshopSystem esys;
    /* Public variables */

    /* Public methods */
    public EshopEventManager() {
        esys = new EshopSystem();
    }

    public void handleInventoryClickEvent(InventoryClickEvent e) {
        // PARTLY IMPLEMENTED...
        boolean correctEvent = e.getInventory().getName().equalsIgnoreCase("enchantgui");

        if (correctEvent) {
            e.setCancelled(true); // Cancel whatever default action might occur
        }

        // ONLY PARTLY IMPLEMENTED
        throw new NotImplementedException();
    }

    public boolean handleCommand(CommandSender sender, Command cmd) {
        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
        } else
            return false;

        if (cmd.getName().equalsIgnoreCase("eshop")) {
            if (playerHasUsePerms(p)) {
                esys.openGUI((Player) sender);
                return true;
            }
        }

        return false;
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
}