package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.Inventories;
import com.geppi.other.PlayerHandler;
import com.geppi.other.pets.CustomPet;
import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.WorldServer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandPet implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    Inventories inventories = new Inventories();

    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/pet")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if(playerHandler.getDonateFeed(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

           // inventories.petInv(player);

            if(args.length != 2) {
                player.sendMessage(colorHandler.donation + "/pet <type>");
                player.sendMessage(colorHandler.donation + "Available Pets: Creeper, Skeleton, Polarbear, Chicken, Rabbit, Wolf, Turtle, Parrot, Pig, Sheep, Ocelot, Fox, Spider, Zombie, Bee");
                return;
            }

            /*
             World world = player.getWorld();//get the world
            List<Entity> entList = world.getEntities();//get all entities in the world
            player.sendMessage(colorHandler.main + "Lag: " + colorHandler.message + "Clearing entities!");
            for(Entity current : entList){//loop through the list
                if (!(current instanceof Player || current.isCustomNameVisible() == true)){//make sure we aren't deleting mobs/players
                    current.remove();//remove it
                }
             */

            //make check to see if player has a pet current time

            player.sendMessage(colorHandler.donation + "Spawned your pet " + args[1]);

            if(args[1].equalsIgnoreCase("creeper")) {
                makePet(player, EntityTypes.CREEPER);
                return;
            }

            if(args[1].equalsIgnoreCase("skeleton")) {
                makePet(player, EntityTypes.SKELETON);
                return;
            }
            if(args[1].equalsIgnoreCase("polarbear")) {
                makePet(player, EntityTypes.POLAR_BEAR);
                return;
            }
            if(args[1].equalsIgnoreCase("chicken")) {
                makePet(player, EntityTypes.CHICKEN);
                return;
            }
            if(args[1].equalsIgnoreCase("rabbit")) {
                makePet(player, EntityTypes.RABBIT);
                return;
            }
            if(args[1].equalsIgnoreCase("wolf")) {
                makePet(player, EntityTypes.WOLF);
                return;
            }
            if(args[1].equalsIgnoreCase("turtle")) {
                makePet(player, EntityTypes.TURTLE);
                return;
            }
            if(args[1].equalsIgnoreCase("parrot")) {
                makePet(player, EntityTypes.PARROT);
                return;
            }
            if(args[1].equalsIgnoreCase("pig")) {
                makePet(player, EntityTypes.PIG);
                return;
            }

            if(args[1].equalsIgnoreCase("sheep")) {
                makePet(player, EntityTypes.SHEEP);
                return;
            }
            if(args[1].equalsIgnoreCase("ocelot")) {
                makePet(player, EntityTypes.OCELOT);
                return;
            }
            if(args[1].equalsIgnoreCase("fox")) {
                makePet(player, EntityTypes.FOX);
                return;
            }
            if(args[1].equalsIgnoreCase("spider")) {
                makePet(player, EntityTypes.SPIDER);
                return;
            }
            if(args[1].equalsIgnoreCase("zombie")) {
                makePet(player, EntityTypes.ZOMBIE);
                return;
            }
            if(args[1].equalsIgnoreCase("bee")) {
                makePet(player, EntityTypes.BEE);
                return;
            }

            player.sendMessage(colorHandler.donation + "Unknown Pet");



        }
    }

    public void makePet(Player player, EntityTypes type) {
        CustomPet pet = new CustomPet(player.getLocation(), player, type);
        pet.setCustomName(new ChatComponentText(colorHandler.message
                + player.getName() + "'s Pet"));
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        world.addEntity(pet);

    }

}

