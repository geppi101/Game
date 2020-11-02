package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.bukkit.Bukkit.getServer;

public class CommandDonatePoint implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    @EventHandler
    public void moneyAdminCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();
        if (args[0].equalsIgnoreCase("/donatepoint")) {
            event.setCancelled(true);

            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 4) {
                player.sendMessage(colorHandler.usage + args[0] + " add/remove <player> <amount>");
                return;
            }
            Player target = getServer().getPlayer(args[2]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer + "This player is offline!");
                return;
            }

            int num;

            try {
                num = Integer.parseInt(args[3]);
            } catch (NumberFormatException ex){
                player.sendMessage(colorHandler.nonNumber);
                return;
            }


            if(args[1].equalsIgnoreCase("add")) {
                player.sendMessage(colorHandler.donation + "Added " + String.valueOf(args[3]) + " points to " + target.getName());
                playerHandler.addDonationPoints(target, Integer.parseInt(args[3]));
            }

            if(args[1].equalsIgnoreCase("remove")) {

                if(num > playerHandler.getMoney(target)) {
                    player.sendMessage(colorHandler.donation + target.getName() + " does not have enough donation points to remove!");
                    return;
                }

                player.sendMessage(colorHandler.donation + "Removed $" + String.valueOf(args[3]) + " to " + target.getName());
                playerHandler.addDonationPoints(target, Integer.parseInt(args[3]));
            }
        }

    }
}