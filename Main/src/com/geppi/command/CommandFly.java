package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandFly implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler(priority = EventPriority.HIGHEST)
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/fly")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) != 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 2) {
                if(player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage("Disabled Flight");
                } else {
                    player.setAllowFlight(true);
                    player.sendMessage("Enabled Flight");
                }
                return;
            }

            Player target = Bukkit.getServer().getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }
            if(target.getAllowFlight()) {
                target.setAllowFlight(false);
                player.sendMessage(colorHandler.main + "Fly: " + colorHandler.message + "Disabled " + target.getName() + "'s flight!");
                target.sendMessage(colorHandler.main + "Fly: " + colorHandler.message + "Your flight has been disabled!");
            } else {
                target.setAllowFlight(true);
                player.sendMessage(colorHandler.main + "Fly: " + colorHandler.message + "Enabled " + target.getName() + "'s flight!");
                target.sendMessage(colorHandler.main + "Fly: " + colorHandler.message + "Your flight has been enabled!");
            }


        }

    }

}

