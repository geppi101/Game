
package com.geppi.other.redstone.commands;

import com.geppi.other.redstone.RedstoneLimit;
import com.geppi.other.redstone.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CommandManager implements CommandExecutor {

    private final RedstoneLimit plugin;

    public CommandManager(RedstoneLimit plugin) {
        this.plugin = plugin;
    }

    public void executeCommand(CommandSender sender, Command command, String[] args, String dirPath) {

        try {
            args[0] = args[0].toLowerCase();
            String commandName = args[0].substring(0, 1).toUpperCase() + args[0].substring(1) + "Command";
            RedstoneLimitCommand commandClass = (RedstoneLimitCommand) Class.forName(dirPath + "." + commandName).newInstance();

            if (sender instanceof ConsoleCommandSender && !commandClass.allowsConsole()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis command can only be executed by players!"));
                return;
            }

            commandClass.execute(sender, args, plugin);

        } catch (ClassNotFoundException e) {
            sender.sendMessage(Utils.colour("&3&lRedstoneLimit Commands"));
            sender.sendMessage(Utils.colour("&c/rl set <limit>"));
            sender.sendMessage(Utils.colour("&c/rl get"));
            sender.sendMessage(Utils.colour("&c/rl help"));
            sender.sendMessage(Utils.colour("&c/rl bypass [on/off]"));
        } catch (InstantiationException e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Critical Error: &cCould not initiate command class, please report this as this is a bug!"));
        } catch (IllegalAccessException e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Critical Error: &cCould not access command class, please report this as this is a bug!"));
        }

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp() || !sender.hasPermission("redstonelimit.admin")) {
            sender.sendMessage(Utils.colour("&cYou do not have permission to execute this command!"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.colour("&3&lRedstoneLimit Commands"));
            sender.sendMessage(Utils.colour("&c/rl set <limit>"));
            sender.sendMessage(Utils.colour("&c/rl get"));
            sender.sendMessage(Utils.colour("&c/rl help"));
            sender.sendMessage(Utils.colour("&c/rl bypass [on/off]"));
            return true;
        }

        executeCommand(sender, command, args, "com.geppi.other.redstone.commands");
        return true;
    }
}