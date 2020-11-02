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

public class CommandRename implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/rename")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <name>");
                return;
            }

            ItemStack inhand = player.getInventory().getItemInMainHand();
            if(player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                player.sendMessage(colorHandler.error + "You do not have an item in your hand!");
                return;
            }

            ItemMeta im = inhand.getItemMeta();
            im.setDisplayName(args[1].replaceAll("_", " "));
            inhand.setItemMeta(im);
//Done!

            player.getInventory().setItemInMainHand(inhand);

        }
    }

}

