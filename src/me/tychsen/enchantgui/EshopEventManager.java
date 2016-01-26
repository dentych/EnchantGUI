package me.tychsen.enchantgui;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.Menu.DefaultEshopSystem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EshopEventManager {
    /* Private variables */
    private DefaultEshopSystem esys;

    /* Constructor */
    public EshopEventManager() {
        esys = new DefaultEshopSystem();
    }

    /* Public methods */


    public boolean handleCommand(CommandSender sender, Command cmd, String[] args) {
        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    EshopConfig.getInstance().reloadConfig(sender);
                }
            } else {
                if (playerHasUsePerms(p)) {
                    esys.showMainMenu(p);
                } else {
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




}