package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandGamemode implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/gamemode") || args[0].equalsIgnoreCase("/gm")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + args[0] + " <gamemode>");
                return;
            }

            if(playerHandler.getDonateDiscoArmorStatus(player) == 1) {
                player.sendMessage(colorHandler.error + "You can not preform this command in disco armor!");
                return;
            }




            if(args[1].equalsIgnoreCase("creative")
                    || args[1].equalsIgnoreCase("c")
                    || args[1].equalsIgnoreCase("1")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(colorHandler.main + "Gamemode: " +colorHandler.message + "You have updated your gamemode to " + args[1] + "!");

                return;
            } else
            if(args[1].equalsIgnoreCase("survival")
                    || args[1].equalsIgnoreCase("s")
                    || args[1].equalsIgnoreCase("0")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(colorHandler.main + "Gamemode: "+colorHandler.message + "You have updated your gamemode to " + args[1] + "!");

                return;
            } else
            if(args[1].equalsIgnoreCase("adventure")
                    || args[1].equalsIgnoreCase("a")
                    || args[1].equalsIgnoreCase("2")) {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(colorHandler.main + "Gamemode: " +colorHandler.message+ "You have updated your gamemode to " + args[1] + "!");

                return;
            } else
            if(args[1].equalsIgnoreCase("spectator")
                    || args[1].equalsIgnoreCase("spec")
                    || args[1].equalsIgnoreCase("3")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(colorHandler.main + "Gamemode: "+colorHandler.message + "You have updated your gamemode to " + args[1] + "!");

                return;
            } else {

                player.sendMessage(colorHandler.error + "Invalid Gamemode!");

            }
        }
    }

}

