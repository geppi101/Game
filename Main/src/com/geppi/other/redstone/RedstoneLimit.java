package com.geppi.other.redstone;

import com.geppi.command.CommandDispose;
import com.geppi.main.Main;
import com.geppi.other.redstone.commands.CommandManager;
import com.geppi.other.redstone.listeners.BlockPlace;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class RedstoneLimit  {

    private ConsoleCommandSender console;
    private int redstoneLimit;
    private final Map<UUID, Boolean> playersBypassing = new HashMap<UUID, Boolean>();
    private boolean restrict;
    private List<String> restrictedBlocks = new ArrayList<String>();



    public void onEnable() {
        console = getServer().getConsoleSender();



        this.redstoneLimit = Main.getInstance().getConfig().getInt("redstone_limit");
        this.restrict = Main.getInstance().getConfig().getBoolean("restrict");
        this.restrictedBlocks = Main.getInstance().getConfig().getStringList("restricted");

        Main.getInstance().saveDefaultConfig();
        getServer().getPluginCommand("redstonelimit").setExecutor(new CommandManager(this));


        console.sendMessage(Utils.colour("&3Enabled &cRedstoneLimit&3!"));

    }

    public void onDisable() {
        console.sendMessage(Utils.colour("&cDisabled &3RedstoneLimit&c!"));
    }

    public int getRedstoneLimit() {
        return this.redstoneLimit;
    }

    public void setRedstoneLimit(int limit) {
        this.redstoneLimit = limit;
        Main.getInstance().getConfig().set("redstone_limit", redstoneLimit);
        Main.getInstance().saveConfig();
    }


    public Boolean getPlayerBypass(UUID player) {
        if (this.playersBypassing.containsKey(player)) return this.playersBypassing.get(player);
        return false;
    }

    public void setPlayerBypass(UUID player, boolean bypass) {
        this.playersBypassing.put(player, bypass);
    }

    public boolean restrict() {
        return this.restrict;
    }

    public List<String> getRestrictedBlocks() {
        return this.restrictedBlocks;
    }

}