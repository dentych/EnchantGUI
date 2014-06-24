package dk.dennist.enchantgui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIListener implements Listener {
    JavaPlugin plugin;
    Main main;

    GUIListener(JavaPlugin plugin, Main main) {
        this.main = main;
        this.plugin = plugin;
    }

    @EventHandler
    public void onInvClickEvent(InventoryClickEvent e) {
        if (e.getSlotType() == InventoryType.SlotType.CONTAINER && e.getInventory().getName().equalsIgnoreCase("enchant gui")) {
            e.setCancelled(true);
            ItemStack item = e.getWhoClicked().getItemInHand();

            // Arrow Damage
            if (e.getWhoClicked() instanceof Player && e.getCurrentItem().hasItemMeta()) {
                for (Enchantment ent : Enchantment.values()) {
                    if (main.getDisplayName(ent).equalsIgnoreCase(e.getCurrentItem().getItemMeta().getDisplayName())) {
                        Player p = (Player) e.getWhoClicked();
                        if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                            if (main.takeMoneyFromPlayer(p, ent)) {
                                item.addEnchantment(ent, ent.getMaxLevel());
                                p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                            } else {
                                p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                            }
                        } else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                        }
                        break;
                    }
                }
            }
        } else if (e.getSlotType() == InventoryType.SlotType.QUICKBAR && e.getInventory().getName().equalsIgnoreCase("enchant gui")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getPlayer().getItemInHand().getType() == Material.ENCHANTMENT_TABLE && e.getPlayer().hasPermission("eshop.table")) {
            main.gm.openEnchantShop(e.getPlayer());
        }
    }
}
