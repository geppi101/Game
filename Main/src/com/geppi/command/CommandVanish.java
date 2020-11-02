package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandVanish implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/vanish") || args[0].equalsIgnoreCase("/v")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(playerHandler.isVanished(player) == 1) {
                playerHandler.setVanished(player, 0);

                for(Player online : Bukkit.getOnlinePlayers()) {
                    online.showPlayer(player);
                }

                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "+ " + ChatColor.GRAY + event.getPlayer().getName());

                return;
            }
            if(playerHandler.isVanished(player) == 0) {
                playerHandler.setVanished(player, 1);
                for(Player online : Bukkit.getOnlinePlayers()) {
                    online.hidePlayer(player);
                }
                Bukkit.getServer().broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "- " + ChatColor.GRAY + event.getPlayer().getName());

                return;
            }


            player.sendMessage(colorHandler.donation + "You must take off your current hat!");

        }
    }

}

