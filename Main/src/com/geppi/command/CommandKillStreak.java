package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandKillStreak implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler  = new ColorHandler();
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        int kills = playerHandler.getKills(player);
        double deaths = playerHandler.getDeaths(player);
        double ratio = kills / deaths;

        if (event.getMessage().equalsIgnoreCase("/killstreak")
                || event.getMessage().equalsIgnoreCase("/ks")) {
            event.setCancelled(true);
            player.sendMessage(colorHandler.main + "User: " + colorHandler.message + player.getName());
            player.sendMessage(colorHandler.main + "Kill Streak: " + colorHandler.message + playerHandler.getKillStreak(player));
            player.sendMessage(colorHandler.main + "Kills: " + colorHandler.message + playerHandler.getKills(player));
            player.sendMessage(colorHandler.main + "Deaths: " + colorHandler.message + playerHandler.getDeaths(player));
            player.sendMessage(colorHandler.main + "Last Killed By: " + colorHandler.message + playerHandler.getLKBP(player));
            player.sendMessage(colorHandler.main + "K/D Ratio: " + colorHandler.message + String.valueOf(ratio));

        }
    }
}
