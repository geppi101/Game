package com.geppi.event;

import com.geppi.other.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.PURPUR_BLOCK)) {
            player.setVelocity(new Vector(player.getLocation().getDirection().multiply(0.85).getX(), 0D, player.getLocation().getDirection().multiply(0.85).getZ()));
        }
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.PURPUR_STAIRS)) {
            player.setVelocity(new Vector(player.getLocation().getDirection().multiply(0.85).getX(), -0, player.getLocation().getDirection().multiply(0.85).getZ()));
        }
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.QUARTZ_STAIRS)) {
            player.setVelocity(new Vector(player.getLocation().getDirection().multiply(0.85).getX(), 0.75D, player.getLocation().getDirection().multiply(0.85).getZ()));
        }
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.QUARTZ_BLOCK)) {
            player.setVelocity(new Vector(player.getLocation().getDirection().multiply(0.85).getX(), 0.75D, player.getLocation().getDirection().multiply(0.85).getZ()));
        }
    }

}
