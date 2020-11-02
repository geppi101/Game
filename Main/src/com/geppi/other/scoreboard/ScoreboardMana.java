package com.geppi.other.scoreboard;

import com.geppi.main.Main;
import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;
import java.util.Objects;

public class ScoreboardMana implements Listener {

    private int taskID;


    public void onEnable() {

        if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player online : Bukkit.getOnlinePlayers()) {
                createBoard(online);
                start(online);
            }
    }


    public void onDisable() {

    }

    public void starting(Player player) {
        createBoard(player);
        start(player);
    }
    public void quitting(Player player) {
        LobbyBoard board = new LobbyBoard(player.getUniqueId());
        if (board.hasID())
            board.stop();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        createBoard(event.getPlayer());
        start(event.getPlayer());
    }




    public void start(Player player) {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            int count = 0;
            final LobbyBoard board = new LobbyBoard(player.getUniqueId());



            @Override
            public void run() {

                if (!board.hasID())
                    board.setID(taskID);
                if (count == 2)
                    count = 0;

                if(player == null || !player.isOnline()) {
                    Bukkit.getScheduler().cancelTask(taskID);
                    return;
                }

                switch(count) {
                    case 0: ;
                        (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR)).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&c&l<< &4&lBehindBars &c&l>>").toString());
                        break;
                    case 1:
                        (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR)).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&4&lIP: &c&l144.217.87.179").toString());
                        break;
                }

                count++;
            }


        }, 0, 40);
    }

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    public void createBoard(Player player) {

        int kills = playerHandler.getKills(player);
        double deaths = playerHandler.getDeaths(player);
        double ratio = kills / deaths;

        double value = ratio;

        DecimalFormat df = new DecimalFormat("#.00");

        String ratioOutput = df.format(value);


        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("HubScoreboard-1", "dummy",
                ChatColor.translateAlternateColorCodes('&', "&c&l<< &4&lBehindBars &c&l>>"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score1 = obj.getScore(ChatColor.GRAY + ChatColor.BOLD.toString() + "=-=-=-=-=-=-=-=-=-=");
        score1.setScore(5);

        Score score2 = obj.getScore(ChatColor.DARK_RED + ChatColor.BOLD.toString()+"Rank: "  +ChatColor.GRAY + ChatColor.stripColor(playerHandler.rankToString(player)));
        score2.setScore(4);

        Score score3 = obj.getScore(ChatColor.DARK_RED + ChatColor.BOLD.toString()+"Money: " +ChatColor.GRAY + playerHandler.getMoney(player));
        score3.setScore(3);

        Score score4 = obj.getScore(ChatColor.DARK_RED + ChatColor.BOLD.toString()+"Tokens: "+ChatColor.GRAY + playerHandler.getToken(player));
        score4.setScore(2);

        if(playerHandler.getKills(player) == 0 || playerHandler.getDeaths(player) == 0) {
            Score score5 = obj.getScore(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "K/D: " + ChatColor.GRAY + "0");
            score5.setScore(1);
        } else {
            Score score5 = obj.getScore(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "K/D: " + ChatColor.GRAY + String.valueOf(ratioOutput));
            score5.setScore(1);
        }

        Score score7 = obj.getScore(ChatColor.BLUE + ChatColor.GRAY.toString() + ChatColor.BOLD.toString() + "=-=-=-=-=-=-=-=-=-=");
        score7.setScore(0);


        player.setScoreboard(board);
    } //, kdr

}