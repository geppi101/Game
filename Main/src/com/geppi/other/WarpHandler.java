package com.geppi.other;

import com.geppi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;

public class WarpHandler {
    ColorHandler colorHandler = new ColorHandler();

    public void setupWarps() {
        File f = new File("plugins/GameCore/" +  "warps.yml");
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        yml.addDefault("warps", "test");

        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean setWarp(String name, Player player) {
        File f = new File("plugins/GameCore/" + "warps.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        if(yml.contains("Warps." + name)) {
            player.sendMessage("This warp has already been made!");
            return true;
        }

        yml.set("Warps." + name.toUpperCase() + ".W", player.getLocation().getWorld().getName());
        yml.set("Warps." + name.toUpperCase() + ".X", player.getLocation().getBlockX());
        yml.set("Warps." + name.toUpperCase() + ".Y", player.getLocation().getBlockY());
        yml.set("Warps." + name.toUpperCase() + ".Z", player.getLocation().getBlockZ());
        yml.set("Warps." + name.toUpperCase() + ".Pitch", player.getLocation().getPitch());
        yml.set("Warps." + name.toUpperCase() + ".Yaw", player.getLocation().getYaw());
        player.sendMessage(colorHandler.warp + "Set warp " + name);


        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delWarp(String name, Player player) {
        File f = new File("plugins/GameCore/" + "warps.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        if(yml.contains("Warps." + name.toUpperCase())) {
            yml.set("Warps." + name.toUpperCase(), null);
            player.sendMessage(colorHandler.warp + "Deleted warp " + name);

            try {
                yml.save(f);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        player.sendMessage(colorHandler.warp + "Cant find warp!");

        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean warp(String name, Player player) {

        File f = new File("plugins/GameCore/" + "warps.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        if(yml.contains("Warps." + name)) {

            player.sendMessage(colorHandler.warp + "Warping to " + name + "!");

            World w = Bukkit.getServer().getWorld(yml.getString("Warps." + name + ".W"));
            int x = yml.getInt("Warps." + name + ".X");
            int y = yml.getInt("Warps." + name + ".Y");
            int z = yml.getInt("Warps." + name + ".Z");
            float pitch = (float) yml.getDouble("Warps." + name + ".Pitch");
            float yaw = (float) yml.getInt("Warps." + name + ".Yaw");


            Location location = new Location(w,x,y,z, (float) pitch, (float) yaw);

            player.teleport(location);
            player.getLocation().setPitch(pitch);
            player.getLocation().setYaw(yaw);
            return true;
        }

        player.sendMessage(colorHandler.warp + "Cant find warp!");
        return true;
    }

    public boolean getList(Player player) {
        File f = new File("plugins/GameCore/" + "warps.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);


        if(!yml.contains("Warps")) {
            player.sendMessage(colorHandler.warp + "There are no warps!");
            return true;
        }

        /*
             StringBuilder str = new StringBuilder();
            for(Player ps : Bukkit.getOnlinePlayers()){
                if(str.length() > 0){
                    str.append(", ");
                }
                str.append(playerHandler.rankToColorString(ps)+ ps.getName() + "§7");
            }
         */

        StringBuilder str = new StringBuilder();
        for (String string : yml.getConfigurationSection("Warps").getKeys(false)){
            if(str.length() > 0){
                str.append(ChatColor.GOLD + "," + ChatColor.YELLOW+ " ");
            }
            str.append(string);

        }
        player.sendMessage(colorHandler.warp + "ALl Warps:");
        player.sendMessage(ChatColor.YELLOW + str.toString());
        return true;
    }




    }

