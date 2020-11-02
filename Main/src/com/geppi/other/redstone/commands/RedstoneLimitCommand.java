package com.geppi.other.redstone.commands;

import com.geppi.other.redstone.RedstoneLimit;
import org.bukkit.command.CommandSender;

public abstract class RedstoneLimitCommand {

    private final boolean allowConsole;

    public RedstoneLimitCommand(boolean allowConsole) {
        this.allowConsole = allowConsole;
    }
    
    public boolean allowsConsole() {
        return allowConsole;
    }

    public abstract void execute(CommandSender sender, String[] args, RedstoneLimit plugin);

}