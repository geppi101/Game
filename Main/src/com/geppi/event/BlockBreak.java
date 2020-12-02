package com.geppi.event;

import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreak implements Listener {

    PlayerHandler playerhandler = new PlayerHandler();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockLowerBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(e.getBlock().getType().equals(Material.SNOW_BLOCK)) {
            if(e.isCancelled()) {
                e.setCancelled(true);
                return;
            }
            e.setDropItems(false);
            final Block block = e.getBlock();
            Bukkit.getServer().getWorld("world").dropItemNaturally(block.getLocation(), new ItemStack(Material.SNOW_BLOCK));
            return;
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if(e.isCancelled()) {
            e.setCancelled(true);
            return;
        }

        if(e.getBlock().getType().equals(Material.WHEAT) && !(e.getBlock().getData() == (byte) 7)) {
            e.setCancelled(true);
        }
        if(e.getBlock().getType().equals(Material.WHEAT) && e.getBlock().getData() == (byte) 7){
            e.setCancelled(true);
            final Block block = e.getBlock();
            block.breakNaturally();
            block.setType(Material.WHEAT);

            return;
        }

        if(e.getBlock().getType().equals(Material.BIRCH_SAPLING)) {
            e.setCancelled(true);
            return;
        }

        if(e.getBlock().getType().equals(Material.BIRCH_LOG)) {
            if (e.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.DIRT)) {

                e.setCancelled(true);
                final Block block = e.getBlock();
                block.breakNaturally();
                block.setType(Material.BIRCH_SAPLING);

                return;
            }
        }


        if(e.getBlock().getType().equals(Material.NETHER_WART) && (e.getBlock().getData() < (byte) 1)) {
            e.setCancelled(true);
        } else {
            if(e.getBlock().getType().equals(Material.NETHER_WART)) {
                e.setCancelled(true);
                final Block block = e.getBlock();
                block.breakNaturally();
                block.setType(Material.NETHER_WART);
                return;
            }
        }
        if(e.getBlock().getType().equals(Material.SUGAR_CANE)) {
            return;
        }
        playerhandler.setBlocksBroken(e.getPlayer(), playerhandler.getBlocksBroken(e.getPlayer()) + 1);

    }


    }

