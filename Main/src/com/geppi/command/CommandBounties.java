package com.geppi.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;

import net.md_5.bungee.api.ChatColor;

public class CommandBounties  implements Listener {

  /* public Map<UUID, Integer> BountyMap = new HashMap<UUID, Integer>(); //data
    public Map<UUID, Integer> ABountyMap = new HashMap<UUID, Integer>(); //arrange data
    public List<UUID> duelP = new ArrayList<UUID>(); //Source to check if user is in a duel
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    @SuppressWarnings("boxing")

    public void onEnable() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

            if (yml.getInt("General.Bounty") >= 1)
                BountyMap.put(p.getUniqueId(), yml.getInt("General.Bounty"));
            ABountyMap = BountyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        }

    }

    public void onDisable() {

    }

    public int getBountyValue(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.getBountyValue.");
    }

    //Formats any string
    public String format(String s) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}"); //Just a pattern for regex
        Matcher match = pattern.matcher(s);
        while (match.find()) {
            String color = s.substring(match.start(), match.end());
            s = s.replace(color, ChatColor.of(color) + "");
            match = pattern.matcher(s);
        }
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

    @SuppressWarnings("boxing")
    public boolean setBountyValue(Player player, int num) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.getBountyValue.", num);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Calls when a player uses a command
    @SuppressWarnings({ "boxing", "deprecation" })
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        //Event Message
        String m = e.getMessage();

        //Splits
        String[] array = m.split(" ");

        //Player
        Player p = e.getPlayer();

        //If format is wrong


        //If command is not bounty then return
        if (!array[0].equalsIgnoreCase("/bounty")) return;
        if (array.length != 3 && array.length != 2) {
            p.sendMessage(format("&cUsage: &6/bounty <user / check / list> <value / user>"));
            return;
        }
        //If first argument is equal to list
        // Command: /bounty list
        if (array[1].equalsIgnoreCase("list")) {


            //does stuff for online players
            BountyMap.clear();
            for (Player op : Bukkit.getServer().getOnlinePlayers()) {
                File f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
                if (yml == null) {
                    playerHandler.setupPlayer(op);
                    f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                    yml = YamlConfiguration.loadConfiguration(f);
                }
                if (yml.getInt("General.Bounty") >= 1)
                    BountyMap.put(op.getUniqueId(), yml.getInt("General.Bounty"));
                ABountyMap = BountyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
            }

            //sends a message
            p.sendMessage(format("&8&m                                   "));
            p.sendMessage(format("&f Online Players with #ff9d00B#ffa300o#ffa900u#ffae00n#ffb400t#ffba00i#ffc000e#ffc600s"));
            p.sendMessage(format("&8&m                                   "));

            if (BountyMap.isEmpty()) {
                p.sendMessage(format("&cThere are no online players with a\nbounty!"));
            } else {
                List<String> str = new ArrayList<String>();
                for (int in = 0; in < BountyMap.keySet().toArray().length; in++) {
                    str.add(Bukkit.getOfflinePlayer((UUID)BountyMap.keySet().toArray()[(in)]).getName());
                }
                p.sendMessage(str.toString().replace("[", "").replace("]", ""));
            }
            //sends a message
            p.sendMessage(format("&8&m                                   "));
        }

        // Command: /bounty check <user>
        else if (array[1].equalsIgnoreCase("check")) {
            if (Bukkit.getOfflinePlayer(array[2]) == null) {
                p.sendMessage(format("&cUser is not a player!"));
                return;
            } Player op = Bukkit.getOfflinePlayer(array[2]).getPlayer();
            File f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
            if (yml == null) {
                playerHandler.setupPlayer(op.getPlayer());
                f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                yml = YamlConfiguration.loadConfiguration(f);
            }
            if (yml.isSet("General.Bounty")) {
                BountyMap.put(op.getUniqueId(), yml.getInt("General.Bounty"));
            }
            if (BountyMap.containsKey(Bukkit.getPlayer(array[2]).getUniqueId())) {
                p.sendMessage(format("&a" + Bukkit.getPlayer(array[2]).getName() + " has a bounty of " + BountyMap.get(Bukkit.getPlayer(array[2]).getUniqueId()) + "$"));
            } else {
                p.sendMessage(format("&c" + Bukkit.getPlayer(array[2]).getName() + " doesn't have a bounty!"));
            }
        }

        // Command: /bounty <user> <value>
        else {
            if (Bukkit.getOfflinePlayer(array[1]) == null) {
                p.sendMessage(format("&cPlayer was not found!"));
                return;
            }
            //Page
            int value = 0;

            //Parses crap
            try {
                value = Integer.parseInt(array[2]);
            } catch (NumberFormatException ex){
                p.sendMessage(colorHandler.nonNumber);
                return;
            }

            if (value < 50) {
                p.sendMessage(format("&cBounty must be at least $50!"));
                return;
            }

            if (Bukkit.getOfflinePlayer(array[1]) == null) {
                p.sendMessage(format("&cUser is not a player!"));
                return;
            }

            Player op = Bukkit.getOfflinePlayer(array[1]).getPlayer();

            File f1 = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
            if (!f1.exists())
                try {
                    f1.createNewFile();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            YamlConfiguration yml1 = YamlConfiguration.loadConfiguration(f1);
            if (yml1 == null) {
                playerHandler.setupPlayer(op.getPlayer());
                f1 = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
                yml1 = YamlConfiguration.loadConfiguration(f1);
            }

            if (yml1.getInt("General.Money") < value) {
                p.sendMessage(format("&cYou do not have that kind of money!"));
                return;
            }

            File f2 = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
            if (!f2.exists())
                try {
                    f2.createNewFile();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            YamlConfiguration yml2 = YamlConfiguration.loadConfiguration(f2);
            if (yml2 == null) {
                playerHandler.setupPlayer(op.getPlayer());
                f2 = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                yml2 = YamlConfiguration.loadConfiguration(f2);
            }

            playerHandler.removeMoney(p, value);

            int current = yml2.getInt("General.Bounty");
            int newc = current + value;
            yml2.set("General.Bounty", newc);
            try {
                yml2.save(f2);
            } catch (IOException io) {
                io.printStackTrace();
            }

        }
    }

    //Calls when a player dies
    @SuppressWarnings("boxing")
    @EventHandler
    public void onKill(PlayerDeathEvent e) {

        //Checks if the killer isn't a player
        if (!(e.getEntity().getLastDamageCause().getEntity() instanceof Player)) return;

        //Checks if the victim has a bounty on their head
        if (!BountyMap.containsKey(e.getEntity().getUniqueId())) return;

        //Checks if the victim was in a duel
        if (duelP.contains(e.getEntity().getUniqueId())) return;

        //gets user's bounty and removes them from the hashmap, sends message
        int bounty = BountyMap.get(e.getEntity().getUniqueId());
        BountyMap.remove(e.getEntity().getUniqueId());
        PlayerHandler playerHandler = new PlayerHandler();
        playerHandler.addMoney((Player) e.getEntity().getLastDamageCause().getEntity(), bounty);
        ((Player) e.getEntity().getLastDamageCause().getEntity()).sendMessage(format("&aYou claimed the bounty of " + e.getEntity().getName() + " and received " + bounty + "$"));

    } */
}
