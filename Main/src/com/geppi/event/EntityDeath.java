package com.geppi.event;

import com.geppi.other.PlayerHandler;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();

    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {

        if(event.getEntity() instanceof Player) {
            playerHandler.setCombatLogTime((Player) event.getEntity(), -1);
            return;
        }

        if (event.getEntity() instanceof Monster) {
            Monster monsterEnt = (Monster) event.getEntity();
            if (monsterEnt.getKiller() instanceof Player) {

                playerHandler.setEntitiesKilledReward(monsterEnt.getKiller(), playerHandler.getEntitiesKilledReward(monsterEnt.getKiller()) + 1);



            }


        }

    }
}