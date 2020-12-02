package com.geppi.command;

import com.geppi.main.Main;
import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class CommandWorld implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    // FIX

    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/worldcreate")) {
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <world>");
                return;
            }

            if (Bukkit.getWorld(args[1]) != null) {
                player.sendMessage(colorHandler.main + "World: " +colorHandler.message + " World already made!");
                return;
            }

            WorldCreator worldCreator = new WorldCreator(args[1]);
            worldCreator.generateStructures(false);
            worldCreator.type(WorldType.NORMAL);

            Bukkit.getServer().createWorld(worldCreator);
            new BukkitRunnable() {
                @Override
                public void run() {
            World world = Bukkit.getServer().getWorld(args[1]);//get the world
            List<Entity> entList = world.getEntities();//get all entities in the world
            for(Entity current : entList){//loop through the list
                if (!(current instanceof Player || current.isCustomNameVisible() || current instanceof ItemFrame)){//make sure we aren't deleting mobs/players
                    current.remove();//remove it
                }
            }
                }
            }.runTaskLater(Main.getInstance(), 4500);
            Bukkit.getServer().broadcastMessage("complete");

        }

        if (args[0].equalsIgnoreCase("/worldtp")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 2) {
                player.sendMessage(colorHandler.usage + args[0] + " <world>");
                return;
            }



            Location loc = new Location(Bukkit.getWorld(args[1]), 1, 1, 1);
            player.sendMessage(colorHandler.main + "World: " + "You have been teleported to world " + args[1] + "!");
            player.teleport(loc);

        }
        if(args[0].equalsIgnoreCase("/worlds")) {

            StringBuilder str = new StringBuilder();
            for(World w : getServer().getWorlds()) {
                if(str.length() > 0){
                    str.append(", ");
                }
                str.append(colorHandler.message+ w.getName().toString() + "§7");

            }
            player.sendMessage(colorHandler.message + str.toString());

        }

    }

}

