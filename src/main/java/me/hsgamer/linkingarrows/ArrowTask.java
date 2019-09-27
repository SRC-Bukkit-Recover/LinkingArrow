package me.hsgamer.linkingarrows;

import fr.mrmicky.fastparticle.FastParticle;
import fr.mrmicky.fastparticle.ParticleType;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ArrowTask extends BukkitRunnable {
    private Entity a1;
    private Entity a2;

    public ArrowTask(Entity a1, Entity a2, boolean async) {
        this.a1 = a1;
        this.a2 = a2;
        if (async) {
            runTaskTimerAsynchronously(LinkingArrows.instance, 0, 0);
        } else {
            runTaskTimer(LinkingArrows.instance, 0, 0);
        }
    }

    @Override
    public void run() {
        if (a1.isDead() || a2.isDead()) cancel();
        Location loc = a1.getLocation().clone();
        Vector dir = a2.getLocation().subtract(loc).toVector().normalize().multiply(0.1);
        double distance = loc.distance(a2.getLocation());
        double space = dir.length();
        for (double i = 0.0D; i < distance; i += space) {
            loc = loc.add(dir);
            FastParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc, 5, 0, 0, 0, 0, Color.RED);
            for (Entity entity : loc.getWorld().getNearbyEntities(loc, .3, .3, .3)) {
                if ((entity instanceof LivingEntity) && !(entity instanceof ArmorStand)) {
                    ((Damageable) entity).damage(LinkingArrows.instance.DAMAGE);
                }
            }
        }
    }
}
