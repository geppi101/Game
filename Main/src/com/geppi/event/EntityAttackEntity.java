package com.geppi.event;

import com.geppi.other.ColorHandler;
import com.geppi.other.Inventories;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class EntityAttackEntity implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    Inventories inventories = new Inventories();
    public HashMap<Player, Player> tag = new HashMap<Player, Player>();

    @EventHandler
    public void playerAttack(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            if(event.getDamager() instanceof Player) {

                Player damaged = (Player) event.getEntity();
                Player damager = (Player) event.getDamager();

                if(playerHandler.getAcceptRules(damaged) == 0) {
                    event.setCancelled(true);
                    damager.sendMessage(colorHandler.error + "This player has their pvp disabled!");
                    return;
                }

                if(event.isCancelled()) {
                   return;
                }
                playerHandler.setCombatLogTime(damaged, 5);
                playerHandler.setCombatLogTime(damager, 5);
                if(playerHandler.getPvpStatus(damaged) == 1) {
                    event.setCancelled(true);
                    damager.sendMessage(colorHandler.error + "This player has their pvp disabled!");
                    return;
                }
                if(playerHandler.getPvpStatus(damager) == 1) {
                    event.setCancelled(true);
                    damager.sendMessage(colorHandler.error + "You have your pvp disabled!");
                    return;
                }




            }//        if (event.getRightClicked().getName().equalsIgnoreCase(ChatColor.RED + ChatColor.BOLD.toString() + "Vote Crate")) {

        }



    }


}
