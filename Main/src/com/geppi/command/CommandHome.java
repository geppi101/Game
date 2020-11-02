package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import com.geppi.other.WarpHandler;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHome implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    WarpHandler warpHandler = new WarpHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/sethome")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 5) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if (!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + "/sethome <home>");
                return;
            }

            playerHandler.setHome(args[1].toUpperCase(), player);
        }


        if (args[0].equalsIgnoreCase("/delhome")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 5) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if (!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + "/delhome <home>");
                return;
            }

            playerHandler.delHome(args[1].toUpperCase(), player);
        }
        if (args[0].equalsIgnoreCase("/home")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 5) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if (!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + "/home <name>");
                return;
            }

            playerHandler.teleportHome(args[1].toUpperCase(), player);
            return;
        }

        if (args[0].equalsIgnoreCase("/homelist")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) < 5) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }


            playerHandler.getHomeList(player);

        }
    }

           /* if(this.data.getConfig().contains(args[1])) {
                player.sendMessage("The warps been made! ");
                return;
            }
            data.getConfig().set(args[1], player.getLocation());
            data.saveConfig();
            player.sendMessage("The warp has been set! ");
            return;
        } */




     /*   if (args[0].equalsIgnoreCase("/setwarp")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + "/setwarp <warp>");
                return;
            }

            warpHandler.getData().set("warps." + args[1] + ".world", player.getLocation().getWorld().getName());
            warpHandler.getData().set("warps." + args[1] + ".x", player.getLocation().getX());
            warpHandler.getData().set("warps." + args[1] + ".y", player.getLocation().getY());
            warpHandler.getData().set("warps." + args[1] + ".z", player.getLocation().getZ());
            warpHandler.saveData();
            player.sendMessage(ChatColor.GREEN + "Set warp " + args[1] + "!");


        }
        if (args[0].equalsIgnoreCase("/warp")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + "/warp <warp>");
                return;
            }

            if (warpHandler.getData().getConfigurationSection("warps." + args[1]) == null) {
                player.sendMessage(ChatColor.RED + "Warp " + args[1] + " does not exist!");
                return;
            }
            World w = Bukkit.getServer().getWorld(warpHandler.getData().getString("warps." + args[0] + ".world"));
            double x = warpHandler.getData().getDouble("warps." + args[1] + ".x");
            double y = warpHandler.getData().getDouble("warps." + args[1] + ".y");
            double z = warpHandler.getData().getDouble("warps." + args[1] + ".z");
            player.teleport(new Location(w, x, y, z));
            player.sendMessage(ChatColor.GREEN + "Teleported to " + args[1] + "!");
        }

        if (args[0].equalsIgnoreCase("/delwarp")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + "/delwarp <warp>");
                return;
            }

            if (warpHandler.getData().getConfigurationSection("warps." + args[1]) == null) {
                player.sendMessage(ChatColor.RED + "Warp " + args[1] + " does not exist!");
                return;
            }
            warpHandler.getData().set("warps." + args[1], null);
            warpHandler.saveData();
            player.sendMessage(ChatColor.GREEN + "Removed warp " + args[1] + "!");
        }
        return; */
}





