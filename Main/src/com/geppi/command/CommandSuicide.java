package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSuicide implements Listener {

    ColorHandler colorHandler = new ColorHandler();
    PlayerHandler playerHandler = new PlayerHandler();

    @EventHandler
    public void moneyAdminCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");
        if(args[0].equalsIgnoreCase("/suicide")) {

            if(playerHandler.getJailTime(event.getPlayer()) > 0) {
                event.getPlayer().sendMessage(colorHandler.error + "You can't /suicide in jail!");
                return;
            }


            event.setCancelled(true);
            event.getPlayer().setHealth(0);
        }
    }


}
