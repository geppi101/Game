package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.bukkit.Bukkit.getServer;

public class CommandPay implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    @EventHandler
    public void moneyAdminCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");
        String nc = ChatColor.YELLOW.toString();

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/pay")) {
            event.setCancelled(true);

            if(args.length < 3) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <amount>");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer + "This player is offline!");
                return;
            }

            int num;

            try {
                num = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex){
                player.sendMessage(colorHandler.nonNumber);
                return;
            }

            if(playerHandler.getMoney(player) < Integer.parseInt(args[2])) {
                player.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + "You do not have enough money!");
                return;
            }
            playerHandler.removeMoney(player, num);
            playerHandler.addMoney(target, num);
            player.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + "You have payed " + num + " to " + target.getName() +"!");
            target.sendMessage(colorHandler.main + "Balance: " + colorHandler.message + "You have been payed " + num + " by " + player.getName() +"!");


        }
    }

}
