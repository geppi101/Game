package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.bukkit.Bukkit.getServer;

public class CommandMoney implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    @EventHandler
    public void moneyCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/money")
                || args[0].equalsIgnoreCase("/balance")
                || args[0].equalsIgnoreCase("/bal")) {
            event.setCancelled(true);
            String nc = ChatColor.YELLOW.toString();
            player.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + playerHandler.getMoney(player));

        }
    }

    @EventHandler
    public void moneyAdminCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();
        if (args[0].equalsIgnoreCase("/eco") || args[0].equalsIgnoreCase("/economy")) {
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
                player.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + "Added $" + String.valueOf(args[3]) + " to " + target.getName());
                playerHandler.addMoney(target, Integer.parseInt(args[3]));
            }

            if(args[1].equalsIgnoreCase("remove")) {

                if(num > playerHandler.getMoney(target)) {
                    player.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + target.getName() + " does not have that much money to remove!");
                    return;
                }

                player.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + "Removed $" + String.valueOf(args[3]) + " to " + target.getName());
                playerHandler.removeMoney(target, Integer.parseInt(args[3]));
            }
        }

    }
}