package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPvp implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void moneyCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/pvp")) {
            event.setCancelled(true);
            if(playerHandler.getDonatePvpStatus(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            //0 = off || 1 = on
            if(playerHandler.getPvpStatus(player) == 0) {
                playerHandler.setPvpStatus(player, 1);
                player.sendMessage(colorHandler.donation + "Turned PVP off!");
                return;
            }
            if(playerHandler.getPvpStatus(player) == 1) {
                playerHandler.setPvpStatus(player, 0);
                player.sendMessage(colorHandler.donation + "Turned PVP on!");
                return;
            }

        }

    }

    }
