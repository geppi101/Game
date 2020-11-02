package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandClearChat implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/clearchat")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            for (int x = 0; x < 150; x++){
                Bukkit.getServer().broadcastMessage("");
            }

            Bukkit.getServer().broadcastMessage(colorHandler.main + "Chat: " + colorHandler.message + player.getName() + " has cleared chat");

        }
    }

}

/*
for (int x = 0; x < 150; x++){
    Bukkit.broadcastMessage("");
}
 */