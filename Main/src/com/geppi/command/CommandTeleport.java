package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.bukkit.Bukkit.getServer;

public class CommandTeleport implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/teleport") || args[0].equalsIgnoreCase("/tp")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length == 1) {
                player.sendMessage(colorHandler.usage + args[0] +" <player> [player]");
                return;
            }
            if(args.length == 2) { //teleport 1


                Player target = getServer().getPlayer(args[1]);
                if(target == null) {
                    player.sendMessage(colorHandler.offlinePlayer );
                    return;
                }

                player.teleport(target);
                player.sendMessage(colorHandler.main + "Teleport: " + colorHandler.message +"teleporting to " + target.getName() + "!");
                target.sendMessage(colorHandler.main + "Teleport: " + colorHandler.message +player.getName() + " is teleporting to you!");

                return;
            }

            if(args.length ==3) { //teleport 1


                Player target1 = getServer().getPlayer(args[1]);
                if(target1 == null) {
                    player.sendMessage(colorHandler.offlinePlayer );
                    return;
                }
                Player target2 = getServer().getPlayer(args[2]);
                if(target2 == null) {
                    player.sendMessage(colorHandler.offlinePlayer);
                    return;
                }


                target1.teleport(target2);
                target1.sendMessage(colorHandler.main + "Teleport: " + colorHandler.message +target1.getName() + " teleporting to " + target2.getName() + "!");
                target2.sendMessage(colorHandler.main + "Teleport: " + colorHandler.message +target1.getName() + " teleporting to " + target2.getName() + "!");

                return;
            }

            player.sendMessage(colorHandler.usage + args[0] +" <player> [player]");

        }
    }

}

