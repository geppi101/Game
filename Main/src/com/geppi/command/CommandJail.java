package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import com.geppi.other.TimeFormatHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import javax.xml.stream.events.StartDocument;

import static org.bukkit.Bukkit.getServer;

public class CommandJail implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    TimeFormatHandler timeFormatHandler = new TimeFormatHandler();

    @EventHandler
    public void jail(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");
        String nc = ChatColor.YELLOW.toString();

        Player player = event.getPlayer();
        if (args[0].equalsIgnoreCase("/jail")) {
            event.setCancelled(true);

            if(playerHandler.getRank(player) < 6) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }
            Player target = getServer().getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }


            player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "Jailed " + target.getName() + " for 5 minutes!");
            target.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "You have been jailed for five minutes!");
            playerHandler.setJailTime(target, 300);
            Location jail = new Location(Bukkit.getWorld("world"), 14.5,125,-27.5);
            target.teleport(jail);
            playerHandler.setInJail(target, 1);


        }
        if (args[0].equalsIgnoreCase("/unjail")) {
            event.setCancelled(true);

            if(playerHandler.getRank(player) < 6) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }
            Player target = getServer().getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }


            playerHandler.resetJailTime(target);
            Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
            target.teleport(spawn);
            playerHandler.setInJail(target, 0);

            player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "Un-jailed " + target.getName());


        }

        if(args[0].equalsIgnoreCase("/bail")) {
            if(playerHandler.getDonateBail(player) != 1) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }




            if(playerHandler.getMoney(player) < 5000) {
                player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "You need $5000 to bail!");
                return;
            }
            playerHandler.resetJailTime(player);
            playerHandler.removeMoney(player, 5000);
            Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
            player.teleport(spawn);
            playerHandler.setInJail(player, 0);

            player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "You have bailed yourself out of jail!");
        }

    }
}


