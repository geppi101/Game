package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandNickname implements Listener{

        PlayerHandler playerHandler = new PlayerHandler();
        ColorHandler colorHandler = new ColorHandler();


        @EventHandler
        public void moneyCommand(PlayerCommandPreprocessEvent event) {

            String message = event.getMessage();
            String[] args = message.split(" ");

            Player player = event.getPlayer();



            if(args[0].equalsIgnoreCase("/nickname")
                    || args[0].equalsIgnoreCase("/nick")) {
                event.setCancelled(true);

                if(playerHandler.getDonateNickname(player) == 0) {
                    player.sendMessage(colorHandler.noPermission);
                    return;
                }

                if(args.length == 1) {
                    player.sendMessage(colorHandler.usage + args[0] + " <name>");
                    return;
                }



             /*  if(args[1].length() < 3 || args[1].length() > 16) {
                    player.sendMessage(colorHandler.donation + "Your nickname must be 3-16 characters long and can not include spaces!");
                    return;
                } */

                if(args[1].contains("&k") || args[1].contains("&l") || args[1].contains("&m") || args[1].contains("&n") || args[1].contains("&o") || args[1].contains("&4") || args[1].contains("&0")) {
                    player.sendMessage(colorHandler.donation + "You can not use special characters, dark red, or black in your nickname!");
                    return;
                }
                //playerHandler.setNickname(player, args[1].replaceAll("&","§"));

                if(playerHandler.getRank(player) < 10) {
                    if(args[1].contains("#")) {
                        player.sendMessage(colorHandler.donation + "You may not use Hex Codes!");
                        return;
                    }
                }


               if(playerHandler.getRank(player) < 10 && !ChatColor.stripColor(args[1].replaceAll("&","§")).equalsIgnoreCase(player.getName())) {
                   player.sendMessage(colorHandler.donation + "You can use colors, but your name must remain the same!");
                   return;
               }

                playerHandler.setNickname(player, format(args[1]));

                player.setCustomName(playerHandler.rankToString(player) + playerHandler.getNickname(player));
                player.setPlayerListName(playerHandler.rankToString(player) + playerHandler.getNickname(player));
                player.sendMessage(colorHandler.donation + "You changed your nickname to " + args[1].replaceAll("&","§"));
            }
        }

    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private String format(String msg) {
        if(Bukkit.getVersion().contains("1.16")) {

            Matcher match = pattern.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


}
