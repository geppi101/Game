package com.geppi.event;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillStreak implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler(priority = EventPriority.HIGH)
    public void onKIll(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        if (!(event.getEntity().getKiller() instanceof org.bukkit.entity.Player)) {
            this.playerHandler.addDeath(event.getEntity(), 1);
            this.playerHandler.resetKillStreak(event.getEntity(), 0);
            playerHandler.setDonateDiscoArmorStatus(event.getEntity(), 0);
            return;
        }
        this.playerHandler.addToKillStreak(event.getEntity().getKiller(), 1);
        this.playerHandler.resetKillStreak(event.getEntity(), 0);
        this.playerHandler.addKill(event.getEntity().getKiller(), 1);
        playerHandler.setDonateDiscoArmorStatus(event.getEntity(), 0);
        this.playerHandler.addDeath(event.getEntity(), 1);
        this.playerHandler.setLastKilledByPlayer(event.getEntity(), event.getEntity().getKiller().getName());

        if(playerHandler.getKillStreak(event.getEntity().getKiller()) == 5) {
            Bukkit.getServer().broadcastMessage(playerHandler.rankToColorString(event.getEntity().getKiller()) + event.getEntity().getKiller().getName() + colorHandler.message + " has reached a kill streak of 5");
        }
        if(playerHandler.getKillStreak(event.getEntity().getKiller()) == 10) {
            Bukkit.getServer().broadcastMessage(playerHandler.rankToColorString(event.getEntity().getKiller()) + event.getEntity().getKiller().getName() + colorHandler.message + " has reached a kill streak of 10");
        }
        if(playerHandler.getKillStreak(event.getEntity().getKiller()) == 15) {
            Bukkit.getServer().broadcastMessage(playerHandler.rankToColorString(event.getEntity().getKiller()) + event.getEntity().getKiller().getName() + colorHandler.message + " has reached a kill streak of 15");
        }
        if(playerHandler.getKillStreak(event.getEntity().getKiller()) == 20) {
            Bukkit.getServer().broadcastMessage(playerHandler.rankToColorString(event.getEntity().getKiller()) + event.getEntity().getKiller().getName() + colorHandler.message + " has reached a kill streak of 20");
        }
        if(playerHandler.getKillStreak(event.getEntity().getKiller()) == 25) {
            Bukkit.getServer().broadcastMessage(playerHandler.rankToColorString(event.getEntity().getKiller()) + event.getEntity().getKiller().getName() + colorHandler.message + " has reached a kill streak of 25");
        }
        if(playerHandler.getKillStreak(event.getEntity().getKiller()) == 30) {
            Bukkit.getServer().broadcastMessage(playerHandler.rankToColorString(event.getEntity().getKiller()) + event.getEntity().getKiller().getName() + colorHandler.message + " has reached a kill streak of 30");
        }

        if (playerHandler.getRank((Player) event.getEntity()) > 5) {
            playerHandler.addToken((Player) event.getEntity().getKiller(), 5);
            playerHandler.setPlayersKilledReward(event.getEntity().getKiller(),playerHandler.getPlayersKilledReward(event.getEntity().getKiller()) +1 );
            return;
        }
        playerHandler.addToken((Player) event.getEntity().getKiller(), 1);
        playerHandler.setPlayersKilledReward(event.getEntity().getKiller(),playerHandler.getPlayersKilledReward(event.getEntity().getKiller()) +1 );

    }
}
