package me.tychsen.enchantgui;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EshopEnchants {
    List<EshopItem> enchantList = new ArrayList<>();

    public EshopEnchants() {
        createEnchantList();
    }

    public ItemStack[] generateMenuItemsForPlayer(Player p) {
        List<EshopItem> itemlist = new ArrayList<>();

        for (EshopItem item : enchantList) {
            if (p.hasPermission(enchantPermName(item.getEnchant())) || p.isOp()) {
                itemlist.add(item);
            }
        }

        ItemStack[] stack = itemlist.toArray(new ItemStack[itemlist.size()]);

        return stack;
    }

    /**
     * Generates the list of all enchants with their Enchantment,
     * Material and a display name.
     */
    private void createEnchantList() {
        addItem(Enchantment.ARROW_DAMAGE, Material.BOW, "Power");
        addItem(Enchantment.ARROW_FIRE, Material.BOW, "Flame");
        addItem(Enchantment.ARROW_INFINITE, Material.BOW, "Infinity");
        addItem(Enchantment.ARROW_KNOCKBACK, Material.BOW, "Punch");
        addItem(Enchantment.DAMAGE_ALL, Material.DIAMOND_SWORD, "Sharpness");
        addItem(Enchantment.DAMAGE_ARTHROPODS, Material.DIAMOND_SWORD, "Bane of Arthropods");
        addItem(Enchantment.DAMAGE_UNDEAD, Material.DIAMOND_SWORD, "Smite");
        addItem(Enchantment.DEPTH_STRIDER, Material.DIAMOND_BOOTS, "Depth Strider");
        addItem(Enchantment.DIG_SPEED, Material.DIAMOND_PICKAXE, "Efficiency");
        addItem(Enchantment.DURABILITY, Material.DIAMOND_PICKAXE, "Durability");
        addItem(Enchantment.FIRE_ASPECT, Material.DIAMOND_SWORD, "Fire aspect");
        addItem(Enchantment.KNOCKBACK, Material.DIAMOND_SWORD, "Knockback");
        addItem(Enchantment.LOOT_BONUS_BLOCKS, Material.DIAMOND_PICKAXE, "Fortune");
        addItem(Enchantment.LOOT_BONUS_MOBS, Material.DIAMOND_SWORD, "Looting");
        addItem(Enchantment.LUCK, Material.FISHING_ROD, "Luck of the sea");
        addItem(Enchantment.LURE, Material.FISHING_ROD, "Lure");
        addItem(Enchantment.OXYGEN, Material.DIAMOND_HELMET, "Respiration");
        addItem(Enchantment.PROTECTION_ENVIRONMENTAL, Material.DIAMOND_CHESTPLATE, "Protection");
        addItem(Enchantment.PROTECTION_EXPLOSIONS, Material.DIAMOND_CHESTPLATE, "Blast protection");
        addItem(Enchantment.PROTECTION_FALL, Material.DIAMOND_BOOTS, "Feather falling");
        addItem(Enchantment.PROTECTION_FIRE, Material.DIAMOND_CHESTPLATE, "Fire protection");
        addItem(Enchantment.PROTECTION_PROJECTILE, Material.DIAMOND_CHESTPLATE, "Projectile protection");
        addItem(Enchantment.SILK_TOUCH, Material.DIAMOND_PICKAXE, "Silk touch");
        addItem(Enchantment.THORNS, Material.DIAMOND_CHESTPLATE, "Thorns");
        addItem(Enchantment.WATER_WORKER, Material.DIAMOND_HELMET, "Aqua affinity");
    }

    private void addItem(Enchantment type, Material mat, String displayName) {
        enchantList.add(new EshopItem(type, mat, displayName));
    }

    /**
     * Returns the permission name for the enchant type.
     */
    private String enchantPermName(Enchantment type) {
        String perm = "eshop.enchant.";
        perm += type.getName().toLowerCase();
        return perm;
    }
}
