package com.geppi.other;

import com.geppi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaultHandler implements Listener {

   ColorHandler colorHandler = new ColorHandler();
    public static Map<String, ItemStack[]> menus = new HashMap<String, ItemStack[]>();

    public void onEnable() {
        //this.getServer().getPluginManager().registerEvents(this, this);
        Main.getInstance().saveDefaultConfig(); // <-- create config.yml

        if (Main.getInstance().getConfig().contains("data")) {
            this.restoreInvs();
            Main.getInstance().getConfig().set("data", null);
            Main.getInstance().saveConfig();
        }


    }

    public void onDisable() {
        if (!menus.isEmpty()) {
            this.saveInvs();
        }
    }

    public void saveInvs() {
        for (Map.Entry<String, ItemStack[]> entry : menus.entrySet()) {
            Main.getInstance().getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        Main.getInstance().saveConfig();

    }

    public void restoreInvs() {
        Main.getInstance().getConfig().getConfigurationSection("data").getKeys(false).forEach(key -> {
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>) Main.getInstance().getConfig().get("data." + key)).toArray(new ItemStack[0]);
            menus.put(key, content);
        });
    }

    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if (args[0].equalsIgnoreCase("/pv")) {
            //0 = off || 1 = on
            event.setCancelled(true);
            Inventory inv = Bukkit.createInventory(player, 54, colorHandler.message + player.getName() + "'s Private Vault");

            if (menus.containsKey(player.getUniqueId().toString()))
                inv.setContents(menus.get(player.getUniqueId().toString()));

            player.openInventory(inv);
            return;

        }
    }
    //System.getProperty("user.dir")

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains(event.getPlayer().getName() + "'s Private Vault"))
            menus.put(event.getPlayer().getUniqueId().toString(), event.getInventory().getContents());
    }

    @EventHandler
    public void onInteractBlock(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (action.equals(Action.RIGHT_CLICK_BLOCK) && block.getType().equals(Material.ENDER_CHEST)) {
            {
                event.setCancelled(true);
                Inventory inv = Bukkit.createInventory(player, 54, colorHandler.message + player.getName() + "'s Private Vault");

                if (menus.containsKey(player.getUniqueId().toString()))
                    inv.setContents(menus.get(player.getUniqueId().toString()));

                player.openInventory(inv);
                return;

            }
        }

    }
}