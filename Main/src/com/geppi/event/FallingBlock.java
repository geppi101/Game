package com.geppi.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.logging.Logger;

public class FallingBlock implements Listener {

   @EventHandler
   public void BlockPhysicsEvent(BlockPhysicsEvent event) {
       if (event.getBlock().getWorld() == Bukkit.getWorld("world")) {

           if (event.getBlock().getType() == Material.REDSTONE
                   || event.getBlock().getType() == Material.IRON_BARS
                   || event.getBlock().getType() == Material.DARK_OAK_DOOR
                   || event.getBlock().getType() == Material.ACACIA_DOOR
                   || event.getBlock().getType() == Material.BIRCH_DOOR
                   || event.getBlock().getType() == Material.CRIMSON_DOOR
                   || event.getBlock().getType() == Material.IRON_DOOR
                   || event.getBlock().getType() == Material.JUNGLE_DOOR
                   || event.getBlock().getType() == Material.OAK_DOOR
                   || event.getBlock().getType() == Material.SPRUCE_DOOR
                   || event.getBlock().getType() == Material.WARPED_DOOR
                   || event.getBlock().getType() == Material.LADDER
                   || event.getBlock().getType() == Material.PUMPKIN_SEEDS
                   || event.getBlock().getType() == Material.PUMPKIN
                   || event.getBlock().getType() == Material.ITEM_FRAME
                   || event.getBlock().getType() == Material.OAK_FENCE
                   || event.getBlock().getType() == Material.BIRCH_FENCE
                   || event.getBlock().getType() == Material.SPRUCE_FENCE
                   || event.getBlock().getType() == Material.STONE_BUTTON
                   || event.getBlock().getType() == Material.REDSTONE_WIRE
                   || event.getBlock().getType() == Material.COMPARATOR
                   || event.getBlock().getType() == Material.REPEATER
                   || event.getBlock().getType() == Material.IRON_TRAPDOOR
                   || event.getBlock().getType() == Material.PISTON
                   || event.getBlock().getType() == Material.PISTON_HEAD
                   || event.getBlock().getType() == Material.MOVING_PISTON
                   || event.getBlock().getType() == Material.BIRCH_SAPLING
                   || event.getBlock().getType() == Material.BIRCH_WOOD
                   || event.getBlock().getType() == Material.BIRCH_LOG
                   || event.getBlock().getType() == Material.SUGAR_CANE
                   || event.getBlock().getType() == Material.HOPPER
                   || event.getBlock().getType()== Material.BIRCH_LEAVES
                   || event.getSourceBlock().getType() == Material.BIRCH_SAPLING
                   || event.getSourceBlock().getType() == Material.BIRCH_LEAVES
                   || event.getSourceBlock().getType() == Material.BIRCH_WOOD
                   || event.getSourceBlock().getType() == Material.BIRCH_LOG
                   || event.getSourceBlock().getType() == Material.SUGAR_CANE
                   || event.getSourceBlock().getType() == Material.HOPPER
                   || event.getBlock().getType() == Material.STICKY_PISTON
                   || event.getBlock().getType() == Material.DIRT
                   || event.getBlock().getType() == Material.COARSE_DIRT
                   || event.getBlock().getType() == Material.CHEST) {

               return;
           }
           try {
               if (event.getBlock().getWorld().getName().equals("world")) {
                   event.setCancelled(true);
               }
           } catch (Exception e) {
               Logger logger = Logger.getLogger("Minecraft");

               logger.info(ChatColor.RED + "MCGSPACE -- ERROR" + e.toString());
           }
       }
   }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
                event.setCancelled(true);
                event.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
            }
        }
    }

}
