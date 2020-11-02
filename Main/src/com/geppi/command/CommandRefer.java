package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.bukkit.Bukkit.getServer;

public class CommandRefer implements Listener  {

    ColorHandler colorHandler = new ColorHandler();
    PlayerHandler playerHandler = new PlayerHandler();

    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/refers")) {
            //0 = off || 1 = on
            event.setCancelled(true);

            if (playerHandler.getHasReferred(player) == 0) {
                player.sendMessage(colorHandler.main + "Refer: " + colorHandler.message + "You have not used your referral!");
                player.sendMessage(colorHandler.main + "Refer: " + colorHandler.message + "You currently have " + String.valueOf(playerHandler.getReferrals(player)) + " referrals!");
            }
            if (playerHandler.getHasReferred(player) == 1) {
                player.sendMessage(colorHandler.main + "Refer: " + colorHandler.message + "You have already used your referral!");
                player.sendMessage(colorHandler.main + "Refer: " + colorHandler.message + "You currently have " + String.valueOf(playerHandler.getReferrals(player)) + " referrals!");
            }

        }




        if(args[0].equalsIgnoreCase("/refer")) {
            event.setCancelled(true);

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }

            if(playerHandler.getHasReferred(player) == 1) {
                player.sendMessage(colorHandler.main + "Refer: " + colorHandler.message + "You have already referred a player!");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer + "This player is offline!");
                return;
            }

            if(target.getName().equals(player.getName())) {
                player.sendMessage(colorHandler.error + "You can not refer yourself!");
                return;
            }

            playerHandler.setHasReferred(player, 1);
            playerHandler.setReferrals(target, playerHandler.getReferrals(target) + 1);

            target.sendMessage(colorHandler.vote + "You have received a referral from " + player.getName() + "!");
            player.sendMessage(colorHandler.vote + "You have given " + target.getName() + " a referral!");

            if(playerHandler.getReferrals(target) == 5) {
                Bukkit.getServer().broadcastMessage(colorHandler.main + "Refer: " + colorHandler.message + target.getName() + " has referred 5 people!");
                return;
            }
            if(playerHandler.getReferrals(target) == 10) {
                Bukkit.getServer().broadcastMessage(colorHandler.main + "Refer: " + colorHandler.message + target.getName() + " has referred 10 people!");
                return;
            }

        }
    }


}
