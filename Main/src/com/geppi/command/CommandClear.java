package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandClear implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/clear")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 6) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            player.getInventory().clear();
            player.sendMessage(colorHandler.main + "Inventory: " + colorHandler.message + "Inventory cleared!");
            playerHandler.setDonateDiscoArmorStatus(player, 0);
        }
    }

}

