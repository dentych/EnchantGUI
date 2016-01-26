package me.tychsen.enchantgui.Event;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.Menu.MenuSystem;
import me.tychsen.enchantgui.Main;
import me.tychsen.enchantgui.Permissions.EshopPermissionSys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventManager implements Listener, CommandExecutor {
    private MenuSystem system;

    public EventManager(MenuSystem system) {
        this.system = system;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if (e.getCurrentItem() == null ||  e.getCurrentItem().getType() == Material.AIR)
            return;
        try {
            handleInventoryClickEvent(e);
        } catch (Exception exc) {
            // TODO: Fix this shit with main getinstance.. it's ugly.
            Main.getInstance().getLogger().severe("EXCEPTION: " + exc.getMessage() + "\n" + exc.getStackTrace()[0].toString() + "\n\n");
        }
    }

    //@EventHandler
    // TODO: Implement Enchanting table right click to open menu.
    // Requires more work, though, as it's currently the item in the hand
    // that is enchanted - which will then be an enchanting table...
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR &&
                e.getPlayer().getItemInHand().getType() == Material.ENCHANTMENT_TABLE) {
            handlePlayerInteractEvent(e);
        }
    }

    private void handleInventoryClickEvent(InventoryClickEvent e) {
        boolean correctEvent = e.getInventory().getName().toLowerCase().startsWith(EshopConfig.getInstance().getMenuName().toLowerCase());
        Player p;

        if (correctEvent && e.getWhoClicked() instanceof Player) {
            e.setCancelled(true); // Cancel whatever default action might occur
            p = (Player) e.getWhoClicked();

            system.handleMenuClick(p, e);
        } else {
            return;
        }
    }

    private void handlePlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.isOp()) {
            // TODO: || playerHasUsePerms(p) <-- Implement this crap somewhere
            // IMPORTANT: PlayerInteractEvent EventHandler is currently disabled, so no worries...
            system.showMainMenu(e.getPlayer());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("eshop")) {
            handleCommand(sender, command, args);
        }

        return true;
    }

    private boolean handleCommand(CommandSender sender, Command cmd, String[] args) {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                EshopConfig.getInstance().reloadConfig(sender);
            }
        } else {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                system.showMainMenu(p);
            }
            else {
                sender.sendMessage("Can't use this command from console...");
            }
        }

        return true;
    }
}
