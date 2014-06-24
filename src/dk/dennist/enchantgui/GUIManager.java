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
        if (!main.menuPage.containsKey(p.getName()))
            main.menuPage.put(p.getName(), 0);

        Server s = p.getServer();

        Inventory inv = s.createInventory(p, 36, "Enchant GUI");
        ItemStack[] menuItems = this.generateMenu(p);
        inv.setContents(menuItems);
        ItemStack item;
        ItemMeta im;
        generateNavigation(inv, main.menuPage.get(p.getName()));
        p.openInventory(inv);
    }

    private ItemStack[] generateMenu(Player p) {
        ItemStack[] menuItems = new ItemStack[24];
        int count = 0;

        for (Enchantment ent : entList) {
            if (ent.getMaxLevel() <= main.menuPage.get(p.getName()))
                continue;

            if (p.hasPermission(main.getPermissionName(ent)) || p.hasPermission("eshop.enchant.all")) {
                if (plugin.getConfig().getBoolean("show-enchants-as-items"))
                    menuItems[count] = itemForEnchant(ent);
                else
                    menuItems[count] = new ItemStack(Material.ENCHANTED_BOOK);

                ItemMeta im = menuItems[count].getItemMeta();
                im.setDisplayName(main.getDisplayName(ent));
                String price = main.getEnchantPrice(ent, main.menuPage.get(p.getName()));
                im.setLore(Arrays.asList(ChatColor.GOLD + "Level: " + (ent.getStartLevel()+main.menuPage.get(p.getName())), ChatColor.GREEN + "$" + price));
                menuItems[count].setItemMeta(im);
                count++;
            }
        }

        return menuItems;
    }

    private void generateNavigation(Inventory inv, int page) {
        ItemStack item;
        ItemMeta im;

        switch (page) {
            case 0:
                item = new ItemStack(Material.EMERALD);
                im = item.getItemMeta();
                im.setDisplayName("Next page");
                item.setItemMeta(im);
                inv.setItem(35, item);
                break;
            case 4:
                item = new ItemStack(Material.EMERALD);
                im = item.getItemMeta();
                im.setDisplayName("Previous page");
                item.setItemMeta(im);
                inv.setItem(27, item);
                break;
            default:
                item = new ItemStack(Material.EMERALD);
                im = item.getItemMeta();
                im.setDisplayName("Previous page");
                item.setItemMeta(im);
                inv.setItem(27, item);
                item = new ItemStack(Material.EMERALD);
                im = item.getItemMeta();
                im.setDisplayName("Next page");
                item.setItemMeta(im);
                inv.setItem(35, item);
        }
        item = new ItemStack(Material.EMERALD);

        for (int i = 1; i <= 5; i++) {
            im = item.getItemMeta();
            im.setDisplayName("Page " + i);
            item.setItemMeta(im);
            inv.setItem((28+i), item);
        }
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
