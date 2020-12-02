package com.geppi.event;

import com.geppi.main.Main;
import com.geppi.other.Inventories;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Respawn implements Listener  {

    PlayerHandler playerHandler = new PlayerHandler();
    Inventories inventories = new Inventories();

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
       // Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
        Location spawn = new Location(Bukkit.getWorld("tempsurvival"), -0.5,125,-0.5);

        event.setRespawnLocation(spawn);
        if(playerHandler.getAcceptRules(event.getPlayer()) ==0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    inventories.rulesInv(event.getPlayer());
                }
            }.runTaskLater(Main.getInstance(), 20);
        }
    }
}
