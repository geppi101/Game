package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandScoreboardToggle implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/scoreboardtoggle") || args[0].equalsIgnoreCase("/sbtoggle")) {
            //0 = off || 1 = on
            event.setCancelled(true);

            if(playerHandler.getScoreboardToggle(player) == 0) {
                playerHandler.setScoreboardToggle(player, 1);
                player.sendMessage(colorHandler.main + "Scoreboard: " + colorHandler.message + "Disabled scoreboard");
                return;
            }
            if(playerHandler.getScoreboardToggle(player) == 1) {
                playerHandler.setScoreboardToggle(player, 0);
                player.sendMessage(colorHandler.main + "Scoreboard: " + colorHandler.message + "Enabled scoreboard");

                return;
            }


        }
    }

}

