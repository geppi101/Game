package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.Inventories;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;

public class CommandToken implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    Inventories inventories = new Inventories();

    @EventHandler
    public void tokenCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/token")
                || args[0].equalsIgnoreCase("/tokens")) {
            event.setCancelled(true);
            player.sendMessage(colorHandler.main + "Tokens: " + colorHandler.message + playerHandler.getToken(player));

        }
    }

    @EventHandler
    public void tokenShopCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/tokenshop")
                || args[0].equalsIgnoreCase("/tshop")) {
            event.setCancelled(true);

           /* if(hasAvaliableSlot(player)) {
                player.sendMessage("You do not have a open slot!");
                return;
            } */

            inventories.tokenShopInv(player);

        }
    }

    public boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void tokenAdminCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");
        String nc = ChatColor.YELLOW.toString();

        Player player = event.getPlayer();
        if (args[0].equalsIgnoreCase("/tokeneco") || args[0].equalsIgnoreCase("/tokeneconomy")) {
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
                player.sendMessage(colorHandler.main + "Token: " + colorHandler.message + "Added " + String.valueOf(args[3]) + " to " + target.getName());
                playerHandler.addToken(target, Integer.parseInt(args[3]));
            }

            if(args[1].equalsIgnoreCase("remove")) {

                if(num > playerHandler.getToken(target)) {
                    player.sendMessage(colorHandler.main + "Token: " + colorHandler.message + target.getName() + " does not have that many tokens to remove!");
                    return;
                }

                player.sendMessage(colorHandler.main + "Token: " + colorHandler.message + "Removed " + String.valueOf(args[3]) + " to " + target.getName());
                playerHandler.removeToken(target, Integer.parseInt(args[3]));
            }
        }

    }
}