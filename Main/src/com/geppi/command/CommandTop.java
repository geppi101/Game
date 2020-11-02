package com.geppi.command;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandTop implements Listener {
    //Data
    public Map<UUID, Integer> MoneyMap = new HashMap<UUID, Integer>();
    public Map<UUID, Integer> TokenMap = new HashMap<UUID, Integer>();

    //Arranged Data
    public Map<UUID, Integer> AMoneyMap = new HashMap<UUID, Integer>();
    public Map<UUID, Integer> ATokenMap = new HashMap<UUID, Integer>();

    //Cache Age
    public long McacheAge;
    public long TcacheAge;

    //Numbers per Page
    private int mp = 6-1;

    //Integer
    private int check = 0;

    //Just the PlayerHandler
    PlayerHandler playerHandler = new PlayerHandler();
    //Just the ColorHandler
    ColorHandler colorHandler = new ColorHandler();

    @SuppressWarnings("boxing")
    public void onEnable() {
        Bukkit.getLogger().info("" + Bukkit.getServer().getOfflinePlayers());
        for (OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()) {
            File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
            if (yml == null) {
                playerHandler.setupPlayer(p.getPlayer());
                f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
                yml = YamlConfiguration.loadConfiguration(f);
            }
            MoneyMap.put(p.getUniqueId(), yml.getInt("General.Money"));
            if (yml.isSet("General.Token")) {
                TokenMap.put(p.getUniqueId(), yml.getInt("General.Token"));
            }
            else {
                yml.set("General.Token", 0);
                try {
                    yml.save(f);
                } catch (IOException io) {
                    io.printStackTrace();
                }
                TokenMap.put(p.getUniqueId(), 0);
            }
        }
        AMoneyMap = MoneyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        ATokenMap = TokenMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        McacheAge = System.currentTimeMillis() - 5*60*1000;
        TcacheAge = System.currentTimeMillis() - 5*60*1000;
    }

    public void onDisable() {
    }

    //Formatter
    public String format(String s) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher match = pattern.matcher(s);
        while (match.find()) {
            String color = s.substring(match.start(), match.end());
            s = s.replace(color, ChatColor.of(color) + "&l");
            match = pattern.matcher(s);
        }
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

    //The command itself
    @SuppressWarnings({ "boxing", "deprecation" })
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        //Player
        Player p = e.getPlayer();

        //Message
        String m = e.getMessage();

        //Splits = Array
        String[] args = m.split(" ");

        //If command is not equal to lb or leaderboard
        if (!args[0].equalsIgnoreCase("/lb") && !args[0].equalsIgnoreCase("/leaderboard")) return;

        //If format is wrong
        if (args.length != 3) {
            p.sendMessage(format("&cUsage: &6/lb <Tokens/Money> <Page>"));
            return;
        }

        //Page
        int page = 0;

        //Parses crap
        try {
            page = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex){
            p.sendMessage(colorHandler.nonNumber);
            return;
        }

        //Sends message if number isn't positive
        if (page <= 0) {
            p.sendMessage(format("&4The number should be positive >:O"));
            return;
        }

        //If value is not available
        if (!args[1].equalsIgnoreCase("Tokens") && !args[1].equalsIgnoreCase("Token") && !args[1].equalsIgnoreCase("T") &&
                !args[1].equalsIgnoreCase("Money") && !args[1].equalsIgnoreCase("Balance") && !args[1].equalsIgnoreCase("B") && !args[1].equalsIgnoreCase("M")) {
            p.sendMessage(format("&cUse the format please!"));
            return;
        }

        if (TokenMap == null || MoneyMap == null) {
            for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
                File f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
                if (yml == null) {
                    playerHandler.setupPlayer(op.getPlayer());
                    f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                    yml = YamlConfiguration.loadConfiguration(f);
                }
                MoneyMap.put(op.getUniqueId(), yml.getInt("General.Money"));
                TokenMap.put(p.getUniqueId(), yml.getInt("General.Token"));
            }
            AMoneyMap = MoneyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
            ATokenMap = TokenMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
            McacheAge = System.currentTimeMillis() - 5*60*1000;
            TcacheAge = System.currentTimeMillis() - 5*60*1000;
        }

        //
        if (check == 0) {
            for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
                File f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
                if (yml == null) {
                    playerHandler.setupPlayer(op.getPlayer());
                    f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                    yml = YamlConfiguration.loadConfiguration(f);
                }
                MoneyMap.put(op.getUniqueId(), yml.getInt("General.Money"));
                TokenMap.put(p.getUniqueId(), yml.getInt("General.Token"));
            }
            AMoneyMap = MoneyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
            ATokenMap = TokenMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
            McacheAge = System.currentTimeMillis() - 5*60*1000;
            TcacheAge = System.currentTimeMillis() - 5*60*1000;
        }

        //Money/Balance/B format
        if (args[1].equalsIgnoreCase("Money") || args[1].equalsIgnoreCase("Balance") || args[1].equalsIgnoreCase("B") || args[1].equalsIgnoreCase("M")) {

            //Check pages
            if (AMoneyMap == null || page > Math.ceil(((double) AMoneyMap.keySet().toArray().length)/mp)) {
                p.sendMessage(format("&cNo more pages!"));
                return;
            }

            //Checks Cache Age
            if (System.currentTimeMillis() >= (McacheAge+5)*60*1000) {

                //Sends message on how long it took to rearrange
                int time = (int) System.currentTimeMillis(); McacheAge = (int) System.currentTimeMillis();
                p.sendMessage("Reloading Money Leaderboard");
                McacheAge = System.currentTimeMillis() - 5*60*1000;
                TcacheAge = System.currentTimeMillis() - 5*60*1000;
                for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
                    File f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                    YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
                    if (yml == null) {
                        playerHandler.setupPlayer(p.getPlayer());
                        f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                        yml = YamlConfiguration.loadConfiguration(f);
                    }
                    MoneyMap.put(op.getUniqueId(), yml.getInt("General.Money"));
                    TokenMap.put(p.getUniqueId(), yml.getInt("General.Token"));
                }
                AMoneyMap = MoneyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
                ATokenMap = TokenMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
                p.sendMessage(format("&9It took &f" + (time-System.currentTimeMillis()) + "ms&9 to rearrange the Top..."));
            }

            //Header
            p.sendMessage(format("&8&m          &a Money Leaderboard &6(" + page + "/" + (int) Math.ceil(((double) AMoneyMap.keySet().toArray().length/mp)) + ") &8&m          "));

            //Sends the list
            if (AMoneyMap.keySet().toArray().length > 0) {
                for (int i = (page-1)*mp; i <= page*mp; i++) {
                    if (i > AMoneyMap.keySet().toArray().length-1) break;
                    p.sendMessage(format("&c" + (i+1) + ". &f" + Bukkit.getOfflinePlayer((UUID) AMoneyMap.keySet().toArray()[i]).getName() + " &8:" + " &f$" + AMoneyMap.get(AMoneyMap.keySet().toArray()[i])));
                }
            }

            //Sends a message if no pages
            else {
                p.sendMessage(format("&cThere are no players with a balance."));
            }

            //Text Components
            TextComponent f1 = new TextComponent(format("&8&m     ")); TextComponent f2 = new TextComponent(format("&6 - ")); TextComponent f3 = new TextComponent(format("&8&m     "));
            TextComponent nextx = new TextComponent(format("&7&mNext&r ")); TextComponent next = new TextComponent(format("&e&lNext "));
            TextComponent backx = new TextComponent(format(" &7&mBack")); TextComponent back = new TextComponent(format(" &e&lBack"));

            //Text Component Events
            next.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format("&7Click to view next page.")).create()));
            back.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format("&7Click to view previous page.")).create()));
            next.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lb m " + (page+1)));
            back.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lb m " + (page-1)));

            //Footer
            if (page == 1) {
                if (AMoneyMap.keySet().toArray().length <= mp) {
                    p.spigot().sendMessage(f1, backx, f2, nextx, f3);
                } else {
                    p.spigot().sendMessage(f1, backx, f2, next, f3);
                }
            }
            else {
                if (page*mp < AMoneyMap.keySet().toArray().length) {
                    p.spigot().sendMessage(f1, back, f2, next, f3);
                } else {
                    p.spigot().sendMessage(f1, back, f2, nextx, f3);
                }
            }

        }

        //Money/Balance/B format
        if (args[1].equalsIgnoreCase("Tokens") || args[1].equalsIgnoreCase("Token") || args[1].equalsIgnoreCase("T")) {

            //Check pages
            if (page > Math.ceil(((double) ATokenMap.keySet().toArray().length)/mp)) {
                p.sendMessage(format("&cNo more pages!"));
                return;
            }

            //Checks Cache Age
            if (System.currentTimeMillis() >= (TcacheAge+5)*60*1000) {

                //Sends message on how long it took to rearrange
                int time = (int) System.currentTimeMillis(); TcacheAge = (int) System.currentTimeMillis();
                p.sendMessage("Reloading Token Leaderboard");
                McacheAge = System.currentTimeMillis() - 5*60*1000;
                TcacheAge = System.currentTimeMillis() - 5*60*1000;
                for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
                    File f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                    YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
                    if (yml == null) {
                        playerHandler.setupPlayer(op.getPlayer());
                        f = new File("plugins/GameCore/PlayerData/" + op.getUniqueId() + ".yml");
                        yml = YamlConfiguration.loadConfiguration(f);
                    }
                    MoneyMap.put(op.getUniqueId(), yml.getInt("General.Money"));
                    TokenMap.put(p.getUniqueId(), yml.getInt("General.Token"));
                }
                AMoneyMap = MoneyMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
                ATokenMap = TokenMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
                p.sendMessage(format("&9It took &f" + (time-System.currentTimeMillis()) + "ms&9 to rearrange the Top..."));
            }

            //Header
            p.sendMessage(format("&8&m          &a Token Leaderboard &6(" + page + "/" + (int) Math.ceil(((double) ATokenMap.keySet().toArray().length/mp)) + ") &8&m          "));

            //Sends the list
            if (ATokenMap.keySet().toArray().length > 0) {
                for (int i = (page-1)*mp; i <= page*mp; i++) {
                    if (i > ATokenMap.keySet().toArray().length-1) break;
                    p.sendMessage(format("&c" + (i+1) + ". &f" + Bukkit.getOfflinePlayer((UUID) ATokenMap.keySet().toArray()[i]).getName() + " &8:" + " &f" + ATokenMap.get(ATokenMap.keySet().toArray()[i]) + " Tokens"));
                }
            }

            //Sends a message if no pages
            else {
                p.sendMessage(format("&cThere are no players with a balance."));
            }

            //Text Components
            TextComponent f1 = new TextComponent(format("&8&m     ")); TextComponent f2 = new TextComponent(format("&6 - ")); TextComponent f3 = new TextComponent(format("&8&m     "));
            TextComponent nextx = new TextComponent(format("&7&mNext&r ")); TextComponent next = new TextComponent(format("&e&lNext "));
            TextComponent backx = new TextComponent(format(" &7&mBack")); TextComponent back = new TextComponent(format(" &e&lBack"));

            //Text Component Events
            next.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format("&7Click to view next page.")).create()));
            back.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format("&7Click to view previous page.")).create()));
            next.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lb t " + (page+1)));
            back.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lb t " + (page-1)));

            //Footer
            if (page == 1) {
                if (ATokenMap.keySet().toArray().length <= mp) {
                    p.spigot().sendMessage(f1, backx, f2, nextx, f3);
                } else {
                    p.spigot().sendMessage(f1, backx, f2, next, f3);
                }
            }
            else {
                if (page*mp < ATokenMap.keySet().toArray().length) {
                    p.spigot().sendMessage(f1, back, f2, next, f3);
                } else {
                    p.spigot().sendMessage(f1, back, f2, nextx, f3);
                }
            }
        }
    }
}
