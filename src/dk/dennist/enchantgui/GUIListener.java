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
            Enchantment ent;
                    // Arrow Damage
            if (e.getWhoClicked() instanceof Player && e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("arrow damage")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.ARROW_DAMAGE;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Arrow Fire
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("arrow fire")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.ARROW_FIRE;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Arrow Infinite
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("arrow infinite")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.ARROW_INFINITE;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Arrow Knockback
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("arrow knockback")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.ARROW_KNOCKBACK;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Damage All
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("damage all")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.DAMAGE_ALL;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Damage Arthropods
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("damage arthropods")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.DAMAGE_ARTHROPODS;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Damage Undead
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("damage undead")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.DAMAGE_UNDEAD;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Dig speed
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("dig speed")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.DIG_SPEED;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Durability
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("durability")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.DURABILITY;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Fire aspect
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("fire aspect")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.FIRE_ASPECT;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Knockback
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("knockback")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.KNOCKBACK;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Loot Bonus Blocks
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("loot bonus blocks")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.LOOT_BONUS_BLOCKS;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Loot bonus mobs
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("loot bonus mobs")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.LOOT_BONUS_MOBS;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Luck
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("luck")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.LUCK;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Lure
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("lure")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.LURE;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Oxygen
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("oxygen")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.OXYGEN;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Protection Environmental
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("protection environmental")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.PROTECTION_ENVIRONMENTAL;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Protection Explosions
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("protection explosions")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.PROTECTION_EXPLOSIONS;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Protection Fall
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("protection fall")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.PROTECTION_FALL;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Protection fire
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("protection fire")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.PROTECTION_FIRE;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Protection Projectiles
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("protection projectile")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.PROTECTION_PROJECTILE;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + "Projectile Protection"
                            + ChatColor.WHITE);
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Silk touch
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("silk touch")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.SILK_TOUCH;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Thorns
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Thorns")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.THORNS;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
                // Water work
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("water worker")) {
                    Player p = (Player) e.getWhoClicked();
                    ent = Enchantment.WATER_WORKER;
                    if (item.getType().getMaxDurability() > 25 && ent.canEnchantItem(item) && !item.containsEnchantment(ent)) {
                        if (main.takeMoneyFromPlayer(p, ent.getName().toLowerCase())) {
                            item.addEnchantment(ent, ent.getMaxLevel());
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Your item has been enchanted with " + ChatColor.GREEN + main.getDisplayName(ent));
                        }
                        else {
                            p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "You don't have enough money!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "This enchant can't be put on that item!");
                    }
                }
            }
        }
        else if (e.getSlotType() == InventoryType.SlotType.QUICKBAR && e.getInventory().getName().equalsIgnoreCase("enchant gui")) {
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
