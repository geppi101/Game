package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class CommandPunish implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    //KICK - BAN - MUTE

    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/mute")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if (args.length < 3) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <reason>");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }

            Bukkit.getServer().broadcastMessage(colorHandler.antiCheat + "Muted " + target.getName() + " for " + message.replaceFirst(args[0] + " " + args[1] + " ", ""));
            playerHandler.setMuteDate(target, "Muted By: " + player.getName() + " for " + message.replaceFirst(args[0] + " " + args[1] + " ", ""));


        }
        if (args[0].equalsIgnoreCase("/ban")) {
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 9) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if (args.length < 3) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <reason>");
                return;
            }

            player.sendMessage(colorHandler.antiCheat + "Banned " + args[1]);

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spartan tempban " + args[1] + " 100y" + " " + event.getMessage().replaceFirst("/ban " + args[1] + " ", "") + " | By: " + player.getName());
        }
        if (args[0].equalsIgnoreCase("/unban")) {
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if (args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }

            player.sendMessage(colorHandler.antiCheat + "Unbanned " + args[1]);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spartan unban " + args[1]);
        }

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (args[0].equalsIgnoreCase("/tempban")) {
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if (args.length < 4) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <d/m/6m> <reason>");
                return;
            }
            if (args[2].equalsIgnoreCase("d")) {
                player.sendMessage(colorHandler.antiCheat + "Temp Banned " + args[1] + " for 1 day!");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spartan tempban " + args[1] + " 1d " + event.getMessage().replaceFirst("/tempban " + args[1] + " d ", "") + " | By: " + player.getName() + " for 1 day! " + ChatColor.GRAY + "(" + format.format(now) + ")");

                return;
            }

            if (args[2].equalsIgnoreCase("m")) {
                player.sendMessage(colorHandler.antiCheat + "Temp Banned " + args[1] + " for 1 month!");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spartan tempban " + args[1] + " 1m " + event.getMessage().replaceFirst("/tempban " + args[1] + " m ", "") + " | By: " + player.getName() + " for 1 month! " + ChatColor.GRAY + "(" + format.format(now) + ")");

                return;
            }

            if (args[2].equalsIgnoreCase("6m")) {
                player.sendMessage(colorHandler.antiCheat + "Temp Banned " + args[1] + " for 6 months!");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spartan tempban " + args[1] + " 6m " + event.getMessage().replaceFirst("/tempban " + args[1] + " 6m ", "") + " | By: " + player.getName() + " for 6 months! " + ChatColor.GRAY + "(" + format.format(now) + ")");

                return;
            }

            player.sendMessage(colorHandler.error + "Unknown time!");

            //spartan tempban <player> <time> <reason>
            return;
        }



        if (args[0].equalsIgnoreCase("/kick")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if (args.length < 3) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <reason>");
                return;
            }
            Player target = getServer().getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }

            target.kickPlayer("Kicked By: " + player.getName() + " for " + event.getMessage().replaceFirst(args[0], "".replaceFirst(args[1], "")));

        }




        if (args[0].equalsIgnoreCase("/warn")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if (args.length < 3) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <reason>");
                return;
            }
            Player target = getServer().getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }

            Bukkit.getServer().broadcastMessage(colorHandler.antiCheat + "Warned " + target.getName() + " for " + message.replaceFirst(args[0] + " " + args[1] + " ", ""));


        }



        if (args[0].equalsIgnoreCase("/unmute")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if (args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }

            player.sendMessage(colorHandler.antiCheat + "Unmuted " + target.getName());
            playerHandler.removeMute(target);
        }

        if (args[0].equalsIgnoreCase("/cheatinfo")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }
            if (args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }

            if(player.isOp()) {
                player.chat("/spartan info " + target.getName());
                return;
            }

            player.setOp(true);
            player.chat("/spartan info " + target.getName());
            player.setOp(false);
        }
    }

}

