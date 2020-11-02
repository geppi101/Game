package com.geppi.event;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import com.geppi.other.TimeFormatHandler;
import com.geppi.other.scoreboard.LobbyBoard;
import com.geppi.other.scoreboard.ScoreboardMana;
import com.geppi.other.trails.ParticleData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;
import java.util.Objects;

public class Leave implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    TimeFormatHandler timeFormatHandler = new TimeFormatHandler();

    @EventHandler(priority = EventPriority.HIGH)
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(playerHandler.getCombatLogTime(player) > 0) {
            Bukkit.getServer().broadcastMessage(colorHandler.main + "CombatLog: " +colorHandler.message + player.getName() + " has combat logged at location" + ChatColor.GRAY + " (" + player.getLocation().getBlockX() + "x " + player.getLocation().getBlockY() + "y " + player.getLocation().getBlockZ() + "z)" + colorHandler.message + "!");
            player.setHealth(0);

        }

        event.setQuitMessage(ChatColor.RED + ChatColor.BOLD.toString() + "- " + ChatColor.GRAY + event.getPlayer().getName());

        World world = player.getWorld();//get the world
        List<Entity> entList = world.getEntities();


        for(Entity current : entList){//loop through the list
            if(!(current instanceof Player)) {
                if (current.getCustomName() != null) {

                    if ((current.getCustomName()).contains(event.getPlayer().getName())) {
                        current.remove();
                    }
                }
            }
        }
        LobbyBoard board = new LobbyBoard(event.getPlayer().getUniqueId());
        if (board.hasID())
            board.stop();


        ParticleData p = new ParticleData(event.getPlayer().getUniqueId());
        if(p.hasID())
            p.endTask();
        return;
    }

}
