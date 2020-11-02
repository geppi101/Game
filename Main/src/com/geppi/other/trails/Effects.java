package com.geppi.other.trails;

import com.geppi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Effects {

    private int taskID;
    private final Player player;

    public Effects(Player player) {
        this.player = player;
    }

    public void startTotem() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId());

            @Override
            public void run() {
                if (!particle.hasID()) {
                    particle.setID(taskID);
                }

                var += Math.PI / 16;

                loc = player.getLocation();
                first = loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));

                player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                player.getWorld().spawnParticle(Particle.TOTEM, second, 0);
            }

        }, 0, 1);


    }

    public void startTotem1() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId());

            @Override
            public void run() {
                if (!particle.hasID()) {
                    particle.setID(taskID);
                }

                var += Math.PI / 16;

                loc = player.getLocation();
                first = loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));

                player.getWorld().spawnParticle(Particle.FLAME, first, 0);
                player.getWorld().spawnParticle(Particle.FLAME, second, 0);
            }

        }, 0, 1);
    }

    public void startTotem2() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId());

            @Override
            public void run() {
                if (!particle.hasID()) {
                    particle.setID(taskID);
                }

                var += Math.PI / 16;

                loc = player.getLocation();
                first = loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));

                player.getWorld().spawnParticle(Particle.CLOUD, first, 0);
                player.getWorld().spawnParticle(Particle.CLOUD, second, 0);
            }

        }, 0, 1);
    }

        public void startTotem3() {
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

                double var = 0;
                Location loc, first, second;
                ParticleData particle = new ParticleData(player.getUniqueId());

                @Override
                public void run() {
                    if (!particle.hasID()) {
                        particle.setID(taskID);
                    }

                    var += Math.PI / 16;

                    loc = player.getLocation();
                    first = loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                    second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));

                    player.getWorld().spawnParticle(Particle.HEART, first, 0);
                    player.getWorld().spawnParticle(Particle.HEART, second, 0);
                }

            }, 0, 1);



    }

    public void startTotem4() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId());

            @Override
            public void run() {
                if (!particle.hasID()) {
                    particle.setID(taskID);
                }

                var += Math.PI / 16;

                loc = player.getLocation();
                first = loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));

                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, first, 0);
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH, second, 0);
            }

        }, 0, 1);



    }
}
