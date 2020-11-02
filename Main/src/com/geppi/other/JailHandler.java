package com.geppi.other;

import com.geppi.main.Main;
import com.geppi.other.scoreboard.LobbyBoard;
import com.geppi.other.scoreboard.ScoreboardMana;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Random;

public class JailHandler {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    ScoreboardMana scoreboardMana = new ScoreboardMana();
     public void Jail() {
       Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {


                    if(playerHandler.isInJail(player) == 0) {
                        continue;
                    }

                    playerHandler.setJailTime(player, playerHandler.getJailTime(player) - 1);

                    if(playerHandler.getJailTime(player) <= 0) {
                        playerHandler.setInJail(player, 0);
                        player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "You have been released!");
                        playerHandler.resetJailTime(player);
                        Location spawn = new Location(Bukkit.getWorld("world"), -0.5, 125, -0.5);
                        player.teleport(spawn);

                        continue;
                    }
                }
            }
        }, 0L, 20L);
    }
    public void combatLog() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {

                    if(playerHandler.getCombatLogTime(player) < 0) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(""));
                    }

                    if(playerHandler.getCombatLogTime(player) >= 0) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(colorHandler.main +ChatColor.BOLD.toString() + "Combat Log: " + colorHandler.message+ChatColor.BOLD.toString()+ String.valueOf(playerHandler.getCombatLogTime(player))));
                        Random r = new Random();
                        for (int i = 0; i < 5 ; i++)
                            player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation().add(
                                    r.nextDouble() * 1.2, r.nextDouble() * 1.2, r.nextDouble() * .5), 0);
                        for (int i = 0; i < 5 ; i++)
                            player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation().add(
                                    -1*(r.nextDouble() * 1.2), r.nextDouble() * 1.2, (r.nextDouble() * .5) *-1), 0);
                       playerHandler.setCombatLogTime(player, playerHandler.getCombatLogTime(player) - 1);
                   }

                }
            }
        }, 0L, 20L);
    }

    public void scoreboard() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {

                    if(playerHandler.getScoreboardToggle(player) == 1) {
                        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                        LobbyBoard board = new LobbyBoard(player.getUniqueId());

                        if (board.hasID())
                            board.stop();
                        return;
                    }
                        scoreboardMana.quitting(player);
                        scoreboardMana.starting(player);


                }
            }
        }, 0L, 60L);
    }

    public void colorArmor() {

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            private Random r = new Random();

            public void run() {
                Color c = Color.fromRGB(r.nextInt(255), r.nextInt(255), r.nextInt(255));

                for (Player p : Bukkit.getServer().getOnlinePlayers()) {



                    if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.LEATHER_HELMET && p.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Disco")) {
                        p.getInventory().setHelmet(getColorArmor(Material.LEATHER_HELMET, c));
                    }

                    if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE&& p.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Disco")) {
                        p.getInventory().setChestplate(getColorArmor(Material.LEATHER_CHESTPLATE, c));
                    }

                    if (p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS&& p.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Disco")) {
                        p.getInventory().setLeggings(getColorArmor(Material.LEATHER_LEGGINGS, c));
                    }

                    if (p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() == Material.LEATHER_BOOTS&& p.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Disco")) {
                        p.getInventory().setBoots(getColorArmor(Material.LEATHER_BOOTS, c));
                    }
                }
            }
        }, 0, 20);
    }


    private ItemStack getColorArmor(Material m, Color c) {
        ItemStack i = new ItemStack(m, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
        meta.setColor(c);
        meta.setDisplayName(colorHandler.message + "Disco");
        i.setItemMeta(meta);
        return i;
    }


}
