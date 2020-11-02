package com.geppi.other.redstone.listeners;


import com.geppi.main.Main;
import com.geppi.other.redstone.RedstoneLimit;
import com.geppi.other.redstone.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    RedstoneLimit redstoneLimit = new RedstoneLimit();




    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void BlockPlaceEvent(BlockPlaceEvent event) {

        String blockName = event.getBlock().getType().toString();
        boolean isRedstone = redstoneLimit.getRestrictedBlocks().contains(blockName);

        if (!isRedstone || !redstoneLimit.restrict()) {
            event.setCancelled(false);
            return;
        }

        final int minX = 0;
        final int minZ = 0;
        final int maxX = 15;
        final int maxY = 255;
        final int maxZ = 15;
        int count = 0;

        for (int x = minX; x <= maxX; ++x) {
            for (int y = 0; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    blockName = event.getBlock().getChunk().getBlock(x, y, z).getType().toString();
                    isRedstone = redstoneLimit.getRestrictedBlocks().contains(blockName);

                    if (isRedstone) {
                        count++;

                    }
                }
            }

            if (count == (redstoneLimit.getRedstoneLimit() + 1) || (count > redstoneLimit.getRedstoneLimit() + 1)) {
                if (redstoneLimit.getPlayerBypass(event.getPlayer().getUniqueId())) {
                    event.getPlayer().sendMessage(Utils.colour("&3This chunk exceeds maximum redstone, but you bypassed!"));
                    event.setCancelled(false);
                    return;
                }

                event.getPlayer().sendMessage(Utils.colour("&cThis chunk exceeds maximum redstone!"));
                event.setCancelled(true);
                return;
            }

            event.setCancelled(false);
        }
    }

}