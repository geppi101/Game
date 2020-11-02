package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpawn implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/spawn")) {
            //0 = off || 1 = on



            event.setCancelled(true);
            if(playerHandler.getDonateSpawn(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(playerHandler.getJailTime(player) > 0) {
                player.sendMessage(colorHandler.error + "You can't /spawn in jail!");
                return;
            }


            Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
            player.teleport(spawn);
            player.sendMessage(colorHandler.donation + "Teleporting to Spawn!");

        }
    }

}

