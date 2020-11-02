package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandFeed implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void feedCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/feed")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getDonateFeed(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

           player.setFoodLevel(25);

            player.sendMessage(colorHandler.donation + "You have been fed!");

        }
    }

}

