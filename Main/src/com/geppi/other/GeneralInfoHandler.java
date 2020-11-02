package com.geppi.other;

import com.geppi.other.ColorHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class GeneralInfoHandler {
    ColorHandler colorHandler = new ColorHandler();

    public void setupGeneral() {
        File f = new File("plugins/GameCore/" + "generalinfo.yml");
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        yml.addDefault("eventStatus", Integer.valueOf(0));
        yml.addDefault("eventLandfall", Integer.valueOf(0));


        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getEventStatus() {
        File f = new File("plugins/GameCore/" + "generalinfo.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("eventStatus");
    }

    public boolean setEventStatus(int status) {
        File f = new File("plugins/GameCore/" + "generalinfo.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("eventStatus", status);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public long isLandfall() {
        File f = new File("plugins/GameCore/" + "generalinfo.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("eventLandfall");
    }

    public boolean setLandfall(int status) {
        File f = new File("plugins/GameCore/" + "generalinfo.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("eventLandfall", status);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}