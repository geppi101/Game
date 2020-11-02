
package com.geppi.other.redstone.commands;

import com.geppi.other.redstone.RedstoneLimit;
import com.geppi.other.redstone.Utils;
import org.bukkit.command.CommandSender;

public class HelpCommand extends RedstoneLimitCommand {

    public HelpCommand() {
        super(true);
    }

    public void execute(CommandSender sender, String[] args, RedstoneLimit plugin) {
        if (args.length != 1) {
            sender.sendMessage(Utils.colour("&3Usage: &c/rl help"));
            return;
        }

        sender.sendMessage(Utils.colour("&3&lRedstoneLimit Commands"));
        sender.sendMessage(Utils.colour("&c/rl set <limit>"));
        sender.sendMessage(Utils.colour("&c/rl get"));
        sender.sendMessage(Utils.colour("&c/rl help"));
        sender.sendMessage(Utils.colour("&c/rl bypass [on/off]"));
    }
}