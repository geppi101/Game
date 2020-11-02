package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandNamecolor implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/namecolor")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getDonateNameColor(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }



            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <color>");
                player.sendMessage(colorHandler.donation + "Available name colors: §2DarkGreen §6Gold §8DarkGray §aGreen §cRed §eYellow §1DarkBlue §3DarkAqua §5Purple §7Gray §9Blue §bAqua §dPink §fWhite");
                return;
            }

            player.sendMessage(colorHandler.donation + "Namecolor set!");


            if(args[1].equalsIgnoreCase("DarkGreen")) {
                playerHandler.setNickname(player, ChatColor.DARK_GREEN +player.getName());
                return;
            } 
            if(args[1].equalsIgnoreCase("Gold")) {
                playerHandler.setNickname(player, ChatColor.GOLD +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("DarkGray")) {
                playerHandler.setNickname(player, ChatColor.DARK_GRAY +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Green")) {
                playerHandler.setNickname(player, ChatColor.GREEN +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Red")) {
                playerHandler.setNickname(player, ChatColor.RED +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Yellow")) {
                playerHandler.setNickname(player, ChatColor.YELLOW +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Darkblue")) {
                playerHandler.setNickname(player, ChatColor.DARK_BLUE +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("DarkAqua")) {
                playerHandler.setNickname(player, ChatColor.DARK_AQUA +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Purple")) {
                playerHandler.setNickname(player, ChatColor.DARK_PURPLE +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Gray")) {
                playerHandler.setNickname(player, ChatColor.GRAY +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Blue")) {
                playerHandler.setNickname(player, ChatColor.BLUE +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Aqua")) {
                playerHandler.setNickname(player, ChatColor.AQUA +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("Pink")) {
                playerHandler.setNickname(player, ChatColor.LIGHT_PURPLE +player.getName());
                return;
            }
            if(args[1].equalsIgnoreCase("White")) {
                playerHandler.setNickname(player, ChatColor.DARK_GREEN +player.getName());
                return;
            }



        }
    }

}

