package com.geppi.other;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerHandler {

    ColorHandler colorHandler = new ColorHandler();
    WarpHandler warpHandler = new WarpHandler();
    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    public void setupPlayer(Player p) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.addDefault("General.Name", p.getName());
        yml.addDefault("General.firstJoin", format.format(now));
        yml.addDefault("General.ipAddress", p.getAddress().getAddress().getHostAddress());
        yml.addDefault("General.Rank", Integer.valueOf(1));
        yml.addDefault("General.jailTime", Integer.valueOf(0));
        yml.addDefault("General.isInJail", Integer.valueOf(0));
        yml.addDefault("General.Money", Integer.valueOf(0));
        yml.addDefault("General.Vanished", Integer.valueOf(0));
        yml.addDefault("General.Token", Integer.valueOf(0));
        yml.addDefault("General.donatePoint", Integer.valueOf(0));
        yml.addDefault("General.lastMessaged", "none");
        yml.addDefault("General.Referral", Integer.valueOf(0));
        yml.addDefault("General.hasReferred", Integer.valueOf(0));
        yml.addDefault("General.ScoreBoard", Integer.valueOf(0));
        yml.addDefault("General.Bounty", Integer.valueOf(0));
        yml.addDefault("General.acceptRules", Integer.valueOf(0));
        yml.addDefault("General.Filter", Integer.valueOf(0));


        yml.addDefault("Punish.Mute", "");
        yml.addDefault("Punish.muteReason", "");

        yml.addDefault("Killstreak.Kills", Integer.valueOf(0));
        yml.addDefault("Killstreak.Deaths", Integer.valueOf(0));
        yml.addDefault("Killstreak.killStreak", Integer.valueOf(0));
        yml.addDefault("Killstreak.LKB", "none");
        yml.addDefault("Killstreak.combatLog", "");


        yml.addDefault("Total.blocksBrokenTotal", Integer.valueOf(0));
        yml.addDefault("Total.voteTotal", Integer.valueOf(0));
        yml.addDefault("Total.blocksPlacedTotal", Integer.valueOf(0));
        yml.addDefault("Total.entitiesKilledTotal", Integer.valueOf(0));
        yml.addDefault("Total.onlineTimeTotal", Integer.valueOf(0));

        yml.addDefault("Reward.blocksBrokenReward", Integer.valueOf(0));
        yml.addDefault("Reward.voteReward", Integer.valueOf(0));
        yml.addDefault("Reward.dailyReward", "");
        yml.addDefault("Reward.blocksPlacedReward", Integer.valueOf(0));
        yml.addDefault("Reward.playersKilledReward", Integer.valueOf(0));
        yml.addDefault("Reward.entitiesKilledReward", Integer.valueOf(0));
        yml.addDefault("Reward.easterEggReward", Integer.valueOf(0));


        yml.addDefault("Donate.donateFeed", Integer.valueOf(0));
        yml.addDefault("Donate.donateHat", Integer.valueOf(0));
        yml.addDefault("Donate.donateNickname", Integer.valueOf(0));
        yml.addDefault("Donate.Nickname", ChatColor.of("#DED7D7") + p.getName());
        yml.addDefault("Donate.donatePvpStatus", Integer.valueOf(0));
        yml.addDefault("Donate.pvpStatus", Integer.valueOf(0));
        yml.addDefault("Donate.donateBail", Integer.valueOf(0));
        yml.addDefault("Donate.donateNameColor", Integer.valueOf(0));
        yml.addDefault("Donate.Spawn", Integer.valueOf(0));
        yml.addDefault("Donate.Stats", Integer.valueOf(0));
        yml.addDefault("Donate.Trails", Integer.valueOf(0));
        yml.addDefault("Donate.discoArmor", Integer.valueOf(0));
        yml.addDefault("Donate.discoArmorStatus", Integer.valueOf(0));
        yml.addDefault("Donate.Trash", Integer.valueOf(0));





        yml.addDefault("Kit.kitTools", "");

        yml.addDefault("Parkour.inParkour", "");
        yml.addDefault("Parkour.parkour1", "");
        yml.addDefault("Parkour.parkour2", "");
        yml.addDefault("Parkour.parkour3", "");
        yml.addDefault("Parkour.parkour4", "");
        yml.addDefault("Parkour.parkour5", "");
        yml.addDefault("Parkour.parkour6", "");
        yml.addDefault("Parkour.parkour7", "");
        yml.addDefault("Parkour.parkour8", "");
        yml.addDefault("Parkour.parkour9", "");

        yml.addDefault("Easteregg.easteregg1", "");
        yml.addDefault("Easteregg.easteregg2", "");
        yml.addDefault("Easteregg.easteregg3", "");
        yml.addDefault("Easteregg.easteregg4", "");
        yml.addDefault("Easteregg.easteregg5", "");
        yml.addDefault("Easteregg.easteregg6", "");
        yml.addDefault("Easteregg.easteregg7", "");
        yml.addDefault("Easteregg.easteregg8", "");
        yml.addDefault("Easteregg.easteregg9", "");

        yml.addDefault("PrisonParkour.prisonParkour1", "");
        yml.addDefault("PrisonParkour.prisonParkour2", "");
        yml.addDefault("PrisonParkour.prisonParkour3", "");
        yml.addDefault("PrisonParkour.prisonParkour4", "");
        yml.addDefault("PrisonParkour.prisonParkour5", "");






        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getPrisonParkourDate(Player player, String parkourNumber) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("PrisonParkour.prisonParkour" + parkourNumber);
    }

    public boolean setPrisonParkourDate(Player player, String parkourNumber) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("PrisonParkour.prisonParkour" + parkourNumber, System.currentTimeMillis());
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getBountyValue(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.getBountyValue.");
    }

    public boolean setBountyValue(Player player, int num) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.getBountyValue.", num);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public int isInJail(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.isInJail");
    }

    public boolean setInJail(Player player, int num) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.isInJail", num);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getJailTime(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.jailTime");
    }

    public boolean setJailTime(Player player, int num) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.jailTime", num);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getCombatLogTime(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.combatLog");
    }

    public boolean setCombatLogTime(Player player, int num) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Killstreak.combatLog", num);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public long getEastereggDate(Player player, String parkourNumber) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("Parkour.parkour" + parkourNumber);
    }

    public boolean setEastereggDate(Player player, String parkourNumber) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Parkour.parkour" + parkourNumber, System.currentTimeMillis());
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public long getParkourDate(Player player, String parkourNumber) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("Parkour.parkour" + parkourNumber);
    }

    public boolean setParkourDate(Player player, String parkourNumber) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Parkour.parkour" + parkourNumber, System.currentTimeMillis());
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setHome(String name, Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        if(yml.contains("Home." + name)) {
            player.sendMessage("This home has already been made!");
            return true;
        }

        yml.set("Home." + name.toUpperCase() + ".W", player.getLocation().getWorld().getName());
        yml.set("Home." + name.toUpperCase() + ".X", player.getLocation().getBlockX());
        yml.set("Home." + name.toUpperCase() + ".Y", player.getLocation().getBlockY());
        yml.set("Home." + name.toUpperCase() + ".Z", player.getLocation().getBlockZ());
        yml.set("Home." + name.toUpperCase() + ".Pitch", player.getLocation().getPitch());
        yml.set("Home." + name.toUpperCase() + ".Yaw", player.getLocation().getYaw());
        player.sendMessage(colorHandler.warp + "Set home " + name);


        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getFilter(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.Filter");
    }

    public boolean setFilter(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.Filter", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public int getDonateTrash(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.Trash");
    }

    public boolean setDonateTrash(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.Trash", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateBail(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.donateBail");
    }

    public boolean setDonateBail(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.donateBail", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getAcceptRules(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.acceptRules");
    }

    public boolean setAcceptRules(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.acceptRules", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getInParkour(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.inParkour");
    }

    public boolean setInParkour(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.inParkour", Integer.valueOf(status));
        try {
            yml.save(f);        
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delHome(String name, Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        if(yml.contains("Home." + name.toUpperCase())) {
            yml.set("Home." + name.toUpperCase(), null);
            player.sendMessage(colorHandler.warp + "Deleted home " + name);

            try {
                yml.save(f);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        player.sendMessage(colorHandler.warp + "Cant find home!");

        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean teleportHome(String name, Player player) {

        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);

        if(yml.contains("Home." + name)) {

            player.sendMessage(colorHandler.warp + "Warping to " + name + "!");

            World w = Bukkit.getServer().getWorld(yml.getString("Home." + name + ".W"));
            int x = yml.getInt("Home." + name + ".X");
            int y = yml.getInt("Home." + name + ".Y");
            int z = yml.getInt("Home." + name + ".Z");
            float pitch = (float) yml.getDouble("Warps." + name + ".Pitch");
            float yaw = (float) yml.getInt("Warps." + name + ".Yaw");


            Location location = new Location(w,x,y,z, (float) pitch, (float) yaw);

            player.teleport(location);
            player.getLocation().setPitch(pitch);
            player.getLocation().setYaw(yaw);
            return true;
        }

        player.sendMessage(colorHandler.warp + "Cant find home!");
        return true;
    }

    public boolean getHomeList(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);


        if(!yml.contains("Home")) {
            player.sendMessage("There are no homes!!");
            System.out.println("DOES IT WORK?");
            return true;
        }

        StringBuilder str = new StringBuilder();
        for (String string : yml.getConfigurationSection("Home").getKeys(false)){
            if(str.length() > 0){
                str.append(ChatColor.GOLD + "," + ChatColor.YELLOW+ " ");
            }
            str.append(string);

        }
        player.sendMessage(colorHandler.warp + "ALl Homes:");
        player.sendMessage(ChatColor.YELLOW + str.toString());
        return true;
    }

    public long getMuteDate(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("Punish.Mute");
    }
    public String getMutedReason(Player p) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getString("Punish.muteReason");
    }
    public boolean setMuteDate(Player player, String reason) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Punish.Mute", System.currentTimeMillis());
        yml.set("Punish.muteReason", reason);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean removeMute(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Punish.Mute", null);
        yml.set("Punish.muteReason", null);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public long getDailyRewardDate(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("Reward.dailyReward");
    }



    public boolean setDailyRewardDate(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Reward.dailyReward", System.currentTimeMillis());
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public long getKitToolsDate(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getLong("Kit.kitTools");
    }

    public boolean setKitToolsDate(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Kit.kitTools", System.currentTimeMillis());
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateFeed(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.donateFeed");
    }

    public boolean setDonateFeed(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.donateFeed", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
//discoArmorStatus

    public int getDonateDiscoArmorStatus(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.discoArmorStatus");
    }

    public boolean setDonateDiscoArmorStatus(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.discoArmorStatus", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateDiscoArmor(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.discoArmor");
    }

    public boolean setDonateDiscoArmor(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.discoArmor", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateStats(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.Stats");
    }

    public boolean setDonateStats(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.Stats", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateTrails(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.Trails");
    }

    public boolean setDonateTrails(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.Trails", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateSpawn(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.Spawn");
    }

    public boolean setDonateSpawn(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.Spawn", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int isVanished(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.Vanished");
    }

    public boolean setVanished(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.Vanished", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getVoteReward(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Reward.voteReward");
    }

    public boolean setVoteReward(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Reward.voteReward", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateHat(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.donateHat");
    }

    public boolean setDonateHat(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.donateHat", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //

    public boolean setEntitiesKilledReward(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Reward.entitiesKilledReward", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getEasterEggReward(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Reward.easterEggReward");
    }
    public boolean setEasterEggReward(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Reward.easterEggReward", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //easterEggReward

    public int getBlocksBroken(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Reward.blocksBrokenReward");
    }
    public boolean setBlocksBroken(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Reward.blocksBrokenReward", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public int getBlocksPlaced(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Reward.blocksPlacedReward");
    }
    public boolean setBlocksPlaced(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Reward.blocksPlacedReward", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public int getEntitiesKilledReward(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Reward.entitiesKilledReward");
    }


    public boolean setPlayersKilledReward(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Reward.playersKilledReward", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getPlayersKilledReward(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Reward.playersKilledReward");
    }


    //


    public int getDonateNickname(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.donateNickname");
    }

    public boolean setDonateNickname(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.donateNickname", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonateNameColor(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.donateNameColor");
    }

    public boolean setDonateNameColor(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.donateNameColor", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonatePvpStatus(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.donatePvpStatus");
    }

    public boolean setDondatePvpStatus(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.donatePvpStatus", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getPvpStatus(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Donate.pvpStatus");
    }

    public boolean setPvpStatus(Player player, int status) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.pvpStatus", Integer.valueOf(status));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean resetJailTime(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.jailTime", "-1");
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getRank(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.Rank");
    }

    public boolean setRank(Player player, int rank) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.Rank", Integer.valueOf(rank));
        try {
            yml.save(f);
            Bukkit.getServer().broadcastMessage(colorHandler.main + "Rank: "+ player.getName() + "'s rank has been updated to " + rankToString(player));
            player.setCustomName(rankToString(player) + getNickname(player));
            player.setPlayerListName(rankToString(player) + getNickname(player));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
        }

    public int getReferrals(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.Referral");
    }

    public boolean setReferrals(Player player, int refer) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.Referral", Integer.valueOf(refer));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getHasReferred(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.hasReferred");
    }

    public boolean setHasReferred(Player player, int refer) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.hasReferred", Integer.valueOf(refer));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getScoreboardToggle(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.Scoreboard");
    }

    public boolean setScoreboardToggle(Player player, int toggle) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.Scoreboard", Integer.valueOf(toggle));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



   public int getMoney(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.Money");
    }

    public Player getPlayer(String string) {
        if (Bukkit.getServer().getPlayer(string) == null) return null;
        return Bukkit.getServer().getPlayer(string);
    }

    public Player getUUIDPlayer(UUID uuid) {
        if (Bukkit.getServer().getPlayer(uuid) == null) return null;
        return Bukkit.getServer().getPlayer(uuid);
    }



    public String rankToString(Player player) {
        if(getRank(player) == 10) {
            return (ChatColor.of("#F5002D") + "Admin " + ChatColor.WHITE);
        }
        if(getRank(player) == 9) {
            return (ChatColor.of("#FC9B00") + "Mod " + ChatColor.WHITE);
        }
        if(getRank(player) == 8) {
            return (ChatColor.of("#FC9B00") + "Jr. Mod " + ChatColor.WHITE);
        }
        if(getRank(player) == 7) {
            return (ChatColor.of("#5E95F0") + "Guard " + ChatColor.WHITE);
        }
        if(getRank(player) == 6) {
            return (ChatColor.of("#5E95F0") + "Jr. Guard " + ChatColor.WHITE);
        }
        if(getRank(player) == 5) {
            return (ChatColor.of("#FC58F2") + "Free " + ChatColor.WHITE);
        }
        if(getRank(player) == 4) {
            return (ChatColor.of("#8A0F82") + "Elite " + ChatColor.WHITE);
        }
        if(getRank(player) == 3) {
            return (ChatColor.of("#0EA2EE") + "Alpha " + ChatColor.WHITE);
        }
        if(getRank(player) == 2) {
            return (ChatColor.of("#338D4C") + "Bravo " + ChatColor.WHITE);
        }
        if(getRank(player) == 1) {
            return (ChatColor.of("#EEEA0E") + "Charlie " + ChatColor.WHITE);
        }
        return String.valueOf(getRank(player));
    }

    public String rankToColorString(Player player) {
        if(getRank(player) == 10) {
            return (ChatColor.of("#F5002D").toString());
        }
        if(getRank(player) == 9) {
            return (ChatColor.of("#FC9B00").toString());
        }
        if(getRank(player) == 8) {
            return (ChatColor.of("#FC9B00").toString());
        }
        if(getRank(player) == 7) {
            return (ChatColor.of("#5E95F0").toString());
        }
        if(getRank(player) == 6) {
            return (ChatColor.of("#5E95F0").toString());
        }
        if(getRank(player) <=5 ) {
            return (ChatColor.of("#DED7D7").toString());
        }
        return String.valueOf(getMoney(player));
    }

    public boolean addMoney(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getMoney(player);
        currentMoney += amount;
        yml.set("General.Money", Integer.valueOf(currentMoney));
        player.sendMessage(colorHandler.main + "Balance: "  + colorHandler.message + String.valueOf(amount) + " money have been added to your account!");
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeMoney(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getMoney(player);
        currentMoney -= amount;
        yml.set("General.Money",  Integer.valueOf(currentMoney));
        player.sendMessage(colorHandler.main + "Balance: "  + colorHandler.message + String.valueOf(amount) + " money have been removed from your account!");

        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getKills(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.Kills");
    }

    public int getDeaths(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.Deaths");
    }

    public int getKillStreak(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.killStreak");
    }

    public boolean resetKillStreak(Player player, int rank) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Killstreak.killStreak", Integer.valueOf(rank));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addKill(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getKills(player);
        currentMoney += amount;
        yml.set("Killstreak.Kills", Integer.valueOf(currentMoney));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addDeath(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getDeaths(player);
        currentMoney += amount;
        yml.set("Killstreak.Deaths", Integer.valueOf(currentMoney));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addToKillStreak(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getKillStreak(player);
        currentMoney += amount;
        yml.set("Killstreak.killStreak", Integer.valueOf(currentMoney));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setLastKilledByPlayer(Player p, String name) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Killstreak.LKB", name);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getLKBP(Player p) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getString("Killstreak.LKB");
    }

    public boolean setLastMessaged(Player p, String name) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.lastMessaged", name);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getLastMessaged(Player p) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getString("General.lastMessaged");
    }

    public boolean setNickname(Player p, String name) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Donate.Nickname", name);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getNickname(Player p) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getString("Donate.Nickname");
    }

    public int getToken(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.Token");
    }

    public boolean addToken(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getToken(player);
        currentMoney += amount;
        yml.set("General.Token",  Integer.valueOf(currentMoney));
        player.sendMessage(colorHandler.main + "Token: "  + colorHandler.message + String.valueOf(amount) + " tokens have been added to your account!");
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean removeToken(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getToken(player);
        currentMoney -= amount;
        yml.set("General.Token", Integer.valueOf(currentMoney));
        player.sendMessage(colorHandler.main + "Token: "  + colorHandler.message + String.valueOf(amount) + " tokens have been removed from your account!");

        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getDonationPoints(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.donatePoint");
    }

    public boolean addDonationPoints(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getDonationPoints(player);
        currentMoney += amount;
        yml.set("General.donatePoint",  Integer.valueOf(currentMoney));
        player.sendMessage(colorHandler.donation + String.valueOf(amount) + " donation points have been added to your account!");
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeDonationPoints(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getDonationPoints(player);
        currentMoney -= amount;
        yml.set("General.donatePoint", Integer.valueOf(currentMoney));
        player.sendMessage(colorHandler.donation + String.valueOf(amount) + " donation points have been removed from your account!");

        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
