package com.geppi.other.redstone.commands;

import com.geppi.other.redstone.RedstoneLimit;
import com.geppi.other.redstone.Utils;
import org.bukkit.command.CommandSender;

public class SetCommand extends RedstoneLimitCommand {

    public SetCommand() {
        super(true);
    }

    public void execute(CommandSender sender, String[] args, RedstoneLimit plugin) {
        if (args.length != 2) {
            sender.sendMessage(Utils.colour("&3Usage: &c/rl set <limit> &3(in blocks)"));
            return;
        }

        if (!Utils.isInteger(args[1])) {
            sender.sendMessage(Utils.colour("&3Usage: &c/rl set <limit> &3(in blocks)"));
            return;
        }

        plugin.setRedstoneLimit(Integer.parseInt(args[1]));
        sender.sendMessage(Utils.colour("&3Successfully set the redstone limit to &c" + args[1] + " &3blocks!"));
    }
}