package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Bukkit.getServer;

public class CommandVote implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/vote")) {
            //0 = off || 1 = on
            event.setCancelled(true);

            player.sendMessage(colorHandler.donation + "You currently have " + String.valueOf(playerHandler.getVoteReward(player)) + " vote rewards!");

        }

        if(args[0].equalsIgnoreCase("/voteadmin")) {
            event.setCancelled(true);

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer + "This player is offline!");
                return;
            }

            playerHandler.setVoteReward(target, playerHandler.getVoteReward(target) + 1);
            target.sendMessage(colorHandler.vote + "You have received a vote reward from " + player.getName() + "!");
            player.sendMessage(colorHandler.vote + "You have given " + target.getName() + " a vote reward!");

        }
    }

}

