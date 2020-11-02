package com.geppi.event;

import com.geppi.main.Main;
import com.geppi.other.*;
import com.geppi.other.GeneralInfoHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.potion.PotionEffectType;

public class InventoryClick implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler = new ColorHandler();
    Inventories inventories = new Inventories();
    TimeFormatHandler timeFormatHandler = new TimeFormatHandler();
    GeneralInfoHandler generalInfoHandler = new GeneralInfoHandler();

    public Bar bar;





    @EventHandler
    private void inventoryClick(InventoryClickEvent event) {
          Player player = (Player) event.getWhoClicked();


          if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
              if (event.getCurrentItem().hasItemMeta() == true && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorHandler.message + "Disco")) {
                  if (event.getCurrentItem() != null || (event.getCurrentItem().getType().equals(Material.AIR)) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(colorHandler.message + "Disco")) {
                      event.setCancelled(true);
                      player.updateInventory();
                      player.closeInventory();
                      player.sendMessage(colorHandler.donation + "To remove disco armor use /discoarmor");
                      return;

                  }
              }
          }

          if (event.getView().getTitle().contains("Land Fall")) {
              event.setCancelled(true);
              if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                  return;
              }
              if (event.getCurrentItem().getItemMeta().getDisplayName().contains("START")) {


                  generalInfoHandler.setEventStatus(1);
                  generalInfoHandler.setLandfall(1);

                  player.closeInventory();
                  bar = new Bar(Main.getInstance());
                  bar.createBar();


                  if (Bukkit.getOnlinePlayers().size() > 0)
                      for (Player on : Bukkit.getOnlinePlayers())
                          bar.addPlayer(on);
              }
              if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Portal")) {
                  player.closeInventory();
                  player.chat("/rg sel -w event landfall_fence");
                  player.chat("//set air");
              }
              if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Close Portal")) {
                  player.closeInventory();
                  player.chat("/rg sel -w event landfall_fence");
                  player.chat("//set nether_brick_fence");
              }
              if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Wait")) {
                  player.closeInventory();
                  player.chat("/rg sel -w event landfall_glass");
                  player.chat("//set air");
              }
              if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Close Wait")) {
                  player.closeInventory();
                  player.chat("/rg sel -w event landfall_glass");
                  player.chat("//set glass");
              }
              if (event.getCurrentItem().getType().equals(Material.OAK_LEAVES)) {
                  player.closeInventory();
                  player.chat("/rg sel -w event landfall_log");
                  player.chat("//set oak_log");
                  player.chat("/rg sel -w event landfall_leaf");
                  player.chat("//set oak_leaves");
              }
              if (event.getCurrentItem().getType().equals(Material.GRAVEL)) {
                  player.closeInventory();
                  player.chat("/rg sel -w event landfall_log");
                  player.chat("//set oak_leaves");
                  player.chat("/rg sel -w event landfall_leaf");
                  player.chat("//set gravel");

              }
              if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Set Water")) {
                  player.closeInventory();
                  player.chat("/rg sel -w event landfall_water");
                  player.chat("//set water");
              }
              if (event.getCurrentItem().getItemMeta().getDisplayName().contains("END")) {
                  player.closeInventory();
                  if(generalInfoHandler.getEventStatus()==0) {
                      player.sendMessage(colorHandler.error + "No current event!");
                      return;
                  }


                  generalInfoHandler.setEventStatus(0);
                  generalInfoHandler.setLandfall(0);
                  bar.getBar().removeAll();
              }
          }

          if (event.getView().getTitle().contains("Stats")) {
              event.setCancelled(true);
              if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                  return;
              }
              if (event.getCurrentItem().getType().equals(Material.IRON_SWORD)) {
                  player.closeInventory();
                  inventories.killStreakInv(player);
              }
              if (event.getCurrentItem().getType().equals(Material.PAPER)) {
                  player.closeInventory();
                  inventories.rewardsInv(player);
              }
              if (event.getCurrentItem().getType().equals(Material.BOOK)) {
                  player.closeInventory();
                  inventories.totalsInv(player);
              }
          }
          if (event.getView().getTitle().contains("KillStreak")) {
              event.setCancelled(true);
              if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                  return;
              }
          }

        if (event.getView().getTitle().contains("Vote Rewards")) {
            event.setCancelled(true);
            if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                return;
            }
        }

          if (event.getView().getTitle().contains("Donation Shop")) {
              event.setCancelled(true);
              if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                  return;
              }
          }

        if (event.getView().getTitle().contains("Totals")) {
            event.setCancelled(true);
            if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                return;
            }
        }

        if (event.getView().getTitle().contains("Rules")) {
            event.setCancelled(true);
            if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                return;
            }
            if (event.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                player.closeInventory();

                player.sendMessage(colorHandler.main + "Rules: " + colorHandler.message +"You have accepted the rules of the server");
                if(player.getFlySpeed() == 0f && player.getWalkSpeed() == 0f) {
                    player.setFlySpeed(0.1f);
                    player.setWalkSpeed(0.2f);
                    player.removePotionEffect(PotionEffectType.JUMP);
                    playerHandler.setAcceptRules(player, 1);
                }
            }

            if (event.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                player.closeInventory();

                player.kickPlayer(colorHandler.main + "Rules: " + colorHandler.message +"You have declined the rules of the server");
            }


        }




        if(event.getView().getTitle().contains("Rewards")) {
            event.setCancelled(true);
            if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                return;
            }

            if (event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                player.closeInventory();
                if(playerHandler.getPlayersKilledReward(player) < 500) {
                    player.sendMessage(colorHandler.reward + "Not enough Player Kill points!");
                    return;
                } //FIX THE ONLY COST 5
                playerHandler.setPlayersKilledReward(player, playerHandler.getPlayersKilledReward(player)-5);
                playerHandler.addMoney(player,  1000);
                player.sendMessage(colorHandler.reward +"You has redeemed 500 Player Kill points for $1000");
            }

            if (event.getCurrentItem().getType().equals(Material.ZOMBIE_HEAD)) {
                player.closeInventory();
                if(playerHandler.getEntitiesKilledReward(player) < 500) {
                    player.sendMessage(colorHandler.reward + "Not enough Entity Kill points!");
                    return;
                } //FIX THE ONLY COST 5
                playerHandler.setEntitiesKilledReward(player, playerHandler.getEntitiesKilledReward(player)-5);
                playerHandler.addMoney(player,  1000);
                player.sendMessage(colorHandler.reward +"You has redeemed 500 Player Kill points for $1000");
            }

            if (event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)) {
                player.closeInventory();
                if(playerHandler.getBlocksBroken(player) < 5000) {
                    player.sendMessage(colorHandler.reward + "Not enough Broken Block points!");
                    return;
                } //FIX THE ONLY COST 5
                playerHandler.setBlocksBroken(player, playerHandler.getBlocksBroken(player)-5);
                playerHandler.addMoney(player, 1000);
                player.sendMessage(colorHandler.reward +"You has redeemed 5000 Break Blocks points for $1000");
            }

            if (event.getCurrentItem().getType().equals(Material.BRICK)) {
                player.closeInventory();
                if(playerHandler.getBlocksPlaced(player) < 5000) {
                    player.sendMessage(colorHandler.reward + "Not enough Blocks Placed points!");
                    return;
                } //FIX THE ONLY COST 5
                playerHandler.setBlocksPlaced(player, playerHandler.getBlocksPlaced(player)-5);
                playerHandler.addMoney(player,  1000);
                player.sendMessage(colorHandler.reward +"You has redeemed 5000 Blocks placed points for $1000");
            }

            if (event.getCurrentItem().getType().equals(Material.EGG)) {
                player.closeInventory();
                if(playerHandler.getEasterEggReward(player) < 50) {
                    player.sendMessage(colorHandler.reward + "(50) Not enough Easter Eggs found!");
                    return;
                } //FIX THE ONLY COST 5
                playerHandler.setEasterEggReward(player, playerHandler.getEasterEggReward(player)-5);
                playerHandler.addMoney(player,  1000);
                player.sendMessage(colorHandler.reward +"You has redeemed 50 Easter Egg finds for $1000");
            }

        }

          if(event.getView().getTitle().contains("Token Shop")) {
              event.setCancelled(true);
              if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType().equals(Material.AIR))) {
                  return;
              }





              if (event.getCurrentItem().getType().equals(Material.DIAMOND_HELMET)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 500) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  500);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 800) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  800);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().toString());
                  player.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
              }
              if (event.getCurrentItem().getType().equals(Material.DIAMOND_LEGGINGS)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 700) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  700);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 400) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  400);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }

              if (event.getCurrentItem().getType().equals(Material.ENDER_CHEST)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1500) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1500);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }

              if (event.getCurrentItem().getType().equals(Material.ENCHANTED_BOOK)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1500) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1500);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
                  EnchantmentStorageMeta bookmeta = (EnchantmentStorageMeta) book.getItemMeta();
                  bookmeta.addStoredEnchant(Enchantment.MENDING, 1, true);
                  book.setItemMeta(bookmeta);
                  player.getInventory().addItem(book);
              }

              if (event.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 40) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  40);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 100) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  100);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 50) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  50);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.DIAMOND_SHOVEL)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 25) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  25);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.DIAMOND_HOE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 25) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  25);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.ANVIL)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1500) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1500);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.HOPPER)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 2500) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  2500);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.BEACON)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1500) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1500);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.BOW)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 50) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  50);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.ARROW)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 20) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  20);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  ItemStack arrow = new ItemStack(Material.ARROW);
                  arrow.setAmount(64);
                  player.getInventory().addItem(arrow);
              }
              if (event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1000) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1000);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  ItemStack experience = new ItemStack(Material.EXPERIENCE_BOTTLE);
                  experience.setAmount(64);
                  player.getInventory().addItem(experience);
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_AXE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 80) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  80);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_SWORD)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 200) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  200);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_PICKAXE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 100) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  100);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_SHOVEL)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 50) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  50);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_HOE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 50) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  50);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_HELMET)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1000) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1000);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_CHESTPLATE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1600) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1600);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_LEGGINGS)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 1400) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  1400);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.NETHERITE_BOOTS)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 800) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  800);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.COOKED_BEEF)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 32) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  32);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  ItemStack cookedBeef = new ItemStack(Material.COOKED_BEEF);
                  cookedBeef.setAmount(64);
                  player.getInventory().addItem(cookedBeef);
              }
              if (event.getCurrentItem().getType().equals(Material.GOLDEN_APPLE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 500) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  500);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }
              if (event.getCurrentItem().getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
                  player.closeInventory();
                  if(playerHandler.getToken(player) < 2000) {
                      player.sendMessage(colorHandler.token + "Not enough tokens to purchase this!");
                      return;
                  }
                  playerHandler.removeToken(player,  2000);
                  player.sendMessage(colorHandler.token +"You have purchased " + event.getCurrentItem().getType().toString());
                  player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
              }


          }

    }



}