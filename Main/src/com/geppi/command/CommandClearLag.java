package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;


public class CommandClearLag implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/clearlag")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 8) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }


            World world = player.getWorld();//get the world
            List<Entity> entList = world.getEntities();//get all entities in the world
            player.sendMessage(colorHandler.main + "Lag: " + colorHandler.message + "Clearing entities!");
            for(Entity current : entList){//loop through the list
                if (!(current instanceof Player || current.isCustomNameVisible() || current instanceof ItemFrame)){//make sure we aren't deleting mobs/players
                    current.remove();//remove it
                }
    }

}

        if (args[0].equalsIgnoreCase("/clearlagall")) {

                //0 = off || 1 = on
                event.setCancelled(true);
                if (playerHandler.getRank(player) < 8) {
                    player.sendMessage(colorHandler.noPermission);
                    return;
                }


                World world = player.getWorld();//get the world
                List<Entity> entList = world.getEntities();//get all entities in the world
                player.sendMessage(colorHandler.main + "Lag: " + colorHandler.message + "Clearing entities ALL! - /setupmap to fix (insure prison world)");
                for (Entity current : entList) {//loop through the list
                    if (!(current instanceof Player || current instanceof ItemFrame)) {//make sure we aren't deleting mobs/players
                        current.remove();//remove it
                    }
                }
            }

    }
}


