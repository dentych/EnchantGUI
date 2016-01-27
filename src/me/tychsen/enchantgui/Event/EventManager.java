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
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Logger;

public class EventManager implements Listener, CommandExecutor {
    private MenuSystem system;

    public EventManager(MenuSystem system) {
        this.system = system;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        String inventoryName = e.getInventory().getName().toLowerCase();
        String configInventoryName = EshopConfig.getInstance().getMenuName().toLowerCase();
        boolean correctEvent = inventoryName.startsWith(configInventoryName);

        if (!correctEvent) {
            return;
        } else {
            e.setCancelled(true);
            if (!(e.getWhoClicked() instanceof Player)) return;
            try {
                handleInventoryClickEvent(e);
            } catch (Exception ex) {
                Logger logger = Main.getInstance().getLogger();
                logger.severe("EXCEPTION: " + ex.getMessage());
                for (StackTraceElement element : ex.getStackTrace()) {
                    final String exceptionMsg =
                            "Exception thrown from " + element.getMethodName() +
                                    " in class " + element.getClassName() +
                                    " [on line number " + element.getLineNumber() +
                                    " of file " + element.getFileName() + "]";
                    logger.severe(exceptionMsg);
                }
            }
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
        if (e.getCurrentItem().getType() == Material.AIR) return;
        if (e.getClickedInventory().getType() != InventoryType.CHEST) return;

        Player p = (Player) e.getWhoClicked();
        system.handleMenuClick(p, e);
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
            } else {
                sender.sendMessage("Can't use this command from console...");
            }
        }

        return true;
    }
}
