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

public class MineHandler {
    ColorHandler colorHandler = new ColorHandler();

    public void setupMines() {
        File f = new File("plugins/GameCore/" +  "mines.yml");
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        yml.addDefault("mines", "test");
        yml.addDefault("Mines.mine1", "");
        yml.addDefault("Mines.mine2", "");
        yml.addDefault("Mines.mine3", "");
        yml.addDefault("Mines.mine4", "");


        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getMineResetTime(String parkourNumber) {
        File f = new File("plugins/GameCore/" + "mines.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("Mines.mine" + parkourNumber);
    }

    public boolean setMineResetTime(String parkourNumber) {
        File f = new File("plugins/GameCore/" + "mines.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Mines.mine" + parkourNumber, System.currentTimeMillis());
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



}

