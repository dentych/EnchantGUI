package me.tychsen.enchantgui.Event;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.Menu.EshopSystem;
import me.tychsen.enchantgui.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventManager implements Listener {
    private EshopSystem system;

    public EventManager(EshopSystem system) {
        this.system = system;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if (e.getCurrentItem() == null ||  e.getCurrentItem().getType() == Material.AIR)
            return;
        try {
            handleInventoryClickEvent(e);
        } catch (Exception exc) {
            // Todo: Fix this shit
            Main.getInstance().getLogger().severe("EXCEPTION: " + exc.getMessage());
        }
    }

    @EventHandler
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
        } else {
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

    private void handlePlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.isOp() || playerHasUsePerms(p)) {
            esys.showMainMenu(e.getPlayer());
        }
    }

    private void handleEnchantPage(Player p, ItemStack item, int slot) {
        switch (slot) {
            case 27:
                esys.showMainMenu(p);
                break;
            default:
                if (item.getType() != Material.AIR) {
                    esys.purchaseEnchant(p, item, slot + 1);
                }
                break;
        }
    }
}
