package com.geppi.event;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CraftItem implements Listener {

    ColorHandler colorHandler = new ColorHandler();
    PlayerHandler playerHandler = new PlayerHandler();

    /*@EventHandler
    public void craftItem(PrepareItemCraftEvent e) {
        Material itemType = e.getRecipe().getResult().getType();
        Byte itemData = e.getRecipe().getResult().getData().getData();
        if(itemType==Material.DIAMOND_SWORD) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
            for(HumanEntity he:e.getViewers()) {
                if(he instanceof Player) {
                    ((Player)he).sendMessage(ChatColor.RED+"You cannot craft this!");
                }
            }
        }
    } */


    @EventHandler
    @Deprecated
    public void craftItem(PrepareItemCraftEvent e) {
        if(e.getRecipe() == null) return;
        Material itemType = Objects.requireNonNull(e.getRecipe()).getResult().getType();
        if (itemType==Material.ENDER_CHEST
                ||itemType==Material.ANVIL
                ||itemType==Material.HOPPER
                ||itemType==Material.BEACON
                ||itemType==Material.DIAMOND_HELMET
                ||itemType==Material.DIAMOND_CHESTPLATE
                ||itemType==Material.DIAMOND_LEGGINGS
                ||itemType==Material.DIAMOND_BOOTS
                ||itemType==Material.DIAMOND_SWORD
                ||itemType==Material.GOLDEN_APPLE
                ||itemType==Material.NETHERITE_HELMET
                ||itemType==Material.NETHERITE_BOOTS

                ||itemType==Material.NETHERITE_LEGGINGS
                ||itemType==Material.NETHERITE_CHESTPLATE
                ||itemType==Material.NETHERITE_SWORD) {

            e.getInventory().setResult(new ItemStack(Material.AIR));
            for(HumanEntity he:e.getViewers()) {
                if(he instanceof Player) {
                    ((Player)he).sendMessage(colorHandler.error+"You cannot craft this!");
                }
            }
            return;
        }


    }
}
