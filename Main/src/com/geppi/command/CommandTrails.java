package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import com.geppi.other.trails.GUI;
import com.geppi.other.trails.ParticleData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandTrails implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/trails")) {
            event.setCancelled(true);
            if(playerHandler.getDonateTrails(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            //0 = off || 1 = on


            GUI menu = new GUI();
            menu.openInventory(player);

        }
        if(args[0].equalsIgnoreCase("/trailremove")) {
            event.setCancelled(true);
            if(playerHandler.getDonateTrails(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            ParticleData p = new ParticleData(event.getPlayer().getUniqueId());
            if(p.hasID())
                p.endTask();
            return;
        }
    }

}

