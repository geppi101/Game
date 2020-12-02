package com.geppi.other.pets;

import net.minecraft.server.v1_16_R2.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;


public class CustomPet extends EntityZombieHusk {

    public CustomPet(Location loc, Player player, EntityTypes type) {
        super(type, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setBaby(true);

        this.setInvulnerable(true);
        this.setSilent(true);


        //target
        this.setGoalTarget((EntityLiving)((CraftPlayer)player).getHandle(),
                TargetReason.CUSTOM, true);
    }

    @Override
    public void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalPet(this, 1, 15));
        this.goalSelector.a(2, new PathfinderGoalLookAtPlayer(this,
                EntityHuman.class, 8.0F));
    }
}