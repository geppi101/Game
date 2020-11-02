package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHat implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/hat")) {


            event.setCancelled(true);
            if(playerHandler.getDonateHat(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                player.sendMessage(colorHandler.donation + "You do not have an item in your hand!");
                return;
            }

            if(player.getInventory().getHelmet() == null) {
                player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
                player.getInventory().remove(player.getInventory().getItemInMainHand());
                return;
            }

            player.sendMessage(colorHandler.donation + "You must take off your current hat!");

        }
    }

}

