package me.tychsen.enchantgui;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.Menu.DefaultMenuSystem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EshopEventManager {
    /* Private variables */
    private DefaultMenuSystem esys;

    /* Constructor */
    public EshopEventManager() {
        esys = new DefaultMenuSystem();
    }

    /* Public methods */




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