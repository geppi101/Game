package com.geppi.other.redstone;

import org.bukkit.ChatColor;

public class Utils {

    public static String colour(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}