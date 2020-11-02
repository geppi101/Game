package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.Bukkit.getOfflinePlayer;
import static org.bukkit.Bukkit.getServer;

public class CommandPlayerHead implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/playerhead")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <player>");
                return;
            }

            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta sm = (SkullMeta) skull.getItemMeta();
            sm.setOwningPlayer(getOfflinePlayer(args[1]));
            skull.setItemMeta(sm);

            player.getInventory().addItem(skull);

        }
    }

}

