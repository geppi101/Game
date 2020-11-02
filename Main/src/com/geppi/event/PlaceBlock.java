package com.geppi.event;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.text.DecimalFormat;

public class PlaceBlock implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlaceBlock(BlockPlaceEvent event) {



        playerHandler.setBlocksPlaced(event.getPlayer(), playerHandler.getBlocksPlaced(event.getPlayer()) + 1);

        Player player = event.getPlayer();

        Location fix = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
        if (event.isCancelled()) {

            int playerX = player.getLocation().getBlockX();
            int playerY = player.getLocation().getBlockY();
            int playerZ = player.getLocation().getBlockZ();
            Location loc = new Location(player.getWorld(), playerX, playerY - 1, playerZ);

            Block block = loc.getBlock();

                if(getBlockBelowLoc(player.getLocation()).getY() +3 < player.getLocation().getY()&& player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR && !player.isOnGround()) {
                Location loc2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() +1, player.getLocation().getZ(), player.getLocation().getYaw(), -90f);
                event.getPlayer().teleport(getBlockBelowLoc(player.getLocation()));
                event.getPlayer().teleport(loc2);
                event.getPlayer().sendMessage(colorHandler.error + "Suspected block glitching!");
                return;
            }

            if (block.getType() == player.getInventory().getItemInMainHand().getType() && !player.isOnGround()) {
                event.setCancelled(true);
                Location loc2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ(), player.getLocation().getYaw(), -90f);
                event.getPlayer().teleport(loc2);
                event.getPlayer().sendMessage(colorHandler.error + "Suspected block glitching!");

            }
        }
    }

    public Location getBlockBelowLoc(Location loc) {
        Location locBelow = loc.subtract(0, 1, 0);
        if(locBelow.getBlock().getType() == Material.AIR) {
            locBelow = getBlockBelowLoc(locBelow);
        }
        return locBelow;
    }

    public boolean isAboveGround(Player player, int checkLevels) {

        World world = player.getWorld();
        Location location = player.getLocation();

        if (location.getBlock().getType() == Material.AIR && !location.getBlock().getType().name().contains("SLAB")) {
            for (int i = 1; i < checkLevels; i++) {
                if (world.getBlockAt((int)location.getX(), location.getBlockY() - i, (int)location.getY()).getType() != Material.AIR) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isSafeLocation(Location location) {
        Block feet = location.getBlock();
        if (!feet.getType().isTransparent() && !feet.getLocation().add(0, 1, 0).getBlock().getType().isTransparent()) {
            return false; // not transparent (will suffocate)
        }
        Block head = feet.getRelative(BlockFace.UP);
        if (!head.getType().isTransparent()) {
            return false; // not transparent (will suffocate)
        }
        Block ground = feet.getRelative(BlockFace.DOWN);
        if (!ground.getType().isSolid()) {
            return false; // not solid
        }
        return true;
    }


}
