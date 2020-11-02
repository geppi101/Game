package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.awt.*;

public class CommandList implements Listener  {

    /*
    String list;

int count = 0;

for (Player p: Bukkit.getOnlinePlayers()) {
count++;

list += p.getName() + ", ";
}
     */




    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    String list;


    @EventHandler(priority = EventPriority.HIGHEST)
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/list")) {

           /* int count = 0;
            for (Player p: Bukkit.getOnlinePlayers()) {
                count++;
                list += playerHandler.rankToColorString(p) +p.getName() + ChatColor.WHITE + ", ";
            }
            player.sendMessage(list); */

            StringBuilder str = new StringBuilder();
            for(Player ps : Bukkit.getOnlinePlayers()){
                if(str.length() > 0){
                    str.append(", ");
                }
                str.append(playerHandler.rankToColorString(ps)+ ps.getName() + "§7");
            }
            player.sendMessage(colorHandler.main + "Online Players: " + colorHandler.message  + Bukkit.getServer().getOnlinePlayers().size());
            player.sendMessage(str.toString());



        }


    }

}

