package com.geppi.main;

import com.geppi.command.*;
import com.geppi.event.*;
import com.geppi.other.*;
import com.geppi.other.redstone.RedstoneLimit;
import com.geppi.other.scoreboard.ScoreboardMana;

import com.geppi.other.tab.AnimatedTab;
import com.geppi.other.trails.ClickEvent;
import com.geppi.other.trails.GUI;
import com.geppi.other.trails.Movement;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Main extends JavaPlugin {

    JailHandler jailHandler = new JailHandler();
    public WorldGuardPlugin worldGuardPlugin;

    public WarpHandler data;

    WarpHandler warpHandler = new WarpHandler();
    MineHandler mineHandler = new MineHandler();
    FileHandler fileHandler = new FileHandler();
    CrateHandler crateHandler = new CrateHandler();
    VaultHandler vaultHandler = new VaultHandler();
    PlayerHandler playerHandler = new PlayerHandler();
    AnimatedTab animatedTab = new AnimatedTab();
    ScoreboardMana scoreboardMana = new ScoreboardMana();
    CommandTop commandTop = new CommandTop();
    RedstoneLimit redstoneLimit = new RedstoneLimit();


    public Map<UUID, Integer> crateUsesMap;
    public Random random;
    private static Main instance;
    private Object RedstoneLimit;

    public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");

        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }

    public void onEnable() {

       // worldGuardPlugin = getWorldGuard();
        setInstance(this);
        events();
       jailHandler.Jail();
      // scoreboardMana.onEnable();
       animatedTab.onEnable();
       jailHandler.colorArmor();
       this.fileHandler.Setup();
       crateHandler.enable();
        warpHandler.setupWarps();
        mineHandler.setupMines();
        jailHandler.combatLog();
        jailHandler.scoreboard();
//
        vaultHandler.onEnable();
        commandTop.onEnable();
        redstoneLimit.onEnable();


        GUI menu = new GUI();
        menu.register();



    }

    public void onDisable() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            playerHandler.setInJail(p, 0);
            playerHandler.resetJailTime(p);

        }
        vaultHandler.onDisable();
        setInstance(null);
        redstoneLimit.onDisable();

    }

    public void events() {
        Bukkit.getPluginManager().registerEvents(new PlaceBlock(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandEasterEgg(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandSell(), (Plugin)this);

        Bukkit.getPluginManager().registerEvents(new MoveEvent(), (Plugin)this);

        Bukkit.getPluginManager().registerEvents(new KillStreak(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandKillStreak(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandPrivateMessage(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandVote(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new EventSign(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandNickname(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandDonate(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandSuicide(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandSetupMap(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntity(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandToken(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandList(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandPay(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandVanish(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandGamemode(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandWarp(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandKit(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandFeed(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandClear(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new PickupItem(), (Plugin)this); // CLEAN ^
        Bukkit.getPluginManager().registerEvents(new CommandWorld(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandRefer(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new EntityDeath(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandClearChat(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandDiscoArmor(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandPet(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandDispose(), (Plugin)this);


        Bukkit.getPluginManager().registerEvents((Listener)new Leave(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new VaultHandler(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new ScoreboardMana(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandScoreboardToggle(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandBounties(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandDuel(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandTop(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandFix(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandCraft(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandFly(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandFilter(), (Plugin)this);


        Bukkit.getPluginManager().registerEvents(new CraftItem(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandPunish(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandClearLag(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandPlayerHead(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandSpeed(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new FallingBlock(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandSpawn(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new Respawn(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandHelp(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandRename(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandRules(), (Plugin)this);

        Bukkit.getPluginManager().registerEvents(new CommandMoney(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandJail(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandRank(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new PublicChat(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new Join(), (Plugin)this);
       Bukkit.getPluginManager().registerEvents(new EntityAttackEntity(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandPvp(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandHat(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandTeleport(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandContraband(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandStats(), (Plugin)this);
       Bukkit.getPluginManager().registerEvents(new CommandHome(), (Plugin)this);
       Bukkit.getPluginManager().registerEvents(new CommandNamecolor(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new ClickEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new Movement(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CommandTrails(), (Plugin)this);


    }

    public static Main getInstance() {
        return instance;
    }

    private static void setInstance(Main instance) {
        Main.instance = instance;
    }




}
