package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpeed implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/speed")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <1-10>");
                return;
            }


            try {
                int speed = Integer.parseInt(args[1]);
                player.setFlySpeed((float)speed / 10.0f);
                player.setWalkSpeed((float)speed / 10.0f);
                player.sendMessage(colorHandler.main + "Speed: " + colorHandler.message + "Set to " + speed);
            } catch (IllegalArgumentException e) {
                player.sendMessage(colorHandler.usage + args[0] + " <1-10>");
            }

        }
        if(args[0].equalsIgnoreCase("/speedreset")) {
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }



                player.setFlySpeed(0.1f);
                player.setWalkSpeed(0.2f);
                player.sendMessage(colorHandler.main + "Speed: " + colorHandler.message + "Has been reset ");

        }


    }

}

