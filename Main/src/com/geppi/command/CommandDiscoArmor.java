package com.geppi.command;

import com.geppi.other.ColorHandler;
import com.geppi.other.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandDiscoArmor implements Listener {


    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();


    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/discoarmor")) {
            //0 = off || 1 = on
            event.setCancelled(true);

            if(playerHandler.getDonateDiscoArmor(player) == 0) {
                player.sendMessage(colorHandler.noPermission);
                return;
            }

            if(player.getGameMode() == GameMode.CREATIVE) {
                player.sendMessage(colorHandler.error + "You can not run this command in creative mode!");
                return;
            }

            if(playerHandler.getDonateDiscoArmorStatus(player) == 1) {
                player.getInventory().setHelmet(new ItemStack(Material.AIR));
                player.getInventory().setChestplate(new ItemStack(Material.AIR));
                player.getInventory().setLeggings(new ItemStack(Material.AIR));
                player.getInventory().setBoots(new ItemStack(Material.AIR));
                player.sendMessage(colorHandler.donation + "Disabled Disco Armor!");
                playerHandler.setDonateDiscoArmorStatus(player, 0);
                return;
            }

            if(event.getPlayer().getInventory().getHelmet() != null
            || event.getPlayer().getInventory().getChestplate() != null
            || event.getPlayer().getInventory().getLeggings() != null
            || event.getPlayer().getInventory().getBoots() != null) {
                player.sendMessage(colorHandler.donation + "You need to take off all your armor!");
                return;
            }



            ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
            ItemMeta helmMeta = helm.getItemMeta();
            helmMeta.setDisplayName(colorHandler.message + "Disco");
            helm.setItemMeta(helmMeta);

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemMeta chestplateMeta = chestplate.getItemMeta();
            chestplateMeta.setDisplayName(colorHandler.message + "Disco");
            chestplate.setItemMeta(chestplateMeta);

            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemMeta leggingsMeta = leggings.getItemMeta();
            leggingsMeta.setDisplayName(colorHandler.message + "Disco");
            leggings.setItemMeta(leggingsMeta);

            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
            ItemMeta bootsMeta = boots.getItemMeta();
            bootsMeta.setDisplayName(colorHandler.message + "Disco");
            boots.setItemMeta(bootsMeta);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);

            playerHandler.setDonateDiscoArmorStatus(player, 1);
            player.sendMessage(colorHandler.donation + "Enabled Disco Armor!");

        }
    }

}

