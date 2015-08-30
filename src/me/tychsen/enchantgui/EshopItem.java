package me.tychsen.enchantgui;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EshopItem extends ItemStack {
    private Enchantment type;
    private String displayName;

    public EshopItem(Enchantment type, Material mat, String displayName) {
        super(mat);

        this.type = type;
        this.displayName = displayName;

        ItemMeta meta = getItemMeta();
        meta.setDisplayName(displayName);
        setItemMeta(meta);
    }

    public Enchantment getEnchant() { return type; }

    public String getDisplayName() { return displayName; }
}
