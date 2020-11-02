package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.Inventories;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static org.bukkit.Bukkit.getServer;

public class CommandDonate implements Listener {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    Inventories inventories = new Inventories();

    @EventHandler
    public void moneyCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if(args[0].equalsIgnoreCase("/donations") || args[0].equalsIgnoreCase("/donate")) {
            event.setCancelled(true);
            String nc = ChatColor.YELLOW.toString();
            player.sendMessage(colorHandler.donation + "LINK");
            player.sendMessage(colorHandler.main + "1-Hat " + colorHandler.message + "Put any item as a hat!");
            player.sendMessage(colorHandler.main + "2-Pvp " + colorHandler.message + "Disable/Enable pvp!");
            player.sendMessage(colorHandler.main + "3-Nickname " + colorHandler.message + "Set a custom nickname for display!!");
            player.sendMessage(colorHandler.main + "4-Feed " + colorHandler.message + "Never run out of hunger again!!");
            player.sendMessage(colorHandler.main + "5-Bail " + colorHandler.message + "Bail out of jail (costs 5000)!!");
            player.sendMessage(colorHandler.main + "6-Namecolor " + colorHandler.message + "Change your namecolor!!");
            player.sendMessage(colorHandler.main + "7-Spawn " + colorHandler.message + "Teleport to Spawn!!");
            player.sendMessage(colorHandler.main + "8-Stats " + colorHandler.message + "View stats, totals, redeem rewards, and more!!");
            player.sendMessage(colorHandler.main + "9-Trails " + colorHandler.message + "Have a particle trail!!");
            player.sendMessage(colorHandler.main + "10-Disco Armor " + colorHandler.message + "Have disco armor!!");
            //TODO
            player.sendMessage(colorHandler.main + "11-Pet " + colorHandler.message + "Have a baby pet follow you around!!");
            player.sendMessage(colorHandler.main + "12-Trash " + colorHandler.message + "Opens a trash GUI!!");
            player.sendMessage(colorHandler.main + "13-Craft " + colorHandler.message + "Craft anywhere you want!!");
            player.sendMessage(colorHandler.main + "13-Player Vault " + colorHandler.message + "Access your player vault whenever!!!");


            return;
        }
        if(args[0].equalsIgnoreCase("/buy")) {
            inventories.donationShopInv(player);
            return;
        }
    }

    @EventHandler
    public void moneyAdminCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");
        String nc = ChatColor.YELLOW.toString();

        Player player = event.getPlayer();
        if (args[0].equalsIgnoreCase("/donationadmin")||args[0].equalsIgnoreCase("/donateadmin")) {
            event.setCancelled(true);

            if(playerHandler.getRank(player) < 10) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(args.length != 4) {
                player.sendMessage(colorHandler.usage + args[0] + " add/remove <player> <donation>");
                return;
            }
            Player target = getServer().getPlayer(args[2]);
            if(target == null) {
                player.sendMessage(colorHandler.offlinePlayer + "This player is offline!");
                return;
            }

            int num;

            try {
                num = Integer.parseInt(args[3]);
            } catch (NumberFormatException ex){
                player.sendMessage(colorHandler.nonNumber);
                return;
            }

            if(num < 1 || num > 10) {
                player.sendMessage(colorHandler.error + "Must be numbers 1-10, check /donate to see the relating number");
                return;
            }

            if(args[1].equalsIgnoreCase("add")) {
                player.sendMessage(colorHandler.donation + colorHandler.message + "Added donation " + String.valueOf(args[3]) + " to " + target.getName());
                target.sendMessage(colorHandler.donation + colorHandler.message + "Donation added " + String.valueOf(args[3]) + " by " + player.getName());

                if(num == 1) { //Hat
                    playerHandler.setDonateHat(target, 1);

                }

                if(num == 2) { //PVP
                    playerHandler.setDondatePvpStatus(target, 1);

                }

                if(num == 3) { //Nickname
                    playerHandler.setDonateNickname(target, 1);

                }
                if(num == 4) { //Nickname
                    playerHandler.setDonateFeed(target, 1);

                }
                if(num == 5) { //Nickname
                    playerHandler.setDonateBail(target, 1);

                }
                if(num == 6) { //PVP
                    playerHandler.setDonateNameColor(target, 1);

                }

                if(num == 7) { //PVP
                    playerHandler.setDonateSpawn(target, 1);

                }
                if(num == 8) { //PVP
                    playerHandler.setDonateStats(target, 1);

                }
                if(num == 9) { //PVP
                    playerHandler.setDonateTrails(target, 1);

                }
                if(num == 10) { //PVP
                    playerHandler.setDonateDiscoArmor(target, 1);

                }
                return;

            }

            if(args[1].equalsIgnoreCase("remove")) {
                player.sendMessage(colorHandler.donation + colorHandler.message + "Removed donation " + String.valueOf(args[3]) + " to " + target.getName());
                target.sendMessage(colorHandler.donation + colorHandler.message + "Donation removed " + String.valueOf(args[3]) + " by " + player.getName());

                if(num == 1) { //Hat
                    playerHandler.setDonateHat(target, 0);

                }

                if(num == 2) { //PVP
                    playerHandler.setDondatePvpStatus(target, 0);

                }


                if(num == 3) { //Nickname
                    playerHandler.setDonateNickname(target, 0);

                }
                if(num == 4) { //Nickname
                    playerHandler.setDonateFeed(target, 0);

                }
                if(num == 5) { //PVP
                    playerHandler.setDonateBail(target, 0);

                }
                if(num == 6) { //PVP
                    playerHandler.setDonateNameColor(target, 0);

                }
                if(num == 7) { //PVP
                    playerHandler.setDonateSpawn(target, 0);

                }
                if(num == 8) { //PVP
                    playerHandler.setDonateStats(target, 0);

                }
                if(num == 9) { //PVP
                    playerHandler.setDonateTrails(target, 0);

                }
                if(num == 10) { //PVP
                    playerHandler.setDonateDiscoArmor(target, 0);

                }
                return;

            }
        }

    }
}