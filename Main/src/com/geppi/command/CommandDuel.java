package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;

import net.minecraft.server.v1_16_R2.PlayerInventory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class CommandDuel implements Listener {

    //Arrays
    public static List<String> l = new ArrayList<String>();
    public static List<String> pl = new ArrayList<String>();

    //Integers
    int maps = 6;
    int openm = 6;

    //Hashmaps
    public Map<UUID, PlayerInventory> pinv = new HashMap<UUID, PlayerInventory>();
    public Map<UUID, UUID> pr = new HashMap<UUID, UUID>();
    public Map<UUID, Integer> pi = new HashMap<UUID, Integer>();
    public Map<UUID, Long> pt = new HashMap<UUID, Long>();
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();

    //Just a shortcut
    public void rfh(Player p) {
        UUID id = p.getUniqueId();
        pr.remove(id);
        pi.remove(id);
        pt.remove(id);
    }

    //Just another shortcut
    @SuppressWarnings("boxing")
    public void afh(Player p, Player t, int w) {
        UUID tid = t.getUniqueId();
        pr.put(tid, p.getUniqueId());
        pi.put(tid, w);
        pt.put(tid, (System.currentTimeMillis() + 60*1000));
    }

    //Starts duel for these...
    public void startDuel(Player p1, Player p2, String m, int w) {
        Location s1, s2;
        pinv.put(p1.getUniqueId(), (PlayerInventory) p1.getInventory()); pinv.put(p2.getUniqueId(), (PlayerInventory) p2.getInventory());
        s1 = null; s2 = null; //Make a way to get location, via config
        /*
         * SET ITEMS
         */
        pl.add(p1.getName()); pl.add(p2.getName());
        p1.teleport(s1); p2.teleport(s2);
    }

    @SuppressWarnings("boxing")
    @EventHandler
    public void duelCommand(PlayerCommandPreprocessEvent event) {

        //Event Message
        String message = event.getMessage();

        //Splits
        String[] args = message.split(" ");

        //Player
        Player player = event.getPlayer();

        //If command is /duel
        if (args[0].equalsIgnoreCase("/duel")) {

            //If arguments value is equal to 2
            if(args.length == 2) {

                //If the first argument is either decline or accept
                if(args[1].equalsIgnoreCase("accept") || args[1].equalsIgnoreCase("decline")) {

                    //If the hashmap contains the player
                    if (pt.containsKey(player.getUniqueId())) {

                        //If the time in the hashmap is higher than 0
                        if (pt.get(player.getUniqueId()) <= (System.currentTimeMillis() + 0)) {

                            //Sends message
                            player.sendMessage("No one sent you a request!");
                            return;
                        }
                    }
                }

                //Sets requester
                Player requester = (Player) Bukkit.getPlayer(pr.get(player.getUniqueId()));

                //If the first argument is accept
                if(args[1].equalsIgnoreCase("accept")) {

                    if (!requester.isOnline()) {

                        //Sends a message, Removes the player from the hashmap : time and requests
                        player.sendMessage("Player who requested is not online.");
                        pr.remove(player.getUniqueId());
                        return;
                    }

                    //Checks if the requester has the money for the duel
                    if (playerHandler.getMoney(requester) >= pi.get(player.getUniqueId())) {

                        //Sends a message, Removes the player from the hashmap : times and requests
                        player.sendMessage("The requester is poor");
                        pr.remove(player.getUniqueId());
                        return;
                    }

                    //Checks if the command sender has the money for the duel
                    if (playerHandler.getMoney(player) >= pi.get(player.getUniqueId())) {
                        player.sendMessage("u is poor");
                        return;
                    }

                    //Checks if there are no maps available
                    if (openm < 1) {

                        //Sends message, Removes the player from the hashmaps
                        player.sendMessage("There are no maps available, try again later!");
                        requester.sendMessage(player.getName() + " accepted the duel!" + "\nThere are no maps available, try again later!");
                        rfh(player);
                    }
                    rfh(player);
                    startDuel(player, requester, l.get(1), pi.get(player.getUniqueId()));

                }

                //If the first argument is deny
                if(args[1].equalsIgnoreCase("deny")) {
                    player.sendMessage("You denied the duel.");
                    requester.sendMessage(player.getName() + " denied the duel!");
                    rfh(player);
                    return;
                }
                return;
            }

            //If not equal to 3
            if(args.length != 3) {

                //Sends message
                player.sendMessage(colorHandler.usage + args[0] + " <player> <wager>");
                return;
            }

            //Sets the target
            Player target = getServer().getPlayer(args[1]);

            //If target is null
            if(target == null) {

                //Sends message
                player.sendMessage(colorHandler.offlinePlayer);
                return;
            }

            //Sets a integer
            int num;

            //Parses second argument into an integer
            try {
                num = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex){
                player.sendMessage(colorHandler.nonNumber);
                return;
            }

            //If wager is lower than 50
            if(num < 50) {

                //Sends message
                player.sendMessage("You must wager atleast $50");
                return;
            }

            //If command sender doesn't have enough money
            if(playerHandler.getMoney(player) < num) {

                //Sends an HORRIBLE message
                player.sendMessage("You're too broke :(");
                return;
            }

            //If the target doesn't have enough money
            if(playerHandler.getMoney(target) < num) {

                //Sends an EVIL message
                player.sendMessage("The person you want to duel is too broke :(");
                return;
            }

            //Checks if there are any maps available
            if (openm > 0) {

                //Sends message, sends requests

                player.sendMessage("Send a request to " + target.getName());
                target.sendMessage("Hey u were requested to kys");
                afh(player, target, num);
                return;
            }

            //If not maps available do this
            else {

                //Sends message
                player.sendMessage("No crappy maps available");
                return;
            }
        }
    }
}