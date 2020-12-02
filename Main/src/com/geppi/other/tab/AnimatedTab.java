package com.geppi.other.tab;

import org.bukkit.Bukkit;

import javax.swing.*;

public class AnimatedTab {

    public TabManager tab;


    public void onEnable() {
        this.tab = new TabManager(this);

        tab.addHeader("&4&lBehind Bars\n-------------------------\n&7Server is currently in Beta!\n");
        tab.addHeader("&4&lBehind Bars\n-------------------------\n&7Please do not share the IP!\n");
        tab.addHeader("&4&lBehind Bars\n-------------------------\n&7Enjoy!\n");

        tab.addFooter("\n&4&lPlayers Online: &7" + Bukkit.getOnlinePlayers().size());


        tab.showTab();
    }



}
