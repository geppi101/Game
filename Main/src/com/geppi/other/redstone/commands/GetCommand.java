package com.geppi.other.redstone.commands;

import com.geppi.other.redstone.RedstoneLimit;
import com.geppi.other.redstone.Utils;
import org.bukkit.command.CommandSender;

public class GetCommand extends RedstoneLimitCommand {

    public GetCommand() {
        super(true);
    }

    public void execute(CommandSender sender, String[] args, RedstoneLimit plugin) {
        if (args.length != 1) {
            sender.sendMessage(Utils.colour("&3Usage: &c/rl get"));
            return;
        }

        sender.sendMessage(Utils.colour("&3The redstone limit is &c" + plugin.getRedstoneLimit() + " &3blocks!"));
    }
}