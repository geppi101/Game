package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHelp implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/help")) {
            //0 = off || 1 = on
            event.setCancelled(true);

            player.sendMessage(colorHandler.main+ "Help: " + colorHandler.message + "View all commands on the discord!");
            player.sendMessage(colorHandler.main+ "Help: " + colorHandler.message + "Join the discord " + "https://discord.gg/3YpDmXm" + " !");

        }
    }

}

