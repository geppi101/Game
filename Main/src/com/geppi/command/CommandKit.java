package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import com.geppi.other.TimeFormatHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class CommandKit implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    TimeFormatHandler timeFormatHandler = new TimeFormatHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/kit")) {

            if (!(args.length == 2)) {
                player.sendMessage(colorHandler.usage + "/kit <name>");
                return;
            }
            //0 = off || 1 = on

            if(args[1].equalsIgnoreCase("tools")) {
                event.setCancelled(true);

                long elapsedTime = System.currentTimeMillis() - playerHandler.getKitToolsDate(player);
                long timeUntilReuse = ((24 * 60 * 60 * 1000) - elapsedTime);
                if (timeUntilReuse > 0) {
                    player.sendMessage(colorHandler.coolDown + timeFormatHandler.formatTime(timeUntilReuse));
                    return;
                }

                player.sendMessage(colorHandler.main + "Kit: " + colorHandler.message + "Received kit Tools!");
                playerHandler.setKitToolsDate(player);
                player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                player.getInventory().addItem(new ItemStack(Material.IRON_AXE));

            }
            if(args[1].equalsIgnoreCase("guard")) {

                if(playerHandler.getRank(player) < 6) {
                    player.sendMessage(colorHandler.noPermission);
                    return;
                }

                event.setCancelled(true);

                player.sendMessage(colorHandler.main + "Kit: " + colorHandler.message + "Received kit Guard!");

                ItemStack guardHelm = new ItemStack(Material.CHAINMAIL_HELMET);
                ItemMeta guardHelmMeta = guardHelm.getItemMeta();
                guardHelmMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Helmet");
                guardHelm.setItemMeta(guardHelmMeta);
                guardHelm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                guardHelm.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
                guardHelm.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                guardHelm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                player.getInventory().addItem(guardHelm);

                ItemStack guardChestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                ItemMeta guardChestplateMeta = guardChestplate.getItemMeta();
                guardChestplateMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Chestplate");
                guardChestplate.setItemMeta(guardChestplateMeta);
                guardChestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                guardChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
                guardChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                guardChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                player.getInventory().addItem(guardChestplate);

                ItemStack guardLeggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                ItemMeta guardLegginsMeta = guardLeggings.getItemMeta();
                guardLegginsMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Leggings");
                guardLeggings.setItemMeta(guardLegginsMeta);
                guardLeggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                guardLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
                guardLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                guardLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                player.getInventory().addItem(guardLeggings);

                ItemStack guardBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
                ItemMeta guardBootsMeta = guardBoots.getItemMeta();
                guardBootsMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Boots");
                guardBoots.setItemMeta(guardBootsMeta);
                guardBoots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                guardBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
                guardBoots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                guardBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                player.getInventory().addItem(guardBoots);

                ItemStack guardSword = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta guardSwordMeta = guardSword.getItemMeta();
                guardSwordMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Sword");
                guardSword.setItemMeta(guardSwordMeta);
                guardSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                guardSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
                guardSword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                guardSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
                player.getInventory().addItem(guardSword);

                ItemStack bow = new ItemStack(Material.BOW);
                ItemMeta bowMeta = bow.getItemMeta();
                bowMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Bow");
                bow.setItemMeta(bowMeta);
                bow.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
                bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
                player.getInventory().addItem(bow);

                ItemStack arrow = new ItemStack(Material.ARROW);
                ItemMeta arrowMeta = arrow.getItemMeta();
                arrowMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Arrow");
                arrow.setItemMeta(arrowMeta);
                arrow.setAmount(128);
                player.getInventory().addItem(arrow);

                ItemStack cookedbeef = new ItemStack(Material.COOKED_CHICKEN);
                ItemMeta cookedBeefMeta = cookedbeef.getItemMeta();
                cookedBeefMeta.setDisplayName(ChatColor.of("#5E95F0") + "Guard Food");
                cookedbeef.setItemMeta(cookedBeefMeta);
                cookedbeef.setAmount(64);
                player.getInventory().addItem(cookedbeef);



            }




        }

        if(args[0].equalsIgnoreCase("/kits")) {
            event.setCancelled(true);

            if(playerHandler.getRank(player) < 6) {
            player.sendMessage(colorHandler.main + "Kit: " + colorHandler.message + "Tools");
            return;
            }
            player.sendMessage(colorHandler.main + "Kit: " + colorHandler.message + "Tools and Guard");
            return;
        }
    }





}

