package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.Inventories;
import com.geppi.other.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandStats implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    Inventories inventories = new Inventories();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/stats")) {

            event.setCancelled(true);

            if(playerHandler.getDonateStats(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            inventories.statsInv(player);


        }
    }




}

