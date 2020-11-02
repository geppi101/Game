package com.geppi.command;

import com.geppi.main.Main;
import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getServer;

public class CommandContraband implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/contraban") || args[0].equalsIgnoreCase("/cb")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            if (playerHandler.getRank(player) > 6) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if (args.length < 3) {
                player.sendMessage(colorHandler.usage + args[0] + " <player> <sword/bow>");
                return;
            }

            Player target = getServer().getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(colorHandler.offlinePlayer + "This player is offline!");
                return;
            }



            if (args[2].equalsIgnoreCase("sword")) {
                sendMessage(player, target, "sword");
                return;
            }

            if (args[2].equalsIgnoreCase("bow")) {
                sendMessage(player, target, "Bow");
                return;
            }

            player.sendMessage(colorHandler.usage + args[0] + " <player> <sword/bow>");


        }
    }

    public void sendMessage(Player player, Player p, String msg) {
        new BukkitRunnable() {
            @Override
            public void run() {
                p.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + "You must drop your sword to "  + player.getName() + " or you will be jailed!");
                p.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + "5 seconds to drop your " + ChatColor.RED + msg  +colorHandler.message+ " to "+ChatColor.YELLOW + player.getName() );
                player.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + p.getName() + " Has 5 seconds to drop their " + ChatColor.RED + msg);
                cancel();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + "4 seconds to drop your " +ChatColor.RED + msg +colorHandler.message+ " to " +ChatColor.YELLOW+ player.getName() );
                        player.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + p.getName() + " Has 4 seconds to drop their " + ChatColor.RED + msg);

                        cancel();
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                p.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + "3 seconds to drop your " + ChatColor.RED +msg +colorHandler.message + " to "+ChatColor.YELLOW + player.getName() );
                                player.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + p.getName() + " Has 3 seconds to drop their " + ChatColor.RED + msg);

                                cancel();
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        p.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + "2 seconds to drop your " + ChatColor.RED +msg +colorHandler.message+ " to " +ChatColor.YELLOW+ player.getName() );
                                        player.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + p.getName() + " Has 2 seconds to drop their " + ChatColor.RED + msg);

                                        cancel();
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                p.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + "1 seconds to drop your " +ChatColor.RED + msg +colorHandler.message + "  to "+ChatColor.YELLOW + player.getName() );
                                                player.sendMessage(colorHandler.main + "Contraban: " + colorHandler.message + p.getName() + " Has 1 seconds to drop their " + ChatColor.RED + msg);

                                                cancel();
                                            }
                                        }.runTaskLater(Main.getInstance(), 20);
                                    }
                                }.runTaskLater(Main.getInstance(), 20);
                            }
                        }.runTaskLater(Main.getInstance(), 20);
                    }
                }.runTaskLater(Main.getInstance(), 20);

            }
        }.runTaskLater(Main.getInstance(), 20);

    }
}

