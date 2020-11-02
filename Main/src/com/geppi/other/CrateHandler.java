package com.geppi.other;

import com.geppi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class CrateHandler {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    String[] prizes = {"Material:Diamond", "Item:Dirt", "Vote:Keys", "Nothing:Nothing"};

    public void enable() {
        Main.getInstance().random = new Random();
       Main.getInstance().crateUsesMap = new HashMap<UUID, Integer>();
    }





    public void activateCrate(Player player) {
        if (Main.getInstance().crateUsesMap.get(player.getUniqueId()) == null) { Main.getInstance().crateUsesMap.put(player.getUniqueId(), 0); }


        Main.getInstance().crateUsesMap.put(player.getUniqueId(), Main.getInstance().crateUsesMap.get(player.getUniqueId())+1);

        String prize = choosePrize();
        String[] prizeIndex = prize.split("\\:");
        String vote = colorHandler.vote + player.getName() + " has won ";

        if(prize.contains("Material")) {
            Bukkit.getServer().broadcastMessage(vote + "a Material: " +prizeIndex[1]);
            player.getInventory().addItem(new ItemStack(Material.valueOf(prizeIndex[1].toUpperCase())));
        } else if (prize.contains("Vote")) {
            Bukkit.getServer().broadcastMessage(vote + "a Vote: 2 "  + prizeIndex[1]);
            playerHandler.setVoteReward(player, playerHandler.getVoteReward(player) +2);
        } else if (prize.contains("Item")) {
            Bukkit.getServer().broadcastMessage(vote + "a Item: "  + prizeIndex[1]);
            player.getInventory().addItem(new ItemStack(Material.valueOf(prizeIndex[1].toUpperCase())));
        } else if (prize.contains("Nothing")) {
            Bukkit.getServer().broadcastMessage(vote + "Nothing!");
        }


    }

    public String choosePrize(){
        return prizes[Main.getInstance().random.nextInt(prizes.length)];

    }
}
