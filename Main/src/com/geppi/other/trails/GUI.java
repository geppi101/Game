package com.geppi.other.trails;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {

    private static Inventory INV;

    public void register() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.AQUA + "" + ChatColor.BOLD + "Trails GUI");

        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Totem");
        item.setItemMeta(meta);
        inv.setItem(0, item);

        item = new ItemStack(Material.LAVA_BUCKET);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Flame");
        item.setItemMeta(meta);
        inv.setItem(1, item);


        item = new ItemStack(Material.GRAY_STAINED_GLASS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Clouds");
        item.setItemMeta(meta);
        inv.setItem(2, item);

        item = new ItemStack(Material.RED_WOOL);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Hearts");
        item.setItemMeta(meta);
        inv.setItem(3, item);


        item = new ItemStack(Material.ENDER_EYE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Dragon Breath");
        item.setItemMeta(meta);
        inv.setItem(4, item);



        setInventory(inv);
    }

    public Inventory getInventory() {
        return INV;
    }

    private void setInventory(Inventory inv) {
        INV = inv;
    }

    public void openInventory(Player player) {
        player.openInventory(INV);
    }

}