package dk.dennist.enchantgui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class GUIManager {
    private JavaPlugin plugin;
    private Main main;
    private HashMap<Enchantment, Material> ents = new HashMap<Enchantment, Material>();
    private List<Enchantment> entList = Arrays.asList(Enchantment.values());

    GUIManager(JavaPlugin plugin, Main main) {
        this.plugin = plugin;
        this.main = main;
        fillEntsMap();
        Collections.sort(entList, new Comparator<Enchantment>() {
            @Override
            public int compare(Enchantment o1, Enchantment o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public void openEnchantShop(Player p) {
        Server s = p.getServer();

        Inventory inv = s.createInventory(p, 27, "Enchant GUI");
        ItemStack[] menuItems = this.generateMenu(p);
        inv.setContents(menuItems);
        p.openInventory(inv);
    }

    private ItemStack[] generateMenu(Player p) {
        ItemStack[] menuItems = new ItemStack[24];
        int count = 0;

        for (Enchantment ent : entList) {
            p.sendMessage(ent.getName());
            if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
                if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                    menuItems[count] = itemForEnchant(ent);
                else
                    menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);

                ItemMeta im = menuItems[count].getItemMeta();
                im.setDisplayName(main.getDisplayName(ent));
                String price = plugin.getConfig().getString(main.getConfigName(ent) + ".price");
                im.setLore(Arrays.asList(ChatColor.GREEN + "$" + price));
                menuItems[count].setItemMeta(im);
                count++;
            }
        }

        return menuItems;
    }

    private ItemStack itemForEnchant(Enchantment ent) {
        if (ents.containsKey(ent))
            return new ItemStack(ents.get(ent));
        else
            return new ItemStack(Material.CAKE);
    }

    private void fillEntsMap() {
        for (Enchantment ent : Enchantment.values()) {
            String name = ent.getName().toLowerCase();
            String parts[] = name.split("_");
            if (name.equals("arrow_damage") || name.equals("arrow_fire") || name.equals("arrow_infinite") || name.equals("arrow_knockback"))
                ents.put(ent, Material.BOW);
            else if (name.equals("damage_all") || name.equals("damage_arthropods") || name.equals("damage_undead") || name.equals("fire_aspect") || name.equals("knockback") || name.equals("loot_bonus_mobs"))
                ents.put(ent, Material.DIAMOND_SWORD);
            else if (name.equals("dig_speed") || name.equals("durability") || name.equals("loot_bonus_blocks") || name.equals("silk_touch"))
                ents.put(ent, Material.DIAMOND_PICKAXE);
            else if (name.equals("luck") || name.equals("lure"))
                ents.put(ent, Material.FISHING_ROD);
            else if (parts[0].equals("protection") || name.equals("thorns"))
                ents.put(ent, Material.DIAMOND_CHESTPLATE);
            else if (name.equals("oxygen") || name.equals("water_worker"))
                ents.put(ent, Material.DIAMOND_HELMET);
            else
                ents.put(ent, Material.ENCHANTED_BOOK);
        }
    }
}
