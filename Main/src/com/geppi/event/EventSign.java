package com.geppi.event;


import com.geppi.other.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;

public class EventSign implements Listener {

    WarpHandler warpHandler = new WarpHandler();
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    TimeFormatHandler timeFormatHandler = new TimeFormatHandler();
    MineHandler mineHandler = new MineHandler();

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();

        if (playerHandler.getRank(player) == 10) {


            if (event.getLine(0).equalsIgnoreCase("[Sell]")) {
                event.setLine(0, "§1[Sell]");
            }

            Material m = Material.matchMaterial(event.getLine(2));

            if (m == null) {
                if (event.getLine(0).equalsIgnoreCase("§1[Sell]")) {
                    player.sendMessage("§5§l* §7Can't find item, " + event.getLine(2));
                    return;
                }
            }

            if (event.getLine(0).equalsIgnoreCase("[Buy]")) {
                event.setLine(0, "§1[Buy]");
            }


            if (m == null) {
                if (event.getLine(0).equalsIgnoreCase("§1[Buy]")) {
                    player.sendMessage("§5§l* §7Can't find item, " + event.getLine(2));
                    return;
                }
            }

            if (event.getLine(0).equalsIgnoreCase("[Warp]")) {
                event.setLine(0, "§1[Warp]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Heal]")) {
                event.setLine(0, "§1[Heal]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Prison Parkour]")) {
                event.setLine(0, "§1[Prison Parkour]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Parkour]")) {
                event.setLine(0, "§1[Parkour]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Smite]")) {
                event.setLine(0, "§1[Smite]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Kit]")) {
                event.setLine(0, "§1[Kit]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Jail Time]")) {
                event.setLine(0, "§1[Jail Time]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Reset Mine]")) {
                event.setLine(0, "§1[Reset Mine]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Un-Jail]")) {
                event.setLine(0, "§1[Un-Jail]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Trash]")) {
                event.setLine(0, "§1[Trash]");
            }
            if (event.getLine(0).equalsIgnoreCase("[Easter Egg]")) {
                event.setLine(0, "§1[Easter Egg]");
            }

            return;
        }




    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§1[Sell]")) {

                int ammount = Integer.parseInt((s.getLine(1).replaceAll("[^\\d.]", "")));
                int cost = Integer.parseInt((s.getLine(3).replaceAll("[^\\d.]", "")));

                String item = s.getLine(2).toLowerCase().toString();
                int count = 0;

                for (ItemStack stack : player.getInventory().getContents()) {
                    if (stack != null && stack.getType() == Material.matchMaterial(item)) {
                        count += stack.getAmount();
                    }
                }

                if(!(count >= ammount)) {
                    player.sendMessage(colorHandler.main + "Store: " + colorHandler.message + "You do not have enough items to sell!");
                    return;
                }
                if(player.getInventory().getItemInOffHand().getType().equals(Material.matchMaterial(s.getLine(2).toString()))) {
                    player.sendMessage("You can not have selling item in offhand!");
                    return;
                }

                player.getInventory().removeItem(new ItemStack(Material.matchMaterial(s.getLine(2).toString()), ammount));
                player.updateInventory();

                playerHandler.addMoney(player, cost);
            }

            if (s.getLine(0).equalsIgnoreCase("§1[Buy]")) {

                int ammount = Integer.parseInt((s.getLine(1).replaceAll("[^\\d.]", "")));
                int cost = Integer.parseInt((s.getLine(3).replaceAll("[^\\d.]", "")));

                String item = s.getLine(2).toLowerCase().toString();
                int count = 0;

                if(playerHandler.getMoney(player) < cost) {
                    player.sendMessage(colorHandler.main + "Store: " + colorHandler.message + "You do not have money to buy!");
                    return;
                }

                /*if(!(count <= ammount)) {
                    player.sendMessage(colorHandler.main + "Store: " + colorHandler.message + "You do not have money to buy!");
                    return;
                } */

                player.getInventory().addItem(new ItemStack(Material.matchMaterial(s.getLine(2).toString()), ammount));
                player.updateInventory();

                playerHandler.removeMoney(player, cost);
            }

            if (s.getLine(0).equalsIgnoreCase("§1[Warp]")) {

                if(s.getLine(2) == "") {
                    return;
                }

                if(playerHandler.getJailTime(player) > 0) {
                    player.sendMessage(colorHandler.error + "You can't warp in jail!");
                    return;
                }

                warpHandler.warp(s.getLine(2).toUpperCase().toString(), player);

            }

            if (s.getLine(0).equalsIgnoreCase("§1[Parkour]")) {

                if(s.getLine(2) == "") {
                    return;
                }

                long elapsedTime = System.currentTimeMillis() - playerHandler.getParkourDate(player, s.getLine(2));
                long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
                if(timeUntilReuse > 0) {
                    player.sendMessage(colorHandler.coolDown + timeFormatHandler.formatTime(timeUntilReuse));
                    return;
                }

                playerHandler.setParkourDate(player, s.getLine(2));
                player.sendMessage(colorHandler.main + "Parkour: " + colorHandler.message+ "You have completed parkour: " + s.getLine(2));
                playerHandler.addToken(player, Integer.valueOf(s.getLine(2) + 0));
            }

            if (s.getLine(0).equalsIgnoreCase("§1[Reset Mine]")) {

                if(s.getLine(2) == "") {
                    return;
                }

                long elapsedTime = System.currentTimeMillis() - mineHandler.getMineResetTime(s.getLine(2));
                long timeUntilReuse = ((60*60*1000) - elapsedTime);
                if(timeUntilReuse > 0) {
                    player.sendMessage(colorHandler.coolDown + timeFormatHandler.formatTime(timeUntilReuse));
                    return;
                }
                if(s.getLine(2).contains("1")) {
                    mineHandler.setMineResetTime(s.getLine(2));
                    Bukkit.getServer().broadcastMessage(colorHandler.main + "Mine: " + colorHandler.message + "Charlie mine has been reset!");

                    if(player.isOp()) {
                        player.chat("/rg sel -w world cmine");
                        player.chat("//set 97.5%stone,0.5%iron_ore,0.5%coal_ore,0.5%glowstone");
                        return;
                    }

                    player.setOp(true);
                    player.chat("/rg sel -w world cmine");
                    player.chat("//set 97.5%stone,0.5%iron_ore,0.5%coal_ore,0.5%glowstone");
                    player.setOp(false);
                }
                if(s.getLine(2).contains("2")) {
                    mineHandler.setMineResetTime(s.getLine(2));
                    Bukkit.getServer().broadcastMessage(colorHandler.main + "Mine: " + colorHandler.message + "Bravo mine has been reset!");

                  if(player.isOp()) {
                      player.chat("/rg sel -w world bmine");
                      player.chat("//set 97.5%sandstone,0.5%iron_ore,0.5%gold_ore,0.5%glowstone");
                      return;
                  }

                    player.setOp(true);
                    player.chat("/rg sel -w world bmine");
                    player.chat("//set 97.5%sandstone,0.5%iron_ore,0.5%gold_ore,0.5%glowstone");
                    player.setOp(false);
                } //TODO MINES
                if(s.getLine(2).contains("3")) {
                    mineHandler.setMineResetTime(s.getLine(2));
                    Bukkit.getServer().broadcastMessage(colorHandler.main + "Mine: " + colorHandler.message + "Alpha mine has been reset!");

                    if(player.isOp()) {
                        player.chat("/rg sel -w world amine");
                        player.chat("//set 97.5%netherrack,0.5%lapis_ore,0.5%diamond_ore,0.5%glowstone");
                        return;
                    }

                    player.setOp(true);
                    player.chat("/rg sel -w world amine");
                    player.chat("//set 97.5%netherrack,0.5%lapis_ore,0.5%diamond_ore,0.5%glowstone");
                    player.setOp(false);
                }
                if(s.getLine(2).contains("4")) {
                    mineHandler.setMineResetTime(s.getLine(2));
                    Bukkit.getServer().broadcastMessage(colorHandler.main + "Mine: " + colorHandler.message + "Elite mine has been reset!");

                    if(player.isOp()) {

                        player.chat("/rg sel -w world emine");
                        player.chat("//set 98.5%snow_block,0.5%emerald_ore,0.5%diamond_ore,0.5%glowstone");
                        return;

                    }

                    player.setOp(true);
                    player.chat("/rg sel -w world emine");
                    player.chat("//set 98.5%snow_block,0.5%emerald_ore,0.5%diamond_ore,0.5%glowstone");
                    player.setOp(false);
                }

            }

            if (s.getLine(0).equalsIgnoreCase("§1[Easter Egg]")) {

                if(s.getLine(2) == "") {
                    return;
                }

                long elapsedTime = System.currentTimeMillis() - playerHandler.getEastereggDate(player, s.getLine(2));
                long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
                if(timeUntilReuse > 0) {
                    player.sendMessage(colorHandler.coolDown + timeFormatHandler.formatTime(timeUntilReuse));
                    return;
                }

                playerHandler.setEastereggDate(player, s.getLine(2));
                playerHandler.setEasterEggReward(player, playerHandler.getEasterEggReward(player) + 1);
                player.sendMessage(colorHandler.main + "EasterEgg: " + colorHandler.message+ "You have completed Easter Egg: " + s.getLine(2));
                playerHandler.addToken(player, 25);
            }

            if (s.getLine(0).equalsIgnoreCase("§1[Prison Parkour]")) {

                if(s.getLine(2) == "") {
                    return;
                }

                long elapsedTime = System.currentTimeMillis() - playerHandler.getPrisonParkourDate(player, s.getLine(2));
                long timeUntilReuse = ((24*60*60*1000) - elapsedTime);
                if(timeUntilReuse > 0) {
                    player.sendMessage(colorHandler.coolDown + timeFormatHandler.formatTime(timeUntilReuse));
                    return;
                }

                playerHandler.setPrisonParkourDate(player, s.getLine(2));
                player.sendMessage(colorHandler.main + "Parkour: " + colorHandler.message+ "You have completed parkour: " + s.getLine(2));
                playerHandler.addToken(player, Integer.valueOf(s.getLine(2) + 0));
            }

            if (s.getLine(0).equalsIgnoreCase("§1[Jail Time]")) {

                if(playerHandler.getJailTime(player) == 0) {
                    player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "You are not in jail!");
                    return;
                }

                player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "You will be released in " + playerHandler.getJailTime(player) + " seconds!");

            }

            if (s.getLine(0).equalsIgnoreCase("§1[Smite]")) {

                if(s.getLine(2) == "") {
                    return;
                }

                Player target = getServer().getPlayer(s.getLine(2));
                if(target == null) {
                    player.sendMessage(colorHandler.offlinePlayer);
                    return;
                }

                player.sendMessage("You have smitten " + s.getLine(2));
                target.sendMessage("You have been smitten by " + s.getLine(2) );
            }

            if (s.getLine(0).equalsIgnoreCase("§1[Heal]")) {

                player.setHealth(20);
                player.sendMessage(colorHandler.main + "Heal: " + colorHandler.message+ "You have been healed");
                player.setFoodLevel(25);
            }

            if (s.getLine(0).equalsIgnoreCase("§1[Un-Jail]")) {


                playerHandler.resetJailTime(player);
                Location spawn = new Location(Bukkit.getWorld("world"), -0.5,125,-0.5);
                player.teleport(spawn);
                playerHandler.setInJail(player, 0);
                player.sendMessage(colorHandler.main + "Jail: " + colorHandler.message + "You have escaped yourself out of jail!");

            }

            if (s.getLine(0).equalsIgnoreCase("§1[Kit]")) {

               player.chat("/kit " + s.getLine(2));
            }
        }


    }
}

