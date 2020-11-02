package com.geppi.event;

import com.geppi.other.*;
import com.geppi.other.GeneralInfoHandler;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.io.File;

public class PlayerInteractEntity implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    CrateHandler crateHandler = new CrateHandler();
    Inventories inventories = new Inventories();
    TimeFormatHandler timeFormatHandler = new TimeFormatHandler();
    GeneralInfoHandler generalInfoHandler = new GeneralInfoHandler();


    @EventHandler
    public void playerInteractCrate(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (event.getRightClicked().getCustomName().contains(event.getPlayer().getName() + "'s Pet")) {
            event.getRightClicked().remove();
            event.getPlayer().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE,
                    event.getPlayer().getLocation().getX(), event.getPlayer().getLocation().getY(),
                    event.getPlayer().getLocation().getZ(), 0);
        }


        if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Vote Crate")) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                event.setCancelled(true);

                if(playerHandler.getVoteReward(player) == 0) {
                    event.getPlayer().sendMessage(colorHandler.vote + "You do not have enough vote rewards available!");
                    return;
                }

                playerHandler.setVoteReward(player, playerHandler.getVoteReward(player) - 1);
                crateHandler.activateCrate(player);
            }
        }


        if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Stats")) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                event.setCancelled(true);

                inventories.statsInv(player);

            }
        }

        if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Event")) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                event.setCancelled(true);

                if(generalInfoHandler.getEventStatus() == 0) {
                    player.sendMessage(colorHandler.error + "No current event!");
                    return;
                }
                 if(generalInfoHandler.isLandfall() == 1) {
                     Location landfallSpawn = new Location(Bukkit.getWorld("event"),120.5,24,-74.5, 0, 0);
                     player.teleport(landfallSpawn);
                     return;
                 }

            }
        }

        if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Daily Reward")) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                event.setCancelled(true);

                File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
                YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
                long elapsedTime = System.currentTimeMillis() - playerHandler.getDailyRewardDate(player);
                long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
                if(timeUntilReuse > 0) {
                    player.sendMessage(colorHandler.coolDown + timeFormatHandler.formatTime(timeUntilReuse));
                    player.closeInventory();
                    return;
                }

                player.sendMessage(colorHandler.main + "Daily Reward: " + colorHandler.message + "You have claimed your daily reward of $500 and 25 tokens!!");
                playerHandler.addMoney(player, 500);
                playerHandler.addToken(player, 25);
                playerHandler.setDailyRewardDate(player);


            }
        }


        return;

    }



}
