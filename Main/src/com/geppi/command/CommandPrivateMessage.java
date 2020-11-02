package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.bukkit.Bukkit.getServer;

public class CommandPrivateMessage implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    @EventHandler
    public void moneyAdminCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");
        String nc = ChatColor.YELLOW.toString();

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/message")
                || args[0].equalsIgnoreCase("/tell")
                || args[0].equalsIgnoreCase("/msg")) {
            event.setCancelled(true);

            if(args.length < 3) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <message>");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer + "This player is offline!");
                return;
            }


            player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.DARK_PURPLE + "]" + ChatColor.ITALIC + message.replace(args[0],"").replace(args[1], "").replaceFirst(" ", ""));
            target.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.DARK_PURPLE + "]" + ChatColor.ITALIC + message.replace(args[0],"").replace(args[1], "").replaceFirst(" ", ""));

            playerHandler.setLastMessaged(player,target.getName());



        }
        if(args[0].equalsIgnoreCase("/r") || args[0].equalsIgnoreCase("/reply")) {
            event.setCancelled(true);
         //   player.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + "Added $" + String.valueOf(args[3]) + " to " + target.getName());
            if(args.length < 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <message>");
                return;
            }

            Player target = getServer().getPlayer(playerHandler.getLastMessaged(player));
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }

            player.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.DARK_PURPLE + "] " + ChatColor.ITALIC + message.replace(args[0] + " ",""));
            target.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.DARK_PURPLE + " > " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.DARK_PURPLE + "] " + ChatColor.ITALIC + message.replace(args[0] + " ",""));


        }

        /*
        [Tom > Alex] Hi


        [Alex > Tom] Hello
         */

    }

}
