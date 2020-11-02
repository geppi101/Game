package com.geppi.event;

import com.geppi.main.Main;
import com.geppi.other.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Join implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    Inventories  inventories = new Inventories();
    ColorHandler colorHandler = new ColorHandler();
    public Bar bar;


    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerHandler.setupPlayer(event.getPlayer());
        player.setCustomName(playerHandler.rankToString(player) + playerHandler.getNickname(player));
        player.setPlayerListName(playerHandler.rankToString(player) + playerHandler.getNickname(player));

        player.setGameMode(GameMode.SURVIVAL);

        for(Player p : Bukkit.getOnlinePlayers()) {
            p.setCollidable(false);
            player.setCollidable(false);
        }
        player.setCollidable(false);


        if(playerHandler.isVanished(player) == 1) {
            event.setJoinMessage("");
            player.hidePlayer(player);
            return;
        }



        if(playerHandler.getAcceptRules(player) == 0) {
            player.setFlySpeed(0f);
            player.setWalkSpeed(0f);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 250));
            player.setAllowFlight(false);
            player.sendMessage("");
            player.sendMessage(colorHandler.main + ChatColor.BOLD.toString() + "Welcome back " + player.getName() + " chat will be disabled until you accept the rules. If you clicked escape, type /rules to try again");
            player.sendMessage("");

            if(!player.isDead()) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        inventories.rulesInv(player);
                    }
                }.runTaskLater(Main.getInstance(), 20);
            }
        }

        if(!player.hasPlayedBefore()) {
            event.setJoinMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Welcome " + player.getName());
            Location cspawn = new Location(Bukkit.getWorld("world"), -0.5,138,198.5);
            player.teleport(cspawn);

            return;
        } else {
            event.setJoinMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "+ " + ChatColor.GRAY + event.getPlayer().getName());
        }


     /*  //TODO make join bar message join for ppl who arent on
         //TODO Add Donation GUI

      if(generalInfoHandler.getEventStatus() == 1) {
            if(!bar.getBar().getPlayers().contains(player))
            bar = new Bar(Main.getInstance());

            bar.addPlayer(event.getPlayer());
        } */




    }

}
