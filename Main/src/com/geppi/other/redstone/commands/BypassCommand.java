package com.geppi.other.redstone.commands;

import com.geppi.other.redstone.RedstoneLimit;
import com.geppi.other.redstone.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BypassCommand extends RedstoneLimitCommand {

    public BypassCommand() {
        super(false);
    }

    public void execute(CommandSender sender, String[] args, RedstoneLimit plugin) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(Utils.colour("&cThis command can only be executed by players!"));
            return;
        }

        Player player = (Player) sender;

        if (args.length == 0 || args.length > 2) {
            sender.sendMessage(Utils.colour("&3Usage: &c/rl bypass [on/off]"));
            return;
        }

        if (args.length == 1) {
            if (!plugin.getPlayerBypass(player.getUniqueId())) {
                plugin.setPlayerBypass(player.getUniqueId(), true);
                sender.sendMessage(Utils.colour("&3You are now bypassing redstone restrictions!"));
                return;
            }

            plugin.setPlayerBypass(player.getUniqueId(), false);
            sender.sendMessage(Utils.colour("&3You are no longer bypassing redstone restrictions!"));
            return;
        }

        if (args[1].equalsIgnoreCase("on")) {
            plugin.setPlayerBypass(player.getUniqueId(), true);
            sender.sendMessage(Utils.colour("&3You are now bypassing redstone restrictions!"));
            return;
        }

        if (args[1].equalsIgnoreCase("off")) {
            plugin.setPlayerBypass(player.getUniqueId(), false);
            sender.sendMessage(Utils.colour("&3You are no longer bypassing redstone restrictions!"));
            return;
        }

        sender.sendMessage(Utils.colour("&3Usage: &c/rl bypass [on/off]"));
    }
}